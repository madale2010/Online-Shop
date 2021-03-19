package paypal.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import financial.Customer;
import payments.PaymentHelper;
import utils.Constants.Urls;

/**
 * Servlet implementation class OrderReview
 */

public class ClassicOrderReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClassicOrderReview() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String token = (String) request.getParameter("token");
		Customer customer = PaypalClassicHelper.createNewCustomer(PaymentHelper.getExpressCheckoutDetails(token));
		String payerId = customer.getPayerId();
		String server = Urls.SERVER_NAME;
		String finalPaymentAmount = customer.getOrderAmount();
		
		PaymentHelper.processExpressCheckout(token, payerId, finalPaymentAmount,server);
		request.setAttribute("customer", customer);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)		throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
