package finalProject;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;


@WebServlet("/settings")
public class settings extends HttpServlet {

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
		
		String db = "jdbc:mysql://localhost:3306/trojantrade";
		String user = "root";
		String pwd = "Kbwoo100!sql";
		String sql = "SELECT * FROM user WHERE user.id =" + userId;
		
		String nickname = null;
		String username = null;
		String password = null;
		String location = null;
		
		try (Connection conn = DriverManager.getConnection(db, user, pwd);
		      Statement st = conn.createStatement();
			  ResultSet rs = st.executeQuery(sql);) {
			if (rs.next()) {
				nickname = rs.getString("nickname");
				username = rs.getString("username");
				password = rs.getString("password");
				location = rs.getString("location");
			}
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
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
				+ "				<form action=\"verify\" method=\"GET\">\r\n"	
				+ "                <div class=\"form-group row results\">\r\n"
				+ "                    <div class=\"col-sm-12 title\">\r\n"
				+ "                        Settings\r\n"
				+ "                    </div>\r\n"
				+ "                </div>\r\n"
				+ "                <div class=\"form-group row settings\">\r\n"
				+ "                    <div class=\"col-sm-2\">Nickname: </div>\r\n"
				+ "                    <div class=\"col-sm-9\">\r\n"
				+ "                        <input type=\"hidden\" class=\"form-control\" id=\"user\" name=\"user\" value=\"" + userId +"\">\r\n"
				+ "                        <input type=\"text\" class=\"form-control\" id=\"nickname\" name=\"nickname\" value=\"" + nickname + "\">\r\n"
				+ "                    </div>\r\n"
				+ "                </div> \r\n"
				+ "                <div class=\"form-group row settings\">\r\n"
				+ "                    <div class=\"col-sm-2\">Username: </div>\r\n"
				+ "                    <div class=\"col-sm-9\">\r\n"
				+ "                        <input type=\"text\" class=\"form-control\" id=\"username\" name=\"username\" value=\"" + username + "\">\r\n"
				+ "                    </div>\r\n"
				+ "                </div> \r\n"
				+ "                <div class=\"form-group row settings\">\r\n"
				+ "                    <div class=\"col-sm-2\">New Password: </div>\r\n"
				+ "                    <div class=\"col-sm-9\">\r\n"
				+ "                        <input type=\"text\" class=\"form-control\" id=\"password\" name=\"password\" value=\"\">\r\n"
				+ "                    </div>\r\n"
				+ "                </div> \r\n"
				+ "                <div class=\"form-group row settings\">\r\n"
				+ "                    <div class=\"col-sm-2\">Location: </div>\r\n"
				+ "                    <div class=\"col-sm-9\">\r\n"
				+ "                        <input type=\"text\" class=\"form-control\" id=\"location\" name=\"location\" value=\"" + location + "\">\r\n"
				+ "                    </div>\r\n"
				+ "                </div> \r\n"
				+ "                <div class=\"form-group row settings\">\r\n"
				+ "                    <div class=\"col-sm-2\">Verify Old Password: </div>\r\n"
				+ "                    <div class=\"col-sm-9\">\r\n"
				+ "                        <input type=\"text\" class=\"form-control\" id=\"verifyPass\" name=\"verifyPass\">\r\n"
				+ "                    </div>\r\n"
				+ "                </div> \r\n"
				+ "                <div class=\"form-group row settings\">\r\n"
				+ "                    <div class=\"col-sm-9\"></div>\r\n"
				+ "                    <div class=\"col-sm-3\">\r\n"
				+ "                    		<button type=\"submit\" class=\"search-button\">\r\n"
				+ "                         	<a onclick=\"return confirm('Are you sure you want to change your settings?')\">\r\n"
				+ "                             	Edit Settings\r\n"
				+ "                             </a>\r\n"
				+ "                         </button>"
				+ "                    </div>\r\n"
				+ "                </div> \r\n"
				+ "				</form>"
				+ "            </div>\r\n"
				+ "       </div>\r\n"
				+ "    </div>\r\n"
				+ "    \r\n"
				+ "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\" crossorigin=\"anonymous\"></script>\r\n"
				+ "</body>\r\n"
				+ "</html>");
	}

   public void destroy() {
      // do nothing.
   }
}
