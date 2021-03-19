package customer;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import paypal.utils.PaypalFunctions;
import utils.FileHelper;
import utils.Constants.Urls;

/**
 * 
 */
public class ExpressCheckout extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(FileHelper.getMethodName());
		HttpSession session = request.getSession();
		/*
		 * '------------------------------------------- ' The paymentAmount is
		 * the total value of ' the shopping cart, that was set ' earlier in a
		 * session variable ' by the shopping cart page
		 * '-------------------------------------------
		 */
		String paymentAmount = (String) request.getParameter("Payment_Amount");
		paymentAmount=paymentAmount.replace("$", "");
		/*
		 * '------------------------------------ ' Calls the SetExpressCheckout
		 * API call ' ' The CallShortcutExpressCheckout function is defined in
		 * the file PayPalFunctions.asp, ' it is included at the top of this
		 * file. '-------------------------------------------------
		 */
		PaypalFunctions ppf = new PaypalFunctions();
		HashMap<?, ?> nvp = ppf.CallShortcutExpressCheckout(paymentAmount, Urls.RETURN_URL, Urls.CANCEL_URL);
		
		String strAck = nvp.get("ACK").toString();
		if (strAck != null && strAck.equalsIgnoreCase("Success")) {
			session.setAttribute("token", nvp.get("TOKEN").toString());
			// ' Redirect to paypal.com
			String redirectURL = response.encodeRedirectURL(nvp.get("TOKEN").toString());
			response.sendRedirect(Urls.PAYPAL_CHECKOUT_TEST+redirectURL);
			
		} else {
			// Display a user friendly Error on the page using any of the
			// following error information returned by PayPal
			response.sendError(500);
//			String ErrorCode = nvp.get("L_ERRORCODE0").toString();
//			String ErrorShortMsg = nvp.get("L_SHORTMESSAGE0").toString();
//			String ErrorLongMsg = nvp.get("L_LONGMESSAGE0").toString();
//			String ErrorSeverityCode = nvp.get("L_SEVERITYCODE0").toString();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}