package action;

import dao.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;

public class validateUser extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String name, password, email, age, page = "";
            try {
	            if(request.getParameter("action")!=null) {
	                name = request.getParameter("username").toString();
	                password = request.getParameter("password").toString();
	                email = request.getParameter("email").toString();
	                age = request.getParameter("age");
	                if(request.getParameter("action").toString().equals("Cancel")) {
	                    response.sendRedirect("index.jsp");
	                    return;
	                }
	                else if(name.isEmpty() || password.isEmpty() || email.isEmpty() || age.isEmpty()) {
	                    request.setAttribute("error", "Please enter all the fields");
	                    page = "register.jsp";
	                }
	                else if(request.getParameter("action").toString().equals("Register")) {
	                    boolean status = UserDAO.register(name, password, email, Integer.parseInt(age));
	                    if(status) 
	                    request.setAttribute("message", "Registered Successfully");
	                    page = "index.jsp";
	                }
	                
	            }
            } catch(SQLException e) { e.printStackTrace(); }
            catch(ClassNotFoundException e) { e.printStackTrace(); }
            RequestDispatcher rd = request.getRequestDispatcher(page);
            rd.forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
