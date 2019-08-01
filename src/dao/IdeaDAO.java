package dao;

import domain.Idea;
import domain.User;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import java.text.*;

public class IdeaDAO {
	
    public static List<Idea> getIdeaListByUser(User userIns) throws SQLException, ParseException, ClassNotFoundException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Idea> ideas = new ArrayList<Idea>();
        Connection con = DBConnection.getConnection();
        int id = userIns.getId();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM idea WHERE user_id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            ideas.add(new Idea(rs.getInt("id"), rs.getString("description"), UserDAO.getUserById(rs.getInt("user_id")), rs.getString("status"), sdf.parse(rs.getString("posted_date"))));
        }
        rs.close();
        ps.close();
        con.close();
        return ideas;
    }
    
    public static List<Idea> getAllApprovedIdea() throws SQLException, ParseException, ClassNotFoundException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Idea> ideas = new ArrayList<Idea>();
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM idea WHERE status = ?");
        ps.setString(1, "Approved");
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            ideas.add(new Idea(rs.getInt("id"), rs.getString("description"), UserDAO.getUserById(rs.getInt("user_id")), rs.getString("status"), sdf.parse(rs.getString("posted_date"))));
        }
        rs.close();
        ps.close();
        con.close();
        return ideas;    
    }
    
    public static List<Idea> getAllIdea() throws SQLException, ParseException, ClassNotFoundException {
        List<Idea> ideas = new ArrayList<Idea>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Connection con = DBConnection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM idea");
        while(rs.next()) {
            ideas.add(new Idea(rs.getInt("id"), rs.getString("description"), UserDAO.getUserById(rs.getInt("user_id")), rs.getString("status"), sdf.parse(rs.getString("posted_date"))));
        }
        rs.close();
        stmt.close();
        con.close();
        return ideas;
    }
    
    public static boolean createIdea(User userIns, String description, int categoryId) throws  ClassNotFoundException, SQLException {
    	PreparedStatement ps = null;
        Connection con = DBConnection.getConnection();
        ps = con.prepareStatement("SELECT id FROM idea WHERE description = ?");
        ps.setString(1, description);
        ResultSet found = ps.executeQuery(); 
        if(found.next()) {
        	found.close();
        	ps.close();
        	con.close();
        	return false;
        }
        else {
        	String status = userIns.getRole().equals("admin") ? "Approved" : "Pending";
            ps = con.prepareStatement("INSERT INTO idea (description, user_id, status, posted_date) VALUES (?, ?, ?, ?)");
            ps.setString(1, description);
            ps.setInt(2, userIns.getId());
            ps.setString(3, status);
            ps.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
            int n = ps.executeUpdate();
            ps.close();
            if(n != 0) {
                ps = con.prepareStatement("Select max(id) FROM idea");
                ResultSet rs = ps.executeQuery();
                int id = 0;
                if(rs.next()) {
                	id = rs.getInt(1);
                }
                rs.close();
                ps.close();
                ps = con.prepareStatement("INSERT INTO idea_category VALUES (?, ?)");
                ps.setInt(1, id);
                ps.setInt(2, categoryId);
                ps.executeUpdate();
                ps.close();
            }
        }
        con.close();
        return true;
    }
    
    public static boolean updateIdea(String description, int ideaId) throws SQLException, ParseException, ClassNotFoundException {
        boolean done = false;
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE idea SET description = ?, posted_date=? WHERE id = ?");
        ps.setString(1, description);
        ps.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
        ps.setInt(3, ideaId);
        int n = ps.executeUpdate();
        if(n > 0) done =  true;
        else done =  false;
        ps.close();
        con.close();
        return done;
    }
    
    public static Idea getIdeaById(int id) throws SQLException, ParseException, ClassNotFoundException {
        Idea idea = new Idea();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM idea WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            idea.setId(rs.getInt("id"));
            idea.setDescription(rs.getString("description"));
            idea.setPostedBy(UserDAO.getUserById(rs.getInt("user_id")));
            idea.setStatus(rs.getString("status"));
            idea.setPostedDate(sdf.parse(rs.getString("posted_date")));
        }
        rs.close();
        ps.close();
        con.close();
        return idea;
    }

    public static boolean updateStatus(int idea, String status) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE idea SET status = ? WHERE id = ?");
        ps.setString(1, status);
        ps.setInt(2, idea);
        int n = ps.executeUpdate();
        ps.close();
        con.close();
        if(n > 0) return true;
        else return false;
    }
    
}
