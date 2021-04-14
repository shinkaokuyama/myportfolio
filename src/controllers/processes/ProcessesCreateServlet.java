package controllers.processes;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Process;
import models.User;
import models.validators.ProcessValidator;
import utils.DBUtil;

/**
 * Servlet implementation class ProcessesCreateServlet
 */
@WebServlet("/processes/create")
public class ProcessesCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessesCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Process p = new Process();

            p.setUser((User)request.getSession().getAttribute("login_user"));

            Date completed_date = new Date(System.currentTimeMillis());
            String rd_str = request.getParameter("completed_date");
            if(rd_str != null && rd_str.equals("")){
                completed_date = Date.valueOf(request.getParameter("completed_date"));
            }
            p.setCompleted_date(completed_date);

            p.setProcess_name(request.getParameter("process_name"));
            p.setMessage(request.getParameter("message"));

            List<String> errors = ProcessValidator.validate(p);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("process", p);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/owners/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(p);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/processes/index");
            }
        }



    }

}
