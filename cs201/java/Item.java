package CSCI201_FinalProject_TrojanTrade;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Item {
	private int id;
	private int user_id;
	private String title;
	private String image_url;
	private String price;
	private String location;
	private String description; 
	private String category;
	private String status;
	
	public Item(int id, int user_id, String title, String image_url, String time, String price, String location, String description, String category, String status) {
		this.id = id;
		this.user_id = user_id;
		this.title = title;
		this.image_url = image_url;
		this.price = price;
		this.location = location;
		this.description = description;
		this.category = category;
		this.status = status;
	}
	public Item(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.user_id = rs.getInt("user_id");
		this.title = rs.getString("title");
		this.image_url = rs.getString("image_url");
		this.price = rs.getString("price");
		this.location = rs.getString("location");
		this.description = rs.getString("description");
		this.category = rs.getString("category");
		this.status = rs.getString("status");
	}
	
	public int getId() {return this.id;}
	public int getUserId() {return this.user_id;}
	public String getTitle() {return this.title;}
	public String getImage() {return this.image_url;}
	public String getPrice() {return this.price;}
	public String getLocation() {return this.location;}
	public String getDescription() {return this.description;}
	public String getCategory() {return this.category;}
	public String getStatus() {return this.status;}
	
}
