package payments;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.Sale;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import financial.Customer;
import financial.InventoryHelper;
import management.InventoryHandler;
import paypal.utils.GenerateAccessToken;
import utils.Cart;
import utils.CustomException;

/**
 * Servlet implementation class ProcessCheckoutRequest
 */
//@WebServlet("/ProcessCheckoutRequest")
public class ProcessCheckoutRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessCheckoutRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> sdkConfig = new HashMap<String, String>();
		sdkConfig.put("mode", "live");
		String accessToken=GenerateAccessToken.getAccessToken();
		APIContext apiContext = new APIContext(accessToken);
		apiContext.setConfigurationMap(sdkConfig);
	
		String paymentId=request.getParameter("paymentId");
		apiContext = new APIContext(accessToken);
		apiContext.setConfigurationMap(sdkConfig);
		Payment payment;
		System.out.println("DEBUG   request for process checkout");
		try {
			Cart cart = request.getSession().getAttribute("cart")!=null ? (Cart) request.getSession().getAttribute("cart") :null;

			payment = Payment.get(accessToken, paymentId);
			
			PaymentExecution paymentExecute = new PaymentExecution();
			paymentExecute.setPayerId(payment.getPayer().getPayerInfo().getPayerId());
			Payment resultPayment = payment.execute(apiContext, paymentExecute);
			String saleId = resultPayment.getTransactions().get(0).getRelatedResources().get(0).getSale().getId();
			//Insert sale record
			Sale sale = Sale.get(accessToken,saleId);
			InventoryHelper.insertNewSale(sale);
			
			//Populate needed information for customer
			Customer customer = new Customer();
			InventoryHandler invObject = new InventoryHandler(payment, sale);
			if(cart!=null){
				invObject.setRateDetails(cart.getRateDetails());
			}
			invObject.updateProductQuantity();
			invObject.generateOrder();
			customer=invObject.updateCustomer(customer);
			InventoryHelper.insertNewOrder(invObject.getOrder());
			request.setAttribute("customer", customer);
			request.getSession().removeAttribute("cartTotal");
		} catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			CustomException.processError(e);
			request.setAttribute(e.getClass().getName(), e);
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
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
