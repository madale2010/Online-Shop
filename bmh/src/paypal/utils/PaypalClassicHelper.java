package paypal.utils;

import java.util.HashMap;

import financial.Customer;

public class PaypalClassicHelper {
public static Customer createNewCustomer(HashMap<?, ?> nvp){
		Customer customer = new Customer();
		String email = nvp.get("EMAIL")!=null ? nvp.get("EMAIL").toString():""; 
		String payerId = nvp.get("PAYERID")!=null ? nvp.get("PAYERID").toString():""; 
		String payerStatus = nvp.get("PAYERSTATUS")!=null ? nvp.get("PAYERSTATUS").toString():""; 
		String salutation = nvp.get("SALUTATION")!=null ? nvp.get("SALUTATION").toString():""; 
		String firstName = nvp.get("FIRSTNAME")!=null ? nvp.get("FIRSTNAME").toString():""; 
		String middleName = nvp.get("MIDDLENAME")!=null ? nvp.get("MIDDLENAME").toString():""; 
		String lastName = nvp.get("LASTNAME")!=null ? nvp.get("LASTNAME").toString():""; 
		String suffix = nvp.get("SUFFIX")!=null ? nvp.get("SUFFIX").toString():""; 
		String cntryCode =  nvp.get("COUNTRYCODE")!=null ? nvp.get("COUNTRYCODE").toString():""; 
		String business = nvp.get("BUSINESS")!=null ? nvp.get("BUSINESS").toString():""; 
		String shipToName = nvp.get("PAYMENTREQUEST_0_SHIPTONAME")!=null ? nvp.get("PAYMENTREQUEST_0_SHIPTONAME").toString():""; 
		String shipToStreet = nvp.get("PAYMENTREQUEST_0_SHIPTOSTREET")!=null ? nvp.get("PAYMENTREQUEST_0_SHIPTOSTREET").toString():""; 
		String shipToStreet2 = nvp.get("PAYMENTREQUEST_0_SHIPTOSTREET2")!=null ? nvp.get("PAYMENTREQUEST_0_SHIPTOSTREET2").toString():""; 
		String shipToCity = nvp.get("PAYMENTREQUEST_0_SHIPTOCITY")!=null ? nvp.get("PAYMENTREQUEST_0_SHIPTOCITY").toString():""; 
		String shipToState =nvp.get("PAYMENTREQUEST_0_SHIPTOSTATE")!=null ? nvp.get("PAYMENTREQUEST_0_SHIPTOSTATE").toString():"";
		String shipToCntryCode = nvp.get("PAYMENTREQUEST_0_SHIPTOCOUNTRYCODE")!=null? nvp.get("PAYMENTREQUEST_0_SHIPTOCOUNTRYCODE").toString():""; 
		String shipToZip = nvp.get("PAYMENTREQUEST_0_SHIPTOZIP")!=null ? nvp.get("PAYMENTREQUEST_0_SHIPTOZIP").toString(): ""; 
		/*
		 * Status of street address.																		
		 */
		String addressStatus = nvp.get("ADDRESSSTATUS")!=null ? nvp.get("ADDRESSSTATUS").toString(): "";  
		/*
		 * Your own invoice or tracking number, as set by you in the 
		 * element of the same name in SetExpressCheckout request												
		 */
		String invoiceNumber = nvp.get("INVNUM")!=null ? nvp.get("INVNUM").toString(): "";  
		/*
		 * Payer's contact telephone number. Note: PayPal returns a 
		 * contact telephone number only if your Merchant account 
		 * profile settings require that the buyer enter one.													
		 */
		String phonNumber = nvp.get("PHONENUM")!=null ? nvp.get("PHONENUM").toString(): "";
		String amt = nvp.get("AMT")!=null ? nvp.get("AMT").toString(): "";
		String taxAmt= nvp.get("TAXAMT")!=null ? nvp.get("TAXAMT").toString():"";
		
		customer.setAddressStatus(addressStatus);
		
		customer.setBusiness(business);
		customer.setCntryCode(cntryCode);
		customer.setEmail(email);
		customer.setFirstName(firstName);
		customer.setInvoiceNumber(invoiceNumber);
		customer.setLastName(lastName);
		customer.setMiddleName(middleName);
		customer.setPayerId(payerId);
		customer.setPayerStatus(payerStatus);
		customer.setPhoneNumber(invoiceNumber);
		customer.setSalutation(salutation);
		customer.setShipToCity(shipToCity);
		customer.setShipToCntryCode(shipToCntryCode);
		customer.setShipToName(shipToName);
		customer.setShipToState(shipToState);
		customer.setShipToStreet(shipToStreet);
		customer.setShipToStreet2(shipToStreet2);
		customer.setShipToZip(shipToZip);
		customer.setSuffix(suffix);
		customer.setPhoneNumber(phonNumber);
		customer.setOrderAmount(amt);
		customer.setOrderTaxAmount(taxAmt);
		return customer;
	}
}
