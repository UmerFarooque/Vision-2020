package dao;

import domain.User;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.text.*;

public class UserDAO {
	
    public static User findByName(String name) throws SQLException, ParseException, ClassNotFoundException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        User user = new User();
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM user WHERE name = ?");
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setAge(rs.getInt("age"));
            user.setRole(rs.getString("role"));
            user.setCreatedDate(sdf.parse(rs.getString("created_date")));
            user.setStatus(rs.getString("status"));
        }
        rs.close();
        ps.close();
        con.close();
        return user;
    }

    public static User getUserById(int id) throws SQLException, ParseException, ClassNotFoundException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        User user = new User();
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM user WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setAge(rs.getInt("age"));
            user.setRole(rs.getString("role"));
            user.setCreatedDate(sdf.parse(rs.getString("created_date")));
            user.setStatus(rs.getString("status"));
        }
        rs.close();
        ps.close();
        con.close();
        return user;     
    }

    public static boolean register(String name, String password, String email, int age) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("INSERT INTO user(name, email, password, age, role, created_date, status) VALUES (?, ?, ?, ?, ?, ?, ?)");
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, password);
        ps.setInt(4, age);
        ps.setString(5, "user");
        ps.setDate(6, new java.sql.Date(new java.util.Date().getTime()));
        ps.setString(7, "Pending");
        int n = ps.executeUpdate();
        ps.close();
        con.close();
        if(n > 0) return true;
        else return false;   
    }

    public static boolean updatePassword(int id, String newPwd) throws SQLException, ClassNotFoundException {
        boolean done = false;
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE user SET password = ? WHERE id = ?");
        ps.setString(1, newPwd);
        ps.setInt(2, id);
        int n = ps.executeUpdate();
        ps.close();
        con.close();
        if(n > 0) done = true;
        else done = false;
        return done;    
        
    }
    
    public static boolean updateStatus(int userId, String status) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE user SET status = ? WHERE id = ?");
        ps.setString(1, status);
        ps.setInt(2, userId);
        int n = ps.executeUpdate();
        ps.close();
        con.close();
        if(n > 0) return true;
        else return false;
    }
    
    public static List<User> getAllUsers() throws SQLException, ParseException, ClassNotFoundException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<User> list = new ArrayList<User>();
        User user;
        Connection con = DBConnection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user");
        while(rs.next()) {
            user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), sdf.parse(rs.getString(7)), rs.getString(8));
            list.add(user);
        }
        rs.close();
        stmt.close();
        con.close();
        return list;
    }
}
