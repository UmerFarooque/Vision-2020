package action;

import dao.IdeaDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class ideaUpdate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String status = request.getParameter("status");
        if(!status.equals("") && !request.getParameter("ideaId").equals("")) {
            try {
                int id = Integer.parseInt(request.getParameter("ideaId"));
                boolean done = IdeaDAO.updateStatus(id, status);
                if(done)
                    request.setAttribute("message", "Updated Successfully!!!");
                request.getRequestDispatcher("display.jsp").forward(request, response);
            } catch(SQLException e) { }
            catch(ClassNotFoundException e) { }
    	} else {
			request.setAttribute("error","Permission denied");
			RequestDispatcher rd = request.getRequestDispatcher("display.jsp");
			rd.forward(request, response);
    	}
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
}
