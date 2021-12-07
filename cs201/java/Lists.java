package CSCI201_FinalProject_TrojanTrade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Lists {
	public boolean checkWishList(int user_id, int item_id) {
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
		System.out.println("1");
		String sql = "INSERT INTO users (nickname, username, password, location) VALUES (?, ?, ?, ?)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(mySQL.db, mySQL.user, mySQL.pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println("2");
			ps.setString(1, "Example");
			ps.setString(2, "example@usc.edu");
			ps.setString(3, "example123");
			ps.setString(4, "USC");
			ps.executeUpdate();
		}
		catch(Exception e) {e.printStackTrace();}
		System.out.println("4");
		
	}

}
