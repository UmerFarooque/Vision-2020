package dao;

import domain.UserIdeaRating;
import java.util.ArrayList;
import java.util.List;
import java.text.*;
import java.sql.*;


public class UserIdeaRatingDAO {
	
    public static boolean ratingIdea(int ideaId, int ratingId, int userId) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("Insert into user_idea_rating(idea_id, rating_id, user_id, rated_date) VALUES (?, ?, ?, ?)");          
        ps.setInt(1, ideaId);
        ps.setInt(2, ratingId);
        ps.setInt(3, userId);
        ps.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
        int n = ps.executeUpdate();
        ps.close();
        con.close();
        if(n > 0) return true;
        else return false;
    }
    
    public static List<UserIdeaRating> getRatingByUserId(int userId) throws SQLException, ParseException, ClassNotFoundException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<UserIdeaRating> all = new ArrayList<UserIdeaRating>();
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM user_idea_rating WHERE user_id = ?");
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            all.add(new UserIdeaRating(IdeaDAO.getIdeaById(rs.getInt("idea_id")), RatingDAO.getRatingById(rs.getInt("rating_id")), UserDAO.getUserById(rs.getInt("user_id")), sdf.parse(rs.getString("rated_date"))));
        }
        rs.close();
        ps.close();
        con.close();
        return all;
    }
    
}
