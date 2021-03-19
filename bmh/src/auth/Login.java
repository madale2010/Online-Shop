package auth;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBManager;

/**
 * Servlet implementation class Login
 */

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager db;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		db = (DBManager) getServletContext().getAttribute("db");
		//Validate and see if they are admin or customer
		String user=request.getParameter("username").toString();
		String password=request.getParameter("password").toString();
		String permissions = "";
		try {
			Connection conn = db.getConnection();
			String sqlStatement = "select USER_RIGHTS from BMH_ADMIN_USERS where USER_NAME=? and USER_PASS=?";
			
			PreparedStatement ps = conn.prepareStatement(sqlStatement);
			ps.setString(1, user);
			ps.setString(2, password);
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				 permissions = resultSet.getString("USER_RIGHTS");
			 }
			resultSet.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(permissions.isEmpty()){
			request.getSession().setAttribute("message", "Invalid user or password!");
			request.setAttribute("message", "Invalid user or password!");
			request.getSession().setAttribute("authUser", false);
			response.sendRedirect(request.getHeader("referer"));
		}
		else if(permissions.equals("admin") || permissions.equals("owner")){
			//redirect to client management pages
			request.getSession().setAttribute("authUser", true);
			response.sendRedirect("manager/management.jsp");
		}
		else if(permissions.equals("customer")){
			//redirect to customer management pages
			request.getSession().setAttribute("authUser", true);
			response.sendRedirect("customer/customerHome.jsp"); 
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
