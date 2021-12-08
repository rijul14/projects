package CSCI201_FinalProject_TrojanTrade;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private int validUserName(String username) {
		int result = 0; // 0 = valid, 1 = not in email form, 2 = not unicode
		int index1 = 0;
	    int index2 = 0;
	    
	    // find whether username has invalid letter
	    for(int i=0; i<username.length(); i++) {
	    	  if(Character.isLetterOrDigit(username.charAt(i)) == false) {
	    		  if(username.charAt(i) != '.' && username.charAt(i) != '@') {
	    			  result = 2;
	    		  }
	    	  }
	      }
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
	    	  result = 1;
	      }
	      // no top-level domain
	      else if(tld.length() <= 1) {
	    	  result = 1;
	      }
	      // invalid top-level domain
	      else if(!tld.equals("edu") && !tld.equals("com") && !tld.equals("net") && !tld.equals("org") && !tld.equals("gov")) {
	    	  result = 1;
	      }
	      
	      return result;
	}

	
	private boolean validUser(String username) {
		boolean status = false;
		String sql = "SELECT * FROM WHERE username = ?";
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
	
	private boolean validPassword(String password) {
		int letter = 0;
		int digit = 0;
		int special = 0;
		for(int i=0; i<password.length(); i++) {
	    	if(Character.isLetter(password.charAt(i))) {
	    		  letter++;
	    	}
	    	if(Character.isDigit(password.charAt(i))) {
	    		digit++;
	    	}
	    	if(password.charAt(i) == '!' || password.charAt(i) == '@' || password.charAt(i) == '$'
	    		|| password.charAt(i) == '&' || password.charAt(i) == '?' || password.charAt(i) == '#') {
	    		special++;
	    	}
	    }
		if(letter == 0 || digit == 0 || special == 0) {
			return false;
		}
		return true;
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
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

	    String username = request.getParameter("username");
	    String nickname = request.getParameter("nickname");
	    String password = request.getParameter("password");
	    String location = request.getParameter("location");
	    
	    
	    if(validUserName(username) == 1) {
	    	request.setAttribute("register", "invalid email form");
	    	request.getRequestDispatcher("/login.jsp").include(request, response);
	    }
	    else if(validUserName(username) == 2) {
	    	request.setAttribute("register", "Sorry, only letters (a-z), numbers(0-9), period(.) are allowed.");
	    	request.getRequestDispatcher("/login.jsp").include(request, response);
	    }
	    else if(validUser(username)) {
	    	request.setAttribute("register", "That username is taken. Try another.");
	    	request.getRequestDispatcher("/login.jsp").include(request, response);
	    }
	    else if(!validPassword(password)) {
	    	request.setAttribute("register", "Password should include at least one letter (a-z), one number (0-9), and one special case(!, @, #, $, &, ?)");
	    	request.getRequestDispatcher("/login.jsp").include(request, response);
	    }
	    else {
			String sql = "INSERT INTO user (nickname, username, password, location) VALUES (?, ?, ?, ?)";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(mySQL.db, mySQL.user, mySQL.pwd);
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, nickname);
				ps.setString(2, username);
				ps.setString(3, password);
				ps.setString(4, location);
				ps.executeUpdate();
			}
			catch(Exception e) {}
			
			User user = getUser(username, password);
			request.getSession().setAttribute("loggedUser", user);
			
			// sending alert
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Successfully signed up! Enjoy Trojan Trade!');");
			out.println("location='home.jsp';");
			out.println("</script>"); 
			
			request.getRequestDispatcher("/home.jsp").include(request, response);
	    }
	    
	}


}
