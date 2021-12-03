

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class TrojanServlet
 */
public class TrojanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TrojanServlet() {
        // TODO Auto-generated constructor stub
    }
    
    protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	String sellername = request.getParameter("sellername");
		String listingname = request.getParameter("listingname");
		String condition = request.getParameter("condition");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String locations = request.getParameter("location");
		String category = request.getParameter("category");

		HttpSession session = request.getSession();
		String error = "";
		PrintWriter out = response.getWriter();
		
		if (sellername == "") {
			error = "Please enter your name.";
		} else if (listingname == null || listingname.trim().length() == 0) {
			error = "What is the item name?";
		} else if (locations == null || locations.trim().length() == 0) {
			error = "Where are you located?";
		} else if (category == null || category.trim().length() == 0) {
			error = "What is the item's category?";
		} else if (condition == null || condition.trim().length() == 0) {
			error = "What would you rate the coniditon of this item (out of 5)?";
		} else if (price == null || price.trim().length() == 0) {
			error = "How  much is this item?";
		} else  (description == null || description.trim().length() == 0) {
			error = "Please give a description of this item.";
		} 
		
		out.print(error);
		out.flush();
		out.close();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
