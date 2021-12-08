package CSCI201_FinalProject_TrojanTrade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Lists {
	public static boolean checkWishList(int user_id, int item_id) {
		boolean status = false;
		String sql = "SELECT * FROM wishlist WHERE wishlist.user_id = ? and wishlist.item_id = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(mySQL.db, mySQL.user, mySQL.pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  user_id);
			ps.setInt(2, item_id);
			ResultSet rs = ps.executeQuery();
			status = rs.next();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	
	public static void main(String[] args) {
		String sql = "INSERT INTO item(user_id, title, image_url, price, location, description, category, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(mySQL.db, mySQL.user, mySQL.pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  2);
			ps.setString(2, "TEST");
			ps.setString(3, "");
			ps.setString(4, "TEST");
			ps.setString(5,  "USC");
			ps.setString(6,  "TEST");
			ps.setString(7,  "TEST");
			ps.setString(8,  "Selling");
			ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
