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

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	private boolean validUserName(String username) {
		int index1 = 0;
	    int index2 = 0;
	      // find invalid form for username (checking email form ex) @usc.edu)
	      for(int i=0; i<username.length(); i++) {
	    	  if(username.charAt(i) == '@') {
	    		  index1 = i;
	    		  break;
	    	  }
	      }
	      for(int i=index1+1; i<username.length(); i++) {
	    	  if(username.charAt(i) == '.') {
	    		  index2 = i;
	    		  break;
	    	  }
	      }
	      String address = username.substring(index1+1, index2);
	      String tld = username.substring(index2+1, username.length());
	      // no address for email
	      if(address.length() <= 1) {
	    	  return false;
	      }
	      // no top-level domain
	      else if(tld.length() <= 1) {
	    	  return false;
	      }
	      // invalid top-level domain
	      else if(!tld.equals("edu") && !tld.equals("com") && !tld.equals("net") && !tld.equals("org") && !tld.equals("gov")) {
	    	  return false;
	      }
	      return true;
	}
	private boolean validUser(String username) {
		boolean status = false;
		String sql = "SELECT * FROM user WHERE username = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(mySQL.db, mySQL.user, mySQL.pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  username);
			ResultSet rs = ps.executeQuery();
			status = rs.next();
		}
		catch(Exception e) {}
		return status;
	}
	
	private boolean validPassword(String username, String password) {
		boolean status = false;
		String sql = "SELECT * FROM user WHERE username = ? and password = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(mySQL.db, mySQL.user, mySQL.pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			status = rs.next();
		}
		catch(Exception e) {}
		return status;
		
	}
	
	private User getUser(String username, String password) {
		String sql = "SELECT * FROM user WHERE username = ? and password = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(mySQL.db, mySQL.user, mySQL.pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return new User(rs);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Item> loadPostlist(int user_id) {
		ArrayList<Item> result = new ArrayList<>();
		String sql = "SELECT * FROM item WHERE user_id = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(mySQL.db, mySQL.user, mySQL.pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  user_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				result.add(new Item(rs));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		if(request.getParameter("login") != null) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
		  
			if(!validUserName(username)) {
				request.setAttribute("error", "invalid email form");
				request.getRequestDispatcher("/login.jsp").include(request, response);
			}      
			else if(validUser(username) == false) {
			    request.setAttribute("error", "Couldn't find your Trojan Trade Account.");
			    request.getRequestDispatcher("/login.jsp").include(request, response);
			}
			else if(!validPassword(username, password)) {
			    request.setAttribute("error", "Wrong password. Try again!");
			    request.getRequestDispatcher("/login.jsp").include(request, response);
			}
			else {
				User user = getUser(username, password);
				session.setAttribute("loggedUser", user);

				session.setAttribute("wishlist", Favorite.getWishlist(user.getId()));
				session.setAttribute("postlist", loadPostlist(user.getId()));
				
				// sending alert 
				out.println("<script>");
				out.println("alert('Welcome Back, " + user.getNickname() + "! Enjoy Trojan Trade!');");
				out.println("location='home.jsp';");
				out.println("</script>"); 
				
				request.getRequestDispatcher("/home.jsp").include(request, response);
			}
		}
		out.close();
		
	}
}
