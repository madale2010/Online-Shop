package payments;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import paypal.utils.GenerateAccessToken;
import utils.Cart;
import utils.CustomException;

/**
 * Servlet implementation class LoadCheckoutProcess
 */
//@WebServlet("/LoadCheckoutProcess")
public class LoadCheckoutProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadCheckoutProcess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = session.getAttribute("cart")!=null ? (Cart) session.getAttribute("cart") :null;
		Map<String, String> sdkConfig = new HashMap<String, String>();
		sdkConfig.put("mode", "live");
		String accessToken=GenerateAccessToken.getAccessToken();
		APIContext apiContext = new APIContext(accessToken);
		apiContext.setConfigurationMap(sdkConfig);
		
		Details detail = PaymentHelper.createShippingDetails(cart);
		Amount amount = PaymentHelper.createAmount(cart);
		amount.setDetails(detail);
		ItemList cartList = PaymentHelper.createItemList(cart);
		List<Transaction> transaction = PaymentHelper.createSingleTransationList(amount, cartList);
		Payment payment = PaymentHelper.loadPaymentDetails(transaction);
		
		try {
			
			payment = payment.create(apiContext);
			String redirectURL = payment.getLinks().get(1).getHref();
			System.out.println("DEBUG   request for cart checkout");
			response.sendRedirect(redirectURL);
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
