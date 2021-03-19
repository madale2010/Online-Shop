package customer;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.Product;
import utils.Constants;
import utils.CustomException;
import utils.FileHelper;

/**
 * Servlet implementation class ShopHome
 */

public class ShopHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopHome() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		 System.out.println(FileHelper.getMethodName());
		String path = "/images/Products/";
		Connection conn = null;
		ArrayList<Product> featuredProductList = new ArrayList<Product>();
		try {
			conn = Constants.DB.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT PROD.PID, PROD.PROD_NAME, PROD.PROD_DESC, PROD.PRICE, PROD.QUANTITY, PROD.SHORT_DESC, PROD.CUR_CODE, IMG.IMAGE_NAME1 "+
														 "FROM BMH_PRODUCTS PROD "+
														 "LEFT JOIN BMH_IMAGES_NAMES IMG "+
														 "ON PROD.PID=IMG.PID "
														 + "WHERE FEATURED='Y' ");
			ResultSet resultSet = ps.executeQuery();
		    while (resultSet.next()) {
		    	Product product = new Product();
		    	product.setPid(resultSet.getString("PID"));
		    	product.setPrice(resultSet.getString("PRICE"));
		    	product.setQuantity(resultSet.getString("QUANTITY"));
		    	product.setShortDescription(resultSet.getString("SHORT_DESC"));
		    	product.setCurCode(resultSet.getString("CUR_CODE"));
		    	product.setImageURL(path+resultSet.getString("IMAGE_NAME1"));
		    	featuredProductList.add(product);
	        }
		    resultSet.close();
		    ps.close();
		   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			CustomException.processError(e);
			e.printStackTrace();
			request.setAttribute(e.getClass().getName(), e);
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		request.setAttribute("productList", featuredProductList);
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
	    rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
