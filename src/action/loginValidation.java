package action;

import dao.*;
import domain.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.text.*;

public class loginValidation extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String value = request.getParameter("action").toString(), page = "";
            if(value.equals("Login")){
            	Connection con = null;
                String username = request.getParameter("username").toString();
                String password = request.getParameter("password").toString();
                if(username.isEmpty() || password.isEmpty()) {
                    request.setAttribute("error", "Please enter all the fields");
                    page = "index.jsp";
                } else {
                    try {
                        con = DBConnection.getConnection();
                        PreparedStatement ps = con.prepareStatement("SELECT * FROM user WHERE name = ? AND password = ?");
                        ps.setString(1, username);
                        ps.setString(2, password);
                        ResultSet rs = ps.executeQuery();
                        if(rs.next()) {
                            if(rs.getString("status").equals("Approved")) {
                                User user = new User();
                                user = UserDAO.findByName(username);
                                HttpSession session = request.getSession();
                                session.setAttribute("userIns", user);
                                page = "display.jsp";
                            }
                            else {
                                request.setAttribute("error", "Permisssion denied");
                                page = "index.jsp";
                            } 
                        } else {
                            request.setAttribute("error", "Invalid Username and Password");
                            page = "index.jsp";
                        }
                    } 
                    catch(SQLException e) { e.printStackTrace(); }
                    catch(ParseException e) { e.printStackTrace(); }
                    catch(ClassNotFoundException e) { e.printStackTrace(); }
                }
                request.getRequestDispatcher(page).forward(request, response);
            } else if(value.equals("Register")) {
                response.sendRedirect("register.jsp");
            }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
