package controllers.processes;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Process;

/**
 * Servlet implementation class ProcessesNewServlet
 */
@WebServlet("/processes/new")
public class ProcessesNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessesNewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());

        Process p = new Process();
        p.setCompleted_date(new Date(System.currentTimeMillis()));
        request.setAttribute("process", p);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/owners/new.jsp");
        rd.forward(request, response);
    }

}
