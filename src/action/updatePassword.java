package action;

import dao.UserDAO;
import domain.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class updatePassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String password = request.getParameter("password");
        String newPwd = request.getParameter("newPassword");
        HttpSession session = request.getSession();  
        User user = (User)session.getAttribute("userIns");
        try {
	        if(user.getPassword().equals(password)) {
	            UserDAO.updatePassword(user.getId(), newPwd);
	            request.setAttribute("message", "Password Changed Successfully!!!");
	            request.getRequestDispatcher("index.jsp").forward(request, response);
	        }
        } catch(SQLException e) { }
        catch(ClassNotFoundException e) { e.printStackTrace(); }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
