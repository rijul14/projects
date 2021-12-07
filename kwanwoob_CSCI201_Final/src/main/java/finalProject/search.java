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


@WebServlet("/search")
public class search extends HttpServlet {

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
		
		String item = request.getParameter("item");
		
		String db = "jdbc:mysql://localhost:3306/trojantrade";
		String user = "root";
		String pwd = "Kbwoo100!sql";
		String sql = "SELECT * FROM item WHERE item.id =" + item;
		
		String title = null;
		String image = null;
		String time = null;
		String price = null;
		String location = null;
		String description = null;
		String category = null;
		
		try (Connection conn = DriverManager.getConnection(db, user, pwd);
		      Statement st = conn.createStatement();
			  ResultSet rs = st.executeQuery(sql);) {
			if (rs.next()) {
				title = rs.getString("title");
				image = rs.getString("image_url");
				time = rs.getString("time");
				price = rs.getString("price");
				location = rs.getString("location");
				description = rs.getString("description");
				category = rs.getString("category");
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
				+ "                <div class=\"form-group row results\">\r\n"
				+ "                    <div class=\"col-sm-12 title\">\r\n"
				+ "                        " + title + "\r\n"
				+ "                    </div>\r\n"
				+ "                </div>\r\n"
				+ "                <div class=\"form-group row settings\">\r\n"
				+ "                    <div class=\"col-sm-5\">\r\n"
				+ "                        <img src=\"" + image + "\">\r\n"
				+ "                    </div>\r\n"
				+ "                    <div class=\"col-sm-7 info\">\r\n"
				+ "                        <div>Time: " + time + "</div>\r\n"
				+ "                        <div>Price: " + price + "</div>\r\n"
				+ "                        <div>Location: " + location + "</div>\r\n"
				+ "                        <div>Description: " + description + "</div>\r\n"
				+ "                        <div>Category: " + category + "</div>\r\n"
				+ "                    </div> \r\n"
				+ "                </div>\r\n"
				+ "                <div class=\"form-group row settings\">\r\n"
				+ "                    <div class=\"col-sm-9\"></div>\r\n"
				+ "                    <div class=\"col-sm-3\">\r\n"
				+ "                        <a class=\"search-button\" href=\"search?item=" + item + "\">Back to Results</a>"
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

   public void destroy() {
      // do nothing.
   }
}
