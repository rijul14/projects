package chatServlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/history")
public class history extends HttpServlet {

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

   public void init() throws ServletException {
      // Do required initialization
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	   	response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		
		String name = request.getParameter("user");
		
		User user = (User) session.getAttribute("loggedUser");
		
		int ID = user.getId();
		int otherID = Integer.parseInt(request.getParameter("id"));
		
		String db = "jdbc:mysql://localhost:3306/trojantrade";
		String root = "root";
		String pwd = "goawayjacob";
		String sql = "SELECT * FROM readMessages WHERE from_id = ? AND to_id = ? \n"
				+ "UNION\n"
				+ "SELECT * FROM readMessages WHERE from_id = ? AND to_id = ? \n"
				+ "UNION\n"
				+ "SELECT * FROM unreadMessages WHERE from_id = ? AND to_id = ? \n"
				+ "UNION\n"
				+ "SELECT * FROM unreadMessages WHERE from_id = ? AND to_id = ?\n"
				+ "ORDER BY time ASC";

		ArrayList<String> messages = new ArrayList<String>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
		}
		catch(ClassNotFoundException e) {
			System.out.println("There was a problem with the driver.");
		}
		
		try {
			Connection conn = DriverManager.getConnection(db,root,pwd);
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, ID);
			st.setInt(2, otherID);
			st.setInt(3, otherID);
			st.setInt(4, ID);
			st.setInt(5, ID);
			st.setInt(6, otherID);
			st.setInt(7, otherID);
			st.setInt(8, ID);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String toPrint;
				int from_id = rs.getInt(1);
				String text = rs.getString(3);
				String time = rs.getString(4);
				if(from_id == ID) {
					toPrint = "[" + time + "] You said: " + text + "\r\n";
				}
				else {
					toPrint = "<b>[" + time + "] " + name + " said: " + text + "</b>\r\n";
				}
				messages.add(toPrint);
			}
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
		// Display results
		String output = "<!DOCTYPE html>\r\n"
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
				+ "            <div class = \"header-info-2\">\r\n"
				+ "                <a href = \"messages\">Messages</a>\r\n"
				+ "            </div>\r\n"
				+ "        </div>\r\n"
				+ "    </div>\r\n"
				+ "    \r\n"
				+ "    <div class=\"main\">\r\n"
				+ "       <div class=\"main-content\">\r\n"
				+ "            <div class = \"message-title\">";
		
		output = output + "                Messages with " + name + ": \n";
		output = output + "            </div>\r\n"
						+ "            <div class=\"message-history\">";
		for(int i=0;i<messages.size();i++) {
			output = output + "                <br></br>";
			output = output + "                <b>" + messages.get(i) + "</b>";
		}
		output = output + "<form name=\"messageForm\" method=\"post\" action=\"send?from=" + ID + "&to=" + otherID + "\">\r\n"
				+ "                    Send: <input type=\"text\" name=\"message\" maxlength = \"500\"/> <br/>\r\n"
				+ "                    <input type=\"submit\" value=\"Send\" />\r\n"
				+ "                </form>\r\n"
				+ "            </div>\r\n"
				+ "       </div>\r\n"
				+ "    </div>\r\n"
				+ "\r\n"
				+ "    \r\n"
				+ "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\" crossorigin=\"anonymous\"></script>\r\n"
				+ "</body>\r\n"
				+ "</html>";
		
		out.println(output);
	}

   public void destroy() {
      // do nothing.
   }
}
