package dao;

import domain.Rating;
import domain.Category;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class RatingDAO {
	
    public static List<Rating> getAllRating() throws SQLException, ClassNotFoundException {
        List<Rating> allRatings = new ArrayList<Rating>();
        Connection con = DBConnection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from rating");
        while(rs.next()) {
            allRatings.add(new Rating(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getInt("value")));
        }
        rs.close();
        stmt.close();
        con.close();
        return allRatings;
    }
    
    public static Rating getRatingById(int id) throws SQLException, ClassNotFoundException {
        Rating rating = new Rating();
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM rating WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            rating.setId(rs.getInt("id"));
            rating.setName(rs.getString("name"));
            rating.setDescription(rs.getString("description"));
            rating.setValue(rs.getInt("value"));
        }
        rs.close();
        ps.close();
        con.close();
        return rating;        
    }
    
    public static List<Category> getAllCategory() throws SQLException, ClassNotFoundException {       
        List<Category> categories = new ArrayList<Category>();
        Connection con = DBConnection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM category");
        while(rs.next()) {
            Category cat = new Category(rs.getInt(1), rs.getString(2));
            categories.add(cat);
        }
        rs.close();
        stmt.close();
        con.close();
        return categories;       
    }

}
