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

@WebServlet("/Post")
public class Post extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loggedUser");
		if(user == null) {
			out.println("<script>");
			out.println("alert('Time out!');");
			out.println("location='home.jsp';");
			out.println("</script>");
			request.getRequestDispatcher("/home.jsp").forward(request, response);
			return;
		}
		int user_id = user.getId();
		String title = (String)request.getParameter("title");
		//String image_url = (String)request.getParameter("image");
		String price = (String)request.getParameter("price");
		String location = (String)request.getParameter("location");
		String description = (String)request.getParameter("description");
		String category = (String)request.getParameter("category");
		String status = (String)request.getParameter("status");
		
		String sql = "INSERT INTO item(user_id, title, image_url, price, location, description, category, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(mySQL.db, mySQL.user, mySQL.pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  user_id);
			ps.setString(2, title);
			ps.setString(3, "");
			ps.setString(4, price);
			ps.setString(5,  location);
			ps.setString(6,  description);
			ps.setString(7,  category);
			ps.setString(8,  status);
			ps.executeUpdate();
		}
		catch(Exception e) {
			out.println("<h4>"+ user_id + " " + title + " " + price + " " + location + " " + description + " " + category + " " + status + "</h4>");
			e.printStackTrace();
		}
		session.setAttribute("postlist", Login.loadPostlist(user_id));
		out.println("<script>");
		out.println("alert('Item has been added');");
		out.println("location='post.jsp';");
		out.println("</script>");
		
		request.getRequestDispatcher("/post.jsp").forward(request, response);
	}
}
