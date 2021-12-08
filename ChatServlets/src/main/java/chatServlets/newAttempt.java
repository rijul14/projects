package chatServlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/attemptNew")
public class newAttempt extends HttpServlet {

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

   public void init() throws ServletException {
      // Do required initialization
   }

   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	   	response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		
		String fromID = request.getParameter("from");
		String to = request.getParameter("target");
		String text = request.getParameter("message");
		
		Instant instant = Instant.now();
		Timestamp timestamp = Timestamp.from(instant);
		String time = timestamp.toString();
		
		String db = "jdbc:mysql://localhost:3306/trojantrade";
		String root = "root";
		String pwd = "goawayjacob";
		String sql0 = "SELECT id FROM user WHERE username = ?";
		String sql1 = "INSERT INTO readMessages SELECT * FROM unreadMessages WHERE from_id = ? AND to_id = ?";
		String sql2 = "SET SQL_SAFE_UPDATES = 0";
		String sql3 = "DELETE FROM unreadMessages WHERE from_id = ? AND to_id = ?";
		String sql4 = "SET SQL_SAFE_UPDATES = 1";
		String sql5 = "INSERT INTO unreadMessages VALUES(?,?,?,?)";
		//should sanitize but since executeUpdate doesn't allow multiple commands we actually live
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
		}
		catch(ClassNotFoundException e) {
			System.out.println("There was a problem with the driver.");
		}
		
		Boolean found = false;
		
		try {
			Connection conn = DriverManager.getConnection(db,root,pwd);
			PreparedStatement st = conn.prepareStatement(sql0);
			st.setString(1, to);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				found = true;
				String toID = rs.getString(1);
				st = conn.prepareStatement(sql1);
				st.setInt(1, Integer.parseInt(toID));
				st.setInt(2, Integer.parseInt(fromID));
				st.executeUpdate();
				st = conn.prepareStatement(sql2);
				st.executeUpdate();
				st = conn.prepareStatement(sql3);
				st.setInt(1, Integer.parseInt(toID));
				st.setInt(2, Integer.parseInt(fromID));
				st.executeUpdate();
				st = conn.prepareStatement(sql4);
				st.executeUpdate();
				st= conn.prepareStatement(sql5);
				st.setInt(1, Integer.parseInt(fromID));
				st.setInt(2, Integer.parseInt(toID));
				st.setString(3, text);
				st.setString(4, time);
				st.executeUpdate();
			}
		}
		catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
		}
		
		String output;
		
		if(found) {
		
			// Display results
			output = "<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "<head>\r\n"
					+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\r\n"
					+ "\r\n"
					+ "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n"
					+ "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n"
					+ "    <link href=\"https://fonts.googleapis.com/css2?family=Lobster&display=swap\" rel=\"stylesheet\">\r\n"
					+ "\r\n"
					+ "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n"
					+ "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n"
					+ "    <link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap\" rel=\"stylesheet\"> \r\n"
					+ "\r\n"
					+ "    <link rel=\"stylesheet\" type=\"text/css\" href=\"page.css\">\r\n"
					+ "    \r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <title>Document</title>\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "    <div class=\"header\">\r\n"
					+ "        <div>\r\n"
					+ "            <a class=\"header-logo\" href=\"test\">Trojan Trade</a>\r\n"
					+ "        </div>\r\n"
					+ "        <div class=\"header-info\">\r\n"
					+ "            <div class=\"header-info-1\">\r\n"
					+ "                <a href=\"test\">Home</a>\r\n"
					+ "            </div>\r\n"
					+ "            <div class=\"header-info-2\">\r\n"
					+ "                <a href=\"settings?user=1\">Login/Register</a>\r\n"
					+ "            </div> \r\n"
					+ "            <div class=\"header-info-2\">\r\n"
					+ "                <a href=\"settings?user=1\">Settings</a>\r\n"
					+ "            </div> \r\n"
					+ "            <div class = \"header-info-2\">\r\n"
					+ "                <a href = \"messages\">Messages</a>\r\n"
					+ "            </div>\r\n"
					+ "        </div>\r\n"
					+ "    </div>\r\n"
					+ "    \r\n"
					+ "    <div class=\"main\">\r\n"
					+ "       <div class=\"main-content\">\r\n"
					+ "            <div class = \"title\">\r\n"
					+ "                Message sent!\r\n"
					+ "            </div>\r\n"
					+ "            <div class=\"message-history\">\r\n"
					+ "                <a href = \"messages\">Back to messages</a>\r\n"
					+ "            </div>\r\n"
					+ "       </div>\r\n"
					+ "    </div>\r\n"
					+ "\r\n"
					+ "    \r\n"
					+ "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\" crossorigin=\"anonymous\"></script>\r\n"
					+ "</body>\r\n"
					+ "</html>";
		}
		else {
			output = "<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "<head>\r\n"
					+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\r\n"
					+ "\r\n"
					+ "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n"
					+ "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n"
					+ "    <link href=\"https://fonts.googleapis.com/css2?family=Lobster&display=swap\" rel=\"stylesheet\">\r\n"
					+ "\r\n"
					+ "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n"
					+ "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n"
					+ "    <link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap\" rel=\"stylesheet\"> \r\n"
					+ "\r\n"
					+ "    <link rel=\"stylesheet\" type=\"text/css\" href=\"page.css\">\r\n"
					+ "    \r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <title>Document</title>\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "    <div class=\"header\">\r\n"
					+ "        <div>\r\n"
					+ "            <a class=\"header-logo\" href=\"test\">Trojan Trade</a>\r\n"
					+ "        </div>\r\n"
					+ "        <div class=\"header-info\">\r\n"
					+ "            <div class=\"header-info-1\">\r\n"
					+ "                <a href=\"test\">Home</a>\r\n"
					+ "            </div>\r\n"
					+ "            <div class=\"header-info-2\">\r\n"
					+ "                <a href=\"settings?user=1\">Login/Register</a>\r\n"
					+ "            </div> \r\n"
					+ "            <div class=\"header-info-2\">\r\n"
					+ "                <a href=\"settings?user=1\">Settings</a>\r\n"
					+ "            </div> \r\n"
					+ "            <div class = \"header-info-2\">\r\n"
					+ "                <a href = \"messages\">Messages</a>\r\n"
					+ "            </div>\r\n"
					+ "        </div>\r\n"
					+ "    </div>\r\n"
					+ "    \r\n"
					+ "    <div class=\"main\">\r\n"
					+ "       <div class=\"main-content\">\r\n"
					+ "            <div class = \"title\">\r\n"
					+ "                Sorry, we didn't find anyone with that username\r\n"
					+ "            </div>\r\n"
					+ "            <div class=\"message-history\">\r\n"
					+ "                <a href = \"messages\">Back to messages</a>\r\n"
					+ "            </div>\r\n"
					+ "       </div>\r\n"
					+ "    </div>\r\n"
					+ "\r\n"
					+ "    \r\n"
					+ "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\" crossorigin=\"anonymous\"></script>\r\n"
					+ "</body>\r\n"
					+ "</html>";
			
		}
		
		out.println(output);
	}

   public void destroy() {
      // do nothing.
   }
}
