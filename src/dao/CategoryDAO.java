package dao;

import domain.Category;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class CategoryDAO {
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
