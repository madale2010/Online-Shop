package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBManager;
import financial.InventoryHelper;
import financial.Order;
import utils.Constants;
import utils.CustomException;

/**
 * Servlet implementation class OrderHandler
 */
public class OrderHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		db = (DBManager) getServletContext().getAttribute("db");
		String orderId = request.getParameter("orderId")!=null ? request.getParameter("orderId"): "";
		if(!orderId.isEmpty()){
			Order order=InventoryHelper.getOrderById(orderId);
			request.setAttribute("order", order);
			request.getRequestDispatcher("manager/editOrder.jsp").forward(request, response);
		} else {
			ArrayList<Order> orderList = new ArrayList<Order>();
			ResultSet resultSet = db.runPreparedStatement(Constants.SqlQueries.BMH_ORDERS_SELECT);
		    try {
				while (resultSet.next()) {
					Order order = InventoryHelper.getOrderFromDB(resultSet);
					orderList.add(order);
				}
				resultSet.close();
			} catch (SQLException e) {
				CustomException.processError(e);
			}
		    request.setAttribute("orderList", orderList);
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
