package action;

import dao.*;
import domain.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.text.ParseException;

public class validateIdea extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                HttpSession session = request.getSession();
                User userIns = (User)session.getAttribute("userIns");
                String page = null;
                if(request.getParameter("createOrUpdate").toString().equals("Create")){
                    int catId = Integer.parseInt(request.getParameter("categoryId").toString());
                    String description = request.getParameter("idea").toString();
                    boolean created = IdeaDAO.createIdea(userIns, description, catId);
                    if(!created) {
                        request.setAttribute("error", "Idea Exists");
                        page = "create.jsp";
                    } else {
                        request.setAttribute("message", "Idea Created Successfully!!");
                        if(userIns.getRole().equals("user")) {
                        	page = "display.jsp";
                        } else {
                        	page = "myIdeas.jsp";
                        }
                    }
                } else if(request.getParameter("createOrUpdate").toString().equals("Update")) {
                    int id1 = Integer.parseInt(request.getParameter("ideaId"));
                    String description = request.getParameter("idea");
                    boolean done = IdeaDAO.updateIdea(description, id1);
                    if(done) {
                    	request.setAttribute("message", "Idea Updated Successfully!!");
                    } else {
                    	request.setAttribute("error", "Cannot update your Idea.");
                    }
                    page = "display.jsp";
                }
                request.getRequestDispatcher(page).forward(request, response);
            } catch (SQLException ex) {
            	ex.printStackTrace();
            } catch (ParseException e) { e.printStackTrace(); }
            catch(ClassNotFoundException e) { e.printStackTrace(); }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}

