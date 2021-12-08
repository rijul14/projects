package CSCI201_FinalProject_TrojanTrade;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Favorite")
public class Favorite extends HttpServlet{
	
	private static ArrayList<Integer> getItemId(int user_id) {
		ArrayList<Integer> item_id = new ArrayList<>();
		String sql = "SELECT * FROM wishlist WHERE user_id = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(mySQL.db, mySQL.user, mySQL.pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  user_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				item_id.add(rs.getInt("item_id"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return item_id;
	}
	
	private static Item getItem(int item_id) {
		Item item = null;
		String sql = "SELECT * FROM item WHERE id = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(mySQL.db, mySQL.user, mySQL.pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  item_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				item = new Item(rs);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return item;
	}
	
	public static ArrayList<Item> getWishlist(int user_id) {
		ArrayList<Item> result = new ArrayList<>();
		ArrayList<Integer> item_id = getItemId(user_id);
		for(int i : item_id) {
			result.add(getItem(i));
		}
		return result;
	}
	
	private static void addWishList(int user_id, int item_id) {
		String sql = "INSERT INTO wishlist(user_id, item_id) VALUES (?, ?)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(mySQL.db, mySQL.user, mySQL.pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  user_id);
			ps.setInt(2, item_id);
			ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void removeWishList(int user_id, int item_id) {
		String sql = "DELETE FROM wishlist WHERE user_id=? AND item_id = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(mySQL.db, mySQL.user, mySQL.pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  user_id);
			ps.setInt(2, item_id);
			ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		int item_id =  Integer.parseInt(request.getParameter("index"));
		
		// check authorized user
		User log = (User)session.getAttribute("loggedUser");
		// not login
        if(log == null) {
        	// sending alert 
        	out.println("<script>");
			out.println("alert('Need to login to add favorite!');");
			out.println("location='detail.jsp';");
			out.println("</script>"); 
			request.getRequestDispatcher("/detail.jsp").include(request,response);
			return;
        }
        
        int user_id = log.getId();
        boolean check = Lists.checkWishList(user_id, item_id);
    	// add item to wishlist
        if(check == false) {
	        addWishList(user_id, item_id);
	        // sending alert
	        out.println("<script>");
	    	out.println("alert('The item has been added to your favorite!');");
	    	out.println("location='detail.jsp';");
    		out.println("</script>"); 
    	}
        // remove item from wishlist
    	else if(check == true){
    		removeWishList(user_id, item_id);
    		// sending alert
            out.println("<script>");
    		out.println("alert('The item has been removed from your favorite!');");
    		out.println("location='detail.jsp';");
    		out.println("</script>"); 
    	}
        
        ArrayList<Item> result = getWishlist(user_id);
        session.setAttribute("wishlist", result);
        request.getRequestDispatcher("/detail.jsp").include(request,response);
	}

}
