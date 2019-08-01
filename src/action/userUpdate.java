package action;

import dao.UserDAO;
import domain.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.sql.SQLException;


public class userUpdate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String status = request.getParameter("status");
        if(!status.equals("") && !request.getParameter("userId").equals("")) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            HttpSession session = request.getSession();  
            User user = (User)session.getAttribute("userIns");
            try {
	            if(user.getRole().equals("admin")) {
	                UserDAO.updateStatus(userId, status);
	                RequestDispatcher rd = request.getRequestDispatcher("approveUsers.jsp");
	                rd.forward(request, response);
	            } else {
	                request.setAttribute("error", "Permission denied");
	                RequestDispatcher rd = request.getRequestDispatcher("approveUsers.jsp");
	                rd.forward(request, response);
	            }
            } catch(SQLException e) { }
            catch(ClassNotFoundException e) { e.printStackTrace(); }
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
