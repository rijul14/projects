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

@WebServlet("/Setting")
public class Setting extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		int id =  Integer.parseInt(request.getParameter("index"));
		String sql = "SELECT * FROM user WHERE id = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(mySQL.db, mySQL.user, mySQL.pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				session.setAttribute("setting", new User(rs));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/setting.jsp").forward(request, response);
	}
}
