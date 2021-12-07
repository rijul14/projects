package finalProject;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;


@WebServlet("/verify")
public class verify extends HttpServlet {

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
		
		String userId = request.getParameter("user");
		String newNick = request.getParameter("nickname");
		String newUser = request.getParameter("username");
		String newPass = request.getParameter("password");
		String newLoc = request.getParameter("location");
		String verifyPass = request.getParameter("verifyPass");

		String db = "jdbc:mysql://localhost:3306/trojantrade";
		String user = "root";
		String pwd = "Kbwoo100!sql";
		String sql = "SELECT * FROM user WHERE user.id =" + userId;
		
		String oldNick = null;
		String oldUser = null;
		String oldPass = null;
		String oldLoc = null;
		
		try (Connection conn = DriverManager.getConnection(db, user, pwd);
		      Statement st = conn.createStatement();
			  ResultSet rs = st.executeQuery(sql);) {
			if (rs.next()) {
				oldNick = rs.getString("nickname");
				oldUser = rs.getString("username");
				oldPass = rs.getString("password");
				oldLoc = rs.getString("location");
			}
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
		boolean verified = true;
		boolean nickChange = false;
		boolean userChange = false;
		boolean passChange = false;
		boolean locChange = false;
		String error = null;
		
		sql = "SELECT * FROM user WHERE user.nickname LIKE '" + newNick + "'";
		try (Connection conn = DriverManager.getConnection(db, user, pwd);
			      Statement st = conn.createStatement();
				  ResultSet rs = st.executeQuery(sql);) {
				if (rs.next() && !newNick.equals(oldNick)) {
					verified = false;
					error = "The nickname is already taken";
				}
				if (!newNick.equals(oldNick)) {
					nickChange = true;
				}
			} catch (SQLException sqle) {
				System.out.println ("SQLException: " + sqle.getMessage());
			}
		
		sql = "SELECT * FROM user WHERE user.username LIKE '" + newUser + "'";
		try (Connection conn = DriverManager.getConnection(db, user, pwd);
			      Statement st = conn.createStatement();
				  ResultSet rs = st.executeQuery(sql);) {
				if (rs.next() && !newUser.equals(oldUser)) {
					verified = false;
					error = "The username is already taken.";
				}
				if (!newUser.equals(oldUser)) {
					userChange = true;
				}
			} catch (SQLException sqle) {
				System.out.println ("SQLException: " + sqle.getMessage());
			}
		
		String replacePass = newPass.replaceAll("\\s", "");
		
		if (!replacePass.equals(oldPass) && !replacePass.isBlank()) {
			passChange = true;
		}
		
		if (!newLoc.equals(oldLoc)) {
			locChange = true;
		}
		
		if (verifyPass == null) {
			verified = false;
			error = "Failed password verification.";
		}
		
		if (!verifyPass.equals(oldPass)) {
			verified = false;
			error = "Failed password verification.";
		}
		
		if (verified) {
			// Change appropriate sql data
			if (nickChange) {
				sql = "UPDATE user SET user.nickname = '" + newNick + "' WHERE user.id =" + userId;
				try {
					Connection conn = DriverManager.getConnection(db, user, pwd);
					Statement st = conn.createStatement();
				    int result = st.executeUpdate(sql);
				}
				catch (SQLException sqle) {
					System.out.println ("SQLException: " + sqle.getMessage());
				}
			}
			
			if (userChange) {
				sql = "UPDATE user SET user.username = '" + newUser + "' WHERE user.id =" + userId;
				try {
					Connection conn = DriverManager.getConnection(db, user, pwd);
					Statement st = conn.createStatement();
				    int result = st.executeUpdate(sql);
				}
				catch (SQLException sqle) {
					System.out.println ("SQLException: " + sqle.getMessage());
				}
			}
			
			if (passChange) {
				sql = "UPDATE user SET user.password = '" + newPass + "' WHERE user.id =" + userId;
				try {
					Connection conn = DriverManager.getConnection(db, user, pwd);
					Statement st = conn.createStatement();
				    int result = st.executeUpdate(sql);
				}
				catch (SQLException sqle) {
					System.out.println ("SQLException: " + sqle.getMessage());
				}
			}
			
			if (locChange) {
				sql = "UPDATE user SET user.location = '" + newLoc + "' WHERE user.id =" + userId;
				try {
					Connection conn = DriverManager.getConnection(db, user, pwd);
					Statement st = conn.createStatement();
				    int result = st.executeUpdate(sql);
				}
				catch (SQLException sqle) {
					System.out.println ("SQLException: " + sqle.getMessage());
				}
			}

			// Display results
			out.println("<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "<head>\r\n"
					+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\r\n"
					+ "\r\n"
					+ "	<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n"
					+ "	<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n"
					+ "	<link href=\"https://fonts.googleapis.com/css2?family=Lobster&display=swap\" rel=\"stylesheet\">\r\n"
					+ "\r\n"
					+ "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n"
					+ "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n"
					+ "    <link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap\" rel=\"stylesheet\"> \r\n"
					+ "\r\n"
					+ "	<link rel=\"stylesheet\" type=\"text/css\" href=\"page.css\">\r\n"
					+ "	\r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <title>Document</title>\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "    <div class=\"header\">\r\n"
					+ "        <div>\r\n"
					+ "            <a class=\"header-logo\" href=\"searchDetails.html\">Trojan Trade</a>\r\n"
					+ "        </div>\r\n"
					+ "        <div class=\"header-info\">\r\n"
					+ "            <div class=\"header-info-1\">\r\n"
					+ "                <a href=\"searchDetails.html\">Home</a>\r\n"
					+ "            </div>\r\n"
					+ "            <div class=\"header-info-2\">\r\n"
					+ "                <a href=\"settings?item=122\">Login/Register</a>\r\n"
					+ "            </div> \r\n"
					+ "            <div class=\"header-info-2\">\r\n"
					+ "                <a href=\"settings?item=122\">Settings</a>\r\n"
					+ "            </div> \r\n"
					+ "        </div>\r\n"
					+ "    </div>\r\n"
					+ "    \r\n"
					+ "    <div class=\"main\">\r\n"
					+ "       <div class=\"main-content\">\r\n"
					+ "            <div class=\"search container\">\r\n"
					+ "                <div class=\"form-group row results\">\r\n"
					+ "                    <div class=\"col-sm-12 title\">\r\n"
					+ "                        Settings\r\n"
					+ "                    </div>\r\n"
					+ "                </div>\r\n"
					+ "                <div class=\"form-group row settings\">\r\n"
					+ "                    <div class=\"col-sm-12 verify\">\r\n"
					+ "                       *Settings successfully changed\r\n"
					+ "                    </div>\r\n"
					+ "                </div>\r\n"
					+ "                <div class=\"form-group row settings\">\r\n"
					+ "                    <div class=\"col-sm-9\"></div>\r\n"
					+ "                    <div class=\"col-sm-3\">\r\n"
					+ "                        <a class=\"search-button\" href=\"settings?user=1\">Back to Settings</a>"
					+ "                    </div>\r\n"
					+ "                </div>\r\n"
					+ "            </div>\r\n"
					+ "       </div>\r\n"
					+ "    </div>\r\n"
					+ "    \r\n"
					+ "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\" crossorigin=\"anonymous\"></script>\r\n"
					+ "</body>\r\n"
					+ "</html>");
		}
		else {
			// Display error
			out.println("<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "<head>\r\n"
					+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\r\n"
					+ "\r\n"
					+ "	<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n"
					+ "	<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n"
					+ "	<link href=\"https://fonts.googleapis.com/css2?family=Lobster&display=swap\" rel=\"stylesheet\">\r\n"
					+ "\r\n"
					+ "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n"
					+ "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n"
					+ "    <link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap\" rel=\"stylesheet\"> \r\n"
					+ "\r\n"
					+ "	<link rel=\"stylesheet\" type=\"text/css\" href=\"page.css\">\r\n"
					+ "	\r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <title>Document</title>\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "    <div class=\"header\">\r\n"
					+ "        <div>\r\n"
					+ "            <a class=\"header-logo\" href=\"searchDetails.html\">Trojan Trade</a>\r\n"
					+ "        </div>\r\n"
					+ "        <div class=\"header-info\">\r\n"
					+ "            <div class=\"header-info-1\">\r\n"
					+ "                <a href=\"searchDetails.html\">Home</a>\r\n"
					+ "            </div>\r\n"
					+ "            <div class=\"header-info-2\">\r\n"
					+ "                <a href=\"settings?item=122\">Login/Register</a>\r\n"
					+ "            </div> \r\n"
					+ "            <div class=\"header-info-2\">\r\n"
					+ "                <a href=\"settings?item=122\">Settings</a>\r\n"
					+ "            </div> \r\n"
					+ "        </div>\r\n"
					+ "    </div>\r\n"
					+ "    \r\n"
					+ "    <div class=\"main\">\r\n"
					+ "       <div class=\"main-content\">\r\n"
					+ "            <div class=\"search container\">\r\n"
					+ "                <div class=\"form-group row results\">\r\n"
					+ "                    <div class=\"col-sm-12 title\">\r\n"
					+ "                        Settings\r\n"
					+ "                    </div>\r\n"
					+ "                </div>\r\n"
					+ "                <div class=\"form-group row settings\">\r\n"
					+ "                    <div class=\"col-sm-12 error\">\r\n"
					+ "                    	    *" + error + "\r\n"
					+ "                    </div>\r\n"
					+ "                </div>\r\n"
					+ "                <div class=\"form-group row settings\">\r\n"
					+ "                    <div class=\"col-sm-9\"></div>\r\n"
					+ "                    <div class=\"col-sm-3\">\r\n"
					+ "                        <a class=\"search-button\" href=\"settings?user=1\">Back to Settings</a>"
					+ "                    </div>\r\n"
					+ "                </div>\r\n"
					+ "            </div>\r\n"
					+ "       </div>\r\n"
					+ "    </div>\r\n"
					+ "    \r\n"
					+ "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\" crossorigin=\"anonymous\"></script>\r\n"
					+ "</body>\r\n"
					+ "</html>");
		}
	}

  public void destroy() {
     // do nothing.
  }
}
