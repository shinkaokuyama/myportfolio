package controllers.login;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Process;
import models.User;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("hasError", false);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    // ログイン処理を実行
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 認証結果を格納する変数
        Boolean check_result = false;

        String code = request.getParameter("code");
        String plain_pass = request.getParameter("password");

        User u = null;

        if(code != null && !code.equals("") && plain_pass != null && !plain_pass.equals("")) {
            EntityManager em = DBUtil.createEntityManager();

            String password = EncryptUtil.getPasswordEncrypt(
                    plain_pass,
                    (String)this.getServletContext().getAttribute("pepper")
                    );

            // 社員番号とパスワードが正しいかチェックする
            try {
                u = em.createNamedQuery("checkLoginCodeAndPassword", User.class)
                      .setParameter("code", code)
                      .setParameter("pass", password)
                      .getSingleResult();
            } catch(NoResultException ex) {}

            em.close();

            if(u != null) {
                check_result = true;
            }
        }

        if(!check_result) {
            // 認証できなかったらログイン画面に戻る
            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("hasError", true);
            request.setAttribute("code", code);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
            rd.forward(request, response);
        } else {
            // 認証できたらログイン状態にしてトップページへリダイレクト
            request.getSession().setAttribute("login_user", u);

            request.getSession().setAttribute("login_user", u);
            request.getSession().setAttribute("flush", "ログインしました。");
        }
        if(u.getAdmin_flag() == 1){
            EntityManager em = DBUtil.createEntityManager();

            int page;
            try{
                page = Integer.parseInt(request.getParameter("page"));
            }catch(Exception e) {
                page = 1;
            }
            List<User> users = em.createNamedQuery("getAllUsers",User.class)
                                 .setFirstResult(15 * (page - 1))
                                 .setMaxResults(15)
                                 .getResultList();

            long users_count = (long)em.createNamedQuery("getUsersCount", Long.class)
                                       .getSingleResult();

            em.close();

            request.setAttribute("users", users);
            request.setAttribute("users_count", users_count);
            request.setAttribute("page", page);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
            rd.forward(request, response);
        }else{
            EntityManager em = DBUtil.createEntityManager();

            User login_user = (User)request.getSession().getAttribute("login_user");

            int page;
            try{
                page = Integer.parseInt(request.getParameter("page"));
            }catch(Exception e){
                page = 1;
            }
            List<Process> processes = em.createNamedQuery("getMyAllProcesses", Process.class)
                                        .setParameter("user", login_user)
                                        .setFirstResult(15 * (page - 1))
                                        .setMaxResults(15)
                                        .getResultList();

            long processes_count = (long)em.createNamedQuery("getMyProcessesCount", Long.class)
                                           .setParameter("user", login_user)
                                           .getSingleResult();

            em.close();


            request.setAttribute("processes", processes);
            request.setAttribute("processes_count", processes_count);
            request.setAttribute("page", page);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/owner.jsp");
            rd.forward(request, response);
        }
    }

}