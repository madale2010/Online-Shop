package payments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.paypal.api.payments.Address;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.CreditCard;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Order;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.RelatedResources;
import com.paypal.api.payments.Sale;
import com.paypal.api.payments.Transaction;

import paypal.utils.PaypalFunctions;
import product.Product;
import utils.Cart;
import utils.Constants.Urls;

public class PaymentHelper {
	/**
	 * Creates the API call to paypal to setup express checkout payment.
	 * @param paymentAmount
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException
	 */
	public static HashMap<?, ?> setExpressCheckout(String paymentAmount, HttpServletResponse response, HttpSession session) throws IOException{
		PaypalFunctions ppf = new PaypalFunctions();
		HashMap<?, ?> nvp = ppf.CallShortcutExpressCheckout(paymentAmount, Urls.RETURN_URL, Urls.CANCEL_URL);
		String strAck = nvp.get("ACK").toString();
		if (strAck != null && strAck.equalsIgnoreCase("Success")) {
			session.setAttribute("token", nvp.get("TOKEN").toString());
			// ' Redirect to paypal.com
			String redirectURL = response.encodeRedirectURL(nvp.get("TOKEN").toString());
			response.sendRedirect(Urls.PAYPAL_CHECKOUT_TEST+redirectURL);
		}
		return nvp;
	}
	/**
	 * Get express checkout details from paypal to complete the order.
	 * @param token
	 * @return
	 */
	public static HashMap<?, ?> getExpressCheckoutDetails(String token){
			PaypalFunctions ppf = new PaypalFunctions();
			HashMap<?, ?> nvp = ppf.GetShippingDetails(token);
			String strAck = nvp.get("ACK").toString();
			if (strAck != null	&& (strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning"))) {
//				Customer customerOrder = new Customer();
				//customerOrder.createNewCustomer(nvp);
			}
			return nvp;
	}
	/**
	 * Method that confirms and process payment with PayPal API
	 * @param token
	 * @param payerId
	 * @param finalPaymentAmount
	 * @param serverName
	 * @return
	 */
	public static HashMap<?, ?> processExpressCheckout(String token,String payerId,String finalPaymentAmount, String serverName){
		PaypalFunctions ppf = new PaypalFunctions();
		HashMap<?, ?> nvp = ppf.ConfirmPayment(token, payerId, finalPaymentAmount, serverName);
		String strAck = nvp.get("ACK").toString();
		if(strAck !=null && !(strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning")))
		{
			/*
			'********************************************************************************************************************
			'
			' THE PARTNER SHOULD SAVE THE KEY TRANSACTION RELATED INFORMATION LIKE 
			'                    transactionId & orderTime 
			'  IN THEIR OWN  DATABASE
			' AND THE REST OF THE INFORMATION CAN BE USED TO UNDERSTAND THE STATUS OF THE PAYMENT 
			'
			'********************************************************************************************************************
			*/
//
//			String transactionId	= nvp.get("PAYMENTINFO_0_TRANSACTIONID").toString(); // ' Unique transaction ID of the payment. Note:  If the PaymentAction of the request was Authorization or Order, this value is your AuthorizationID for use with the Authorization & Capture APIs. 
//			String transactionType 	= nvp.get("PAYMENTINFO_0_TRANSACTIONTYPE").toString(); //' The type of transaction Possible values: l  cart l  express-checkout 
//			String paymentType		= nvp.get("PAYMENTINFO_0_PAYMENTTYPE").toString();  //' Indicates whether the payment is instant or delayed. Possible values: l  none l  echeck l  instant 
//			String orderTime 		= nvp.get("PAYMENTINFO_0_ORDERTIME").toString();  //' Time/date stamp of payment
//			String amt				= nvp.get("PAYMENTINFO_0_AMT").toString();  //' The final amount charged, including any shipping and taxes from your Merchant Profile.
//			String currencyCode		= nvp.get("PAYMENTINFO_0_CURRENCYCODE").toString();  //' A three-character currency code for one of the currencies listed in PayPay-Supported Transactional Currencies. Default: USD. 
//			String feeAmt			= nvp.get("PAYMENTINFO_0_FEEAMT").toString();  //' PayPal fee amount charged for the transaction
//			String settleAmt		= nvp.get("PAYMENTINFO_0_SETTLEAMT").toString();  //' Amount deposited in your PayPal account after a currency conversion.
//			String taxAmt			= nvp.get("PAYMENTINFO_0_TAXAMT").toString();  //' Tax charged on the transaction.
//			String exchangeRate		= nvp.get("PAYMENTINFO_0_EXCHANGERATE").toString();  //' Exchange rate if a currency conversion occurred. Relevant only if your are billing in their non-primary currency. If the customer chooses to pay with a currency other than the non-primary currency, the conversion occurs in the customers account.
//			
			/*
			' Status of the payment: 
					'Completed: The payment has been completed, and the funds have been added successfully to your account balance.
					'Pending: The payment is pending. See the PendingReason element for more information. 
			*/
			
			//String paymentStatus	= nvp.get("PAYMENTINFO_0_PAYMENTSTATUS").toString(); 

			/*
			'The reason the payment is pending:
			'  none: No pending reason 
			'  address: The payment is pending because your customer did not include a confirmed shipping address and your Payment Receiving Preferences is set such that you want to manually accept or deny each of these payments. To change your preference, go to the Preferences section of your Profile. 
			'  echeck: The payment is pending because it was made by an eCheck that has not yet cleared. 
			'  intl: The payment is pending because you hold a non-U.S. account and do not have a withdrawal mechanism. You must manually accept or deny this payment from your Account Overview. 		
			'  multi-currency: You do not have a balance in the currency sent, and you do not have your Payment Receiving Preferences set to automatically convert and accept this payment. You must manually accept or deny this payment. 
			'  verify: The payment is pending because you are not yet verified. You must verify your account before you can accept this payment. 
			'  other: The payment is pending for a reason other than those listed above. For more information, contact PayPal customer service. 
			*/
			
			//String pendingReason	= nvp.get("PAYMENTINFO_0_PENDINGREASON").toString();  

			/*
			'The reason for a reversal if TransactionType is reversal:
			'  none: No reason code 
			'  chargeback: A reversal has occurred on this transaction due to a chargeback by your customer. 
			'  guarantee: A reversal has occurred on this transaction due to your customer triggering a money-back guarantee. 
			'  buyer-complaint: A reversal has occurred on this transaction due to a complaint about the transaction from your customer. 
			'  refund: A reversal has occurred on this transaction because you have given the customer a refund. 
			'  other: A reversal has occurred on this transaction due to a reason not listed above. 
			*/
			
			//String reasonCode		= nvp.get("PAYMENTINFO_0_REASONCODE").toString();   
		}
		return nvp;
	}
	/**
	 * Create Billing address.
	 * @param req
	 * @return
	 */
	public static Address createBillingAddress(HttpServletRequest req){
		Address billingAddress = new Address();
		String city =req.getParameter("")!=null? req.getParameter(""):"";
		String countryCode =req.getParameter("")!=null? req.getParameter(""):"";
		String addressLine =req.getParameter("")!=null? req.getParameter(""):"";
		String zip =req.getParameter("")!=null? req.getParameter(""):"";
		String state =req.getParameter("")!=null? req.getParameter(""):"";
		billingAddress.setCity(city);
		billingAddress.setCountryCode(countryCode);
		billingAddress.setLine1(addressLine);
		billingAddress.setPostalCode(zip);
		billingAddress.setState(state);
		return billingAddress;
		
	}
	public static CreditCard createCreditCard(HttpServletRequest req, Address billingAddress){
		CreditCard creditCard = new CreditCard();
		creditCard.setBillingAddress(billingAddress);
		String cvv2 =req.getParameter("")!=null? req.getParameter(""):"";
		String expireMonth =req.getParameter("")!=null? req.getParameter(""):"";
		String expireYear =req.getParameter("")!=null? req.getParameter(""):"";
		String firstName =req.getParameter("")!=null? req.getParameter(""):"";
		String lastName =req.getParameter("")!=null? req.getParameter(""):"";
		String ccNumber =req.getParameter("")!=null? req.getParameter(""):"";
		
		creditCard.setCvv2(Integer.valueOf(cvv2));
		creditCard.setExpireMonth(Integer.valueOf(expireMonth));
		creditCard.setExpireYear(Integer.valueOf(expireYear));
		creditCard.setFirstName(firstName);
		creditCard.setLastName(lastName);
		creditCard.setNumber(ccNumber);
		creditCard.setType("mastercard");
		return creditCard;
	}
	public static Details createShippingDetails(Cart cart){
		Details details = new Details(); 
		cart.calculateTotals();
		String shipping =cart.getShippingTotal().toString();
		String subTotal =cart.getSubTotal().toString();
		String tax =cart.getTax().toString();
		details.setShipping(shipping);
		details.setSubtotal(subTotal);
		details.setTax(tax);
		return details;
	}
	public static Amount createAmount(Cart cart){
		// ###Amount
		// Let's you specify a payment amount.
		Amount amount = new Amount();
		String currency ="USD";
		cart.calculateTotals();
		String total =cart.getTotal().toString();
		amount.setCurrency(currency);
		// Total must be equal to sum of shipping, tax and subtotal.
		amount.setTotal(total);
		return amount;
	}
	public static ItemList createItemList(Cart cart){
		// ### Items
		
		
		ItemList itemList = new ItemList();
		List<Item> items = new ArrayList<Item>();
		
		for(Product cartItem: cart.getCartItems()) {
			Item item = new Item();
			item.setSku(cartItem.getPid());
			item.setName(cartItem.getName());
			item.setQuantity(cartItem.getQuantity());
			item.setCurrency("USD");
			item.setPrice(cartItem.getPrice().replace("$", ""));
			items.add(item);
			
		}
		itemList.setItems(items);
		
		return itemList;
	}
	public static Payment loadPaymentDetails(List<Transaction> transactions){
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");
		// A Payment Resource; create one using 
		// the above types and intent as 'sale' 
		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setTransactions(transactions);
		
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl(Urls.CANCEL_URL);
		redirectUrls.setReturnUrl(Urls.RETURN_URL);
		payment.setRedirectUrls(redirectUrls);
		
		return payment;
	}
	public static List<Transaction> createSingleTransationList(Amount amount, ItemList itemList){
		Transaction transaction = new Transaction();
		transaction.setDescription("Blue Mountain Holly Order");
		transaction.setItemList(itemList);
		transaction.setAmount(amount);
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);
		return transactions;
	}
	/**
	 * 
	 * @param payment
	 * @return
	 */
	public static Sale getSale(Payment payment){
		List<Transaction> transactionList = payment.getTransactions();
		Sale sale=null;
		for(Transaction transaction: transactionList){
			List<RelatedResources> resources = transaction.getRelatedResources();
			for(RelatedResources source: resources){
				sale = source.getSale();
				
			}	
		}
		return sale;
	}
	public static Order getOrder(Payment payment){
		List<Transaction> transactionList = payment.getTransactions();
		Order order=null;
		for(Transaction transaction: transactionList){
			List<RelatedResources> resources = transaction.getRelatedResources();
			for(RelatedResources source: resources){
				order = source.getOrder();
			}	
		}
		return order;
	}
}
