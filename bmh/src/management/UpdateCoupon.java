package management;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBManager;
import utils.Coupon;
import utils.FileHelper;

/**
 * Servlet implementation class UpdateCoupon
 */
@MultipartConfig(fileSizeThreshold=1024*1024*10,    // 10 MB 
maxFileSize=1024*1024*50,          // 50 MB
maxRequestSize=1024*1024*100)  
public class UpdateCoupon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager db;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCoupon() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(FileHelper.getMethodName());
		db = (DBManager) getServletContext().getAttribute("db");
		String action = request.getParameter("action")!=null ?request.getParameter("action"):"";
		if(request.getParameter("action")!=null){
			if(action.equalsIgnoreCase("change")) {
				String couponId=request.getParameter("couponId");
				Coupon currentCoupon = Coupon.load(couponId);
				request.setAttribute("coupon", currentCoupon);
				request.setAttribute("couponId", currentCoupon.getId());
				request.getRequestDispatcher("updateSingleCoupon.jsp").forward(request, response);
			}
			else if(action.equalsIgnoreCase("save")) {
				Coupon saveCoupon = new Coupon();
				//TODO fill out coupon
				saveCoupon.insertToDatabase();
				
				//Last thing is redirect back to page listing
				response.sendRedirect("listCoupons.jsp");
			}
			else if(action.equalsIgnoreCase("delete")) {
				Coupon saveCoupon = new Coupon();
				String couponId = request.getParameter("couponId");
				saveCoupon.setId(couponId);
				saveCoupon.deleteFromDatabase();
				
				//Last thing is redirect back to page listing
				response.sendRedirect("listCoupons.jsp");
			}
			
		} else {
			//We are just listing all the documents from the db
			pullAllRecords(request);
		}
	}

	/**
	 * Method: pullAllRecords
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param request
	 * 
	 * void
	 */
	private void pullAllRecords(HttpServletRequest request) {
		Connection conn = db.getConnection();
		try {
			PreparedStatement statement = conn.prepareStatement("select * from BMH_COUPONS");
			ResultSet resultSet = statement.executeQuery();
			ArrayList<Coupon> couponList = new ArrayList<Coupon>();
			while (resultSet.next()) {
				couponList.add(new Coupon(resultSet));
			}
			resultSet.close();
			statement.close();
			request.setAttribute("couponList", couponList);
		} catch (Exception e) {
			e.printStackTrace();
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
