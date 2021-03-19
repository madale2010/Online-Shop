package management;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paypal.api.payments.Sale;

import database.DBManager;
import etsy.EstyOrder;
import financial.InventoryHelper;
import google.GoogleStat;
import reports.History;
import utils.Constants.SqlQueries;

/**
 * Servlet implementation class Orders
 */
public class GenerateReports extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager db;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateReports() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		db = (DBManager) getServletContext().getAttribute("db");
		String reportType=request.getRequestURI();
		reportType=reportType.substring(reportType.lastIndexOf("/")+1,reportType.lastIndexOf("."));
		//Case statement here so we just populate object needed
        switch (reportType) {
        case "sales":  
        	request.setAttribute("saleList", populateSales());
            break;
        case "orders":  
        	request.setAttribute("orderList", populateOrder());
            break;
        case "googleStats": 
        	request.setAttribute("googleList", populateGoogle());
            break;
        case "history":  
        	request.setAttribute("historyList", populateHistory());
            break;
        }
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private ArrayList<History> populateHistory() {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<GoogleStat> populateGoogle() {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<EstyOrder> populateOrder() {
		try {
			Connection conn = db.getConnection();
			String sqlStatement = "SELECT ORDER_ID, SALE_DATE, FULL_NAME, DATE_SHIPPED, ORDER_VALUE, SHIPPING, SALES_TAX, ORDER_TOTAL, CARD_PROCESSING_FEES, ORDER_NET "+
								  "FROM BMH_REPORT_ETSY_ORDERS "+
								  "ORDER BY SALE_DATE ";
			PreparedStatement ps = conn.prepareStatement(sqlStatement);
			ResultSet resultSet = ps.executeQuery();
			ArrayList<EstyOrder> orderList = new ArrayList<EstyOrder>();
		    while (resultSet.next()) {
		    	EstyOrder item = new EstyOrder();
		    	item.setOrderId(resultSet.getString("ORDER_ID"));
		    	//item.setSaleDate(resultSet.getString("SALE_DATE"));
		    	item.setFName(resultSet.getString("FULL_NAME"));
		    	//item.setShipDate(resultSet.getString("DATE_SHIPPED"));
		    	item.setOrderValue(resultSet.getString("ORDER_VALUE"));
		    	item.setShipping(resultSet.getString("SHIPPING"));
		    	item.setSalesTax(resultSet.getString("SALES_TAX"));
		    	item.setOrderTotal(resultSet.getString("ORDER_TOTAL"));
		    	item.setCardFees(resultSet.getString("CARD_PROCESSING_FEES"));
		    	item.setNetAmount(resultSet.getString("ORDER_NET"));
		    	orderList.add(item);
		    }
		    resultSet.close();
		    ps.close();
		   return orderList;
		  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private ArrayList<Sale> populateSales() {
		ResultSet rs=db.runPreparedStatement(SqlQueries.BMH_SALES_SELECT);
		ArrayList<Sale> temp = new ArrayList<Sale>();
		try {
			while(rs.next()){
				temp.add(InventoryHelper.getSaleFromDB(rs));
			}
			rs.close();
			return temp;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
