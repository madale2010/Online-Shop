package management;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paypal.api.payments.Sale;

import financial.InventoryHelper;
import financial.Order;
import utils.Alert;
import utils.Constants;
import utils.CustomException;
import utils.CustomersStatistics;
import utils.Constants.SqlQueries;

/**
 * Servlet implementation class ManagementLoader
 */
public class ManagementLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagementLoader() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action")!=null ? request.getParameter("action"):"";
		if(action.contains("fix")){
			String alertId = action.substring(action.lastIndexOf("_")+1,action.length());
			//Redirect page to what we have on file
			String alertName=request.getParameter("alertName_"+alertId);
			String alertLink=request.getParameter("alertLink_"+alertId);
			if(alertName.equalsIgnoreCase("NEWORDER")){
				//redirect to that order page
				Order order=InventoryHelper.getOrderById(alertLink);
				request.setAttribute("order", order);
				//response.sendRedirect("editOrder.jsp");
				request.getRequestDispatcher("editOrder.jsp").forward(request, response);
				//Last thing is update alert.
				Alert alert=Alert.getAlert(alertId);
				alert.setAlertType("CLOSED");
				alert.updateAlert();
			}
		} 
		//Load normal page landing
		else {
			try {
				setAlerts(request);
				setOrders(request);
			} catch (SQLException e) {
				CustomException.processError(e);
				request.setAttribute(e.getClass().getName(), e);
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
			
			CustomersStatistics stats = new CustomersStatistics();
			setDailyStats(request, stats);
			setMonthlyStats(request, stats);
		}
	}

	private void setOrders(HttpServletRequest request) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet rs = Constants.DB.runPreparedStatement(SqlQueries.BMH_SALES_SELECT);
		ArrayList<Sale> saleList = new ArrayList<Sale>();
		while (rs.next()) {
			Sale sale = InventoryHelper.getSaleFromDB(rs);
			saleList.add(sale);
		}
		rs.close();
		request.setAttribute("saleList", saleList);
	}

	private void setAlerts(HttpServletRequest request) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet rs = Constants.DB.runPreparedStatement(SqlQueries.BMH_ALERTS_SELECT);
		ArrayList<Alert> alertsList = new ArrayList<Alert>();
		while (rs.next()) {
			Alert alert = new Alert();
			alert.setAlertId(rs.getInt("ALERT_ID"));
			alert.setAlertType(rs.getString("ALERT_TYPE"));
			alert.setAlertName(rs.getString("ALERT_NAME"));
			alert.setAlertDate(rs.getTimestamp("ALERT_DATE"));
			alert.setAlertDescription(rs.getString("ALERT_DESCRIPTION"));
			alert.setAlertLink(rs.getString("ALERT_LINK"));
			alertsList.add(alert);
		}
		rs.close();
		request.setAttribute("alertsList", alertsList);
	}

	private void setMonthlyStats(HttpServletRequest request, CustomersStatistics stats) {
		// TODO Auto-generated method stub
		request.setAttribute("totalDailySales", stats.getTotalSales());
		request.setAttribute("hitCounter", stats.getVisitCount());
		request.setAttribute("totalDailyComments", stats.getCommentCount());
		request.setAttribute("emailCount", "N/A");
		
	}

	private void setDailyStats(HttpServletRequest request, CustomersStatistics stats) {
		// TODO Auto-generated method stub
		request.setAttribute("totalMonthlySales", stats.getTotalSales());
		request.setAttribute("monthlyHitCounter", stats.getVisitCount());
		request.setAttribute("totalMonthlyComments", stats.getCommentCount());
		request.setAttribute("totalBlogs", "N/A");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
