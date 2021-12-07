package CSCI201_FinalProject_TrojanTrade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	private int id;
	private String nickname;
	private String username;
	private String password;
	private String location;
	
	public User(int id, String nickname, String username, String password, String location) {
		this.id = id;
		this.nickname = nickname;
		this.username = username;
		this.password = password; 
		this.location = location; 
	}
	
	public User(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.nickname = rs.getString("nickname");
		this.username = rs.getString("username");
		this.password = rs.getString("password");
		this.location = rs.getString("location");	
	}
	
	public int getId() {return this.id;}
	public String getNickname() {return this.nickname;}
	public String getUsername() {return this.username;}
	public String getPassword() {return this.password;}
	public String getLocation() {return this.location;}
	
	public static String getNameById(int user_id) {
		String sql = "SELECT * FROM user WHERE id = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(mySQL.db, mySQL.user, mySQL.pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  user_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString("nickname");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "Anonymous";
	}

}
