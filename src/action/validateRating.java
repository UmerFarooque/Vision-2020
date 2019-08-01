package action;

import dao.UserIdeaRatingDAO;
import domain.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import java.sql.SQLException;

public class validateRating extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("userIns");
            int userId = user.getId(), ratingId = Integer.parseInt(request.getParameter("ratingId")), ideaId = Integer.parseInt(request.getParameter("ideaId"));
            try {
            UserIdeaRatingDAO.ratingIdea(ideaId, ratingId, userId);
            RequestDispatcher rd = request.getRequestDispatcher("rate.jsp");
            rd.forward(request, response);
            } catch(SQLException e) { e.printStackTrace(); }
            catch(ClassNotFoundException e) { e.printStackTrace(); }            
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
