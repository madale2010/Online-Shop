package paypal.utils;

public class BillingHandler {
	/*
	 * ================================================================== PayPal
	 * Express Checkout - MARK FLOW : START SNIPPET
	 * ===================================================================
	 */
	// IMPORTANT NOTE: Please import Class paypalfunctions if not in the same
	// package level.
	// import paypalfunctions;

//	public String createBilling(String paymentOption, String paymentProcessorSelected, String pAmount){
//		/*
//		'------------------------------------
//		' The paymentAmount is the total value of 
//		' the shopping cart, that was set 
//		' earlier in a session variable 
//		' by the shopping cart page
//		'------------------------------------
//		*/
//		String paymentAmount = pAmount;
//	if (paymentOption == "PayPal")
//	{
//		/*
//		'------------------------------------
//		' The currencyCodeType and paymentType 
//		' are set to the selections made on the Integration Assistant 
//		'------------------------------------
//		*/
//
//		String currencyCodeType = "USD";
//		String paymentType = "Sale";
//
//		/*
//		'------------------------------------
//		' When you integrate this code 
//		' set the variables below with 
//		' shipping address details 
//		' entered by the user on the 
//		' Shipping page.
//		'------------------------------------
//		*/
//
//		String shipToName 			= "<<ShiptoName>>";
//		String shipToStreet 		= "<<ShipToStreet>>";
//		String shipToStreet2 		= "<<ShipToStreet2>>"; //'Leave it blank if there is no value
//		String shipToCity 			= "<<ShipToCity>>";
//		String shipToState 			= "<<ShipToState>>";
//		String shipToCountryCode 	= "<<ShipToCountryCode>>"; //' Please refer to the PayPal country codes in the API documentation
//		String shipToZip 			= "<<ShipToZip>>";
//		String phoneNum 			= "<<PhoneNumber>>";
//
//		/*
//		'------------------------------------
//		' Calls the SetExpressCheckout API call
//		'
//		' The CallMarkExpressCheckout function is defined in the file PayPalFunctions.asp,
//		' it is included at the top of this file.
//		'-------------------------------------------------
//		*/
//		PaypalFunctions ppf = new PaypalFunctions();
//		HashMap nvp = CallShortcutExpressCheckout(paymentAmount, Constants.RETURN_URL, Constants.CANCEL_URL,
//				shipToName, shipToStreet, shipToStreet2, shipToCity, shipToState,
//										shipToCountryCode, shipToZip, phoneNum );
//										
//		String strAck = nvp.get("ACK").toString();
//		if(strAck !=null && !(strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning")))
//		{
//			return nvp.get("TOKEN").toString());
//			//' Redirect to paypal.com
//			//ReDirectURL( nvp.get("TOKEN").toString());
//		}
//		else
//		{  
//			// Display a user friendly Error on the page using any of the following error information returned by PayPal
//			
//			String ErrorCode = nvp.get("L_ERRORCODE0").toString();
//			String ErrorShortMsg = nvp.get("L_SHORTMESSAGE0").toString();
//			String ErrorLongMsg = nvp.get("L_LONGMESSAGE0").toString();
//			String ErrorSeverityCode = nvp.get("L_SEVERITYCODE0").toString();
//			return "";
//		}
//	}
//	else if ((paymentOption.equals("Visa") || paymentOption.equals("MasterCard") || paymentOption.equals("Amex") || paymentOption.equals("Discover"))
//			 && ( paymentProcessorSelected == "PayPal Direct Payment")){
//
//
//		/*
//		'------------------------------------
//		' The paymentType that was selected earlier 
//		'------------------------------------
//		*/
//		String paymentType = "Sale";
//		
//		/*
//		' Set these values based on what was selected by the user on the Billing page Html form
//		*/
//		
//		String creditCardType 		= "<<Visa/MasterCard/Amex/Discover>>"; //' Set this to one of the acceptable values (Visa/MasterCard/Amex/Discover) match it to what was selected on your Billing page
//		String creditCardNumber 	= "<<CC number>>"; // ' Set this to the string entered as the credit card number on the Billing page
//		String expDate 				= "<<Expiry Date>>"; // ' Set this to the credit card expiry date entered on the Billing page
//		String cvv2 				= "<<cvv2>>"; // ' Set this to the CVV2 string entered on the Billing page 
//		String firstName 			= "<<firstName>>"; // ' Set this to the customer's first name that was entered on the Billing page 
//		String lastName 			= "<<lastName>>"; // ' Set this to the customer's last name that was entered on the Billing page 
//		String street 				= "<<street>>"; // ' Set this to the customer's street address that was entered on the Billing page 
//		String city 				= "<<city>>"; // ' Set this to the customer's city that was entered on the Billing page 
//		String state 				= "<<state>>"; // ' Set this to the customer's state that was entered on the Billing page 
//		String zip 					= "<<zip>>"; // ' Set this to the zip code of the customer's address that was entered on the Billing page 
//		String countryCode 			= "<<PayPal Country Code>>"; // ' Set this to the PayPal code for the Country of the customer's address that was entered on the Billing page 
//		String currencyCode 		= "<<PayPal Currency Code>>"; // ' Set this to the PayPal code for the Currency used by the customer 
//		
//		/*	
//		'------------------------------------------------
//		' Calls the DoDirectPayment API call
//		'
//		' The DirectPayment function is defined in PayPalFunctions.jsp 
//		' included at the top of this file.
//		'-------------------------------------------------
//		*/
//		nvp = DirectPayment( paymentType, paymentAmount, creditCardType, creditCardNumber,
//								expDate, cvv2, firstName, lastName, street, city, state, zip, 
//								countryCode, currencyCode ); 
//
//		String strAck = nvp.get("ACK").toString();
//		if(strAck ==null || strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning") )
//		{
//			// Display a user friendly Error on the page using any of the following error information returned by PayPal
//			String ErrorCode = nvp.get("L_ERRORCODE0").toString();
//			String ErrorShortMsg = nvp.get("L_SHORTMESSAGE0").toString();
//			String ErrorLongMsg = nvp.get("L_LONGMESSAGE0").toString();
//			String ErrorSeverityCode = nvp.get("L_SEVERITYCODE0").toString();
//			return null;
//		}
//	}
//	}
}
