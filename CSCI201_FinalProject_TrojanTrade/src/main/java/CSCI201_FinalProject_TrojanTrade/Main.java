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

@WebServlet("/Main")
public class Main extends HttpServlet {
	private ArrayList<Item> getItemKeyword(String keyword, String location) {
		ArrayList<Item> result = new ArrayList<>();
		String sql = "SELECT * FROM item WHERE (title LIKE ? or description LIKE ? or category LIKE ?) and location = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(mySQL.db, mySQL.user, mySQL.pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ps.setString(2, "%" + keyword + "%");
			ps.setString(3, "%" + keyword + "%");
			ps.setString(4, location);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				result.add(new Item(rs));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private ArrayList<Item> getItemCategory(String category, String location) {
		ArrayList<Item> result = new ArrayList<>();
		String sql = "SELECT * FROM item WHERE category = ? and location = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(mySQL.db, mySQL.user, mySQL.pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, category);
			ps.setString(2, location);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				result.add(new Item(rs));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private ArrayList<Item> getItemKeywordCategory(String keyword, String category, String location) {
		ArrayList<Item> result = new ArrayList<>();
		String sql = "SELECT * FROM item WHERE title LIKE ? or description LIKE ? or category = ? and location = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(mySQL.db, mySQL.user, mySQL.pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ps.setString(2, "%" + keyword + "%");
			ps.setString(3, category);
			ps.setString(4, location);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				result.add(new Item(rs));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("loggedUser");
		String location = "usc";
		if(user != null) {
			location = user.getLocation();
		}
		String keyword = (String)request.getParameter("item-search");
		String category = (String)request.getParameter("category");
		String page = (String)request.getParameter("page");
		
		// when both keyword and category are not given
		if(keyword.equals("") && category == null) {
			request.setAttribute("warning", "Please provide information for search!");
			 if(page.equals("home")) {
	    		  request.getRequestDispatcher("/home.jsp").include(request, response);
	    	 }
			 else if(page.equals("main")) {
				 request.getRequestDispatcher("/main.jsp").include(request, response);
			 }
		}
		else {
			ArrayList<Item> result = null;
			// when only keyword is given
			if(category == null && !keyword.equals("")) {
				result = getItemKeyword(keyword, location);
				session.setAttribute("term", keyword);
				session.setAttribute("total", result.size());
			}
			// when only category is given
			else if(category != null && keyword.equals("")) {
				result = getItemCategory(category, location);
				session.setAttribute("term", category);
				session.setAttribute("total", result.size());
			}
			// when both keyword and category are given
			else {
				result = getItemKeywordCategory(keyword, category, location);
				String term = keyword + ", " + category;
				session.setAttribute("term", term);
				session.setAttribute("total", result.size());
			}
			session.setAttribute("data", result);
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
	}

}
