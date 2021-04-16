package controllers.processes;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Process;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class ProcessesEditServlet
 */
@WebServlet("/processes/edit")
public class ProcessesEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessesEditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Process p = em.find(Process.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        User login_user = (User)request.getSession().getAttribute("login_user");
        if(p != null && login_user.getId() == p.getUser().getId()) {
            request.setAttribute("process", p);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("process_id", p.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/owners/edit.jsp");
        rd.forward(request, response);
    }

}
