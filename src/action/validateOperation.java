package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class validateOperation extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation").toString();
        String page = null, redirect = null;
        if(operation.equals("Approve Users")) {
        	page = "approveUsers.jsp";
        } else if(operation.equals("My Ideas")) {
        	page = "myIdeas.jsp";
        } else if(operation.equals("Contribute")) {
        	page = "create.jsp";
        } else if(operation.equals("Rate Idea")) {
        	page = "rate.jsp";
        } else if(operation.equals("Change Password")) {
        	page = "changePassword.jsp";
        } else if(operation.equals("Logout")) {
        	redirect = "index.jsp";
            HttpSession session = request.getSession(false);
            if(session != null)
                session.invalidate();
        }
        if(redirect != null) response.sendRedirect(redirect);
        else request.getRequestDispatcher(page).forward(request,  response);
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
