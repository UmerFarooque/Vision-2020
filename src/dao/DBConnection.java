package dao;

import java.sql.*;

public class DBConnection {
	
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
    	Connection con = null;
    		try {
    			Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vision","root", "root");
    		}catch (ClassNotFoundException e) {
    			e.printStackTrace();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
	     return con;      
    }
    
}
