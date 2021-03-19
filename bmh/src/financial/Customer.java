package financial;

import com.paypal.api.payments.Sale;
import com.paypal.api.payments.ShippingAddress;

import utils.Cart;

/**
 * 
 */
public class Customer {
	private Cart cart;
	private String payerId;
	private String email; 
	private String payerStatus; 
	private String salutation; 
	private String firstName; 
	private String middleName; 
	private String lastName; 
	private String suffix; 
	private String cntryCode;
	private String business; 
	private String shipToName; 
	private String shipToStreet;
	private String shipToStreet2; 
	private String shipToCity; 
	private String shipToState; 
	private String shipToCntryCode; 
	private String shipToZip;
	private String addressStatus;
	private String invoiceNumber;
	private String saleId;
	private String phoneNumber;
	private String orderAmount;
	private String orderDate;
	private String orderTaxAmount;
	private String shippingOption;
	private String fullName;
	private Order order;
	private Sale sale;
	private ShippingAddress addressInfo;
	
	
	/**
	 * Method: getPayerId
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getPayerId() {
		return payerId;
	}
	/**
	 * Method: setPayerId
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param payerId
	 * 
	 * void
	 */
	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}
	/**
	 * Method: getEmail
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Method: setEmail
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param email
	 * 
	 * void
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Method: getPayerStatus
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getPayerStatus() {
		return payerStatus;
	}
	/**
	 * Method: setPayerStatus
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param payerStatus
	 * 
	 * void
	 */
	public void setPayerStatus(String payerStatus) {
		this.payerStatus = payerStatus;
	}
	/**
	 * Method: getSalutation
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getSalutation() {
		return salutation;
	}
	/**
	 * Method: setSalutation
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param salutation
	 * 
	 * void
	 */
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	/**
	 * Method: getFirstName
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Method: setFirstName
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param firstName
	 * 
	 * void
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * Method: getMiddleName
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * Method: setMiddleName
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param middleName
	 * 
	 * void
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * Method: getLastName
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Method: setLastName
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param lastName
	 * 
	 * void
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * Method: getSuffix
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getSuffix() {
		return suffix;
	}
	/**
	 * Method: setSuffix
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param suffix
	 * 
	 * void
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	/**
	 * Method: getCntryCode
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getCntryCode() {
		return cntryCode;
	}
	/**
	 * Method: setCntryCode
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param cntryCode
	 * 
	 * void
	 */
	public void setCntryCode(String cntryCode) {
		this.cntryCode = cntryCode;
	}
	/**
	 * Method: getBusiness
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getBusiness() {
		return business;
	}
	/**
	 * Method: setBusiness
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param business
	 * 
	 * void
	 */
	public void setBusiness(String business) {
		this.business = business;
	}
	/**
	 * Method: getShipToName
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getShipToName() {
		return shipToName;
	}
	/**
	 * Method: setShipToName
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param shipToName
	 * 
	 * void
	 */
	public void setShipToName(String shipToName) {
		this.shipToName = shipToName;
	}
	/**
	 * Method: getShipToStreet
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getShipToStreet() {
		return shipToStreet;
	}
	/**
	 * Method: setShipToStreet
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param shipToStreet
	 * 
	 * void
	 */
	public void setShipToStreet(String shipToStreet) {
		this.shipToStreet = shipToStreet;
	}
	/**
	 * Method: getShipToStreet2
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getShipToStreet2() {
		return shipToStreet2;
	}
	/**
	 * Method: setShipToStreet2
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param shipToStreet2
	 * 
	 * void
	 */
	public void setShipToStreet2(String shipToStreet2) {
		this.shipToStreet2 = shipToStreet2;
	}
	/**
	 * Method: getShipToCity
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getShipToCity() {
		return shipToCity;
	}
	/**
	 * Method: setShipToCity
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param shipToCity
	 * 
	 * void
	 */
	public void setShipToCity(String shipToCity) {
		this.shipToCity = shipToCity;
	}
	/**
	 * Method: getShipToState
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getShipToState() {
		return shipToState;
	}
	/**
	 * Method: setShipToState
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param shipToState
	 * 
	 * void
	 */
	public void setShipToState(String shipToState) {
		this.shipToState = shipToState;
	}
	/**
	 * Method: getShipToCntryCode
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getShipToCntryCode() {
		return shipToCntryCode;
	}
	/**
	 * Method: setShipToCntryCode
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param shipToCntryCode
	 * 
	 * void
	 */
	public void setShipToCntryCode(String shipToCntryCode) {
		this.shipToCntryCode = shipToCntryCode;
	}
	/**
	 * Method: getShipToZip
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getShipToZip() {
		return shipToZip;
	}
	/**
	 * Method: setShipToZip
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param shipToZip
	 * 
	 * void
	 */
	public void setShipToZip(String shipToZip) {
		this.shipToZip = shipToZip;
	}
	/**
	 * Method: getAddressStatus
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getAddressStatus() {
		return addressStatus;
	}
	/**
	 * Method: setAddressStatus
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param addressStatus
	 * 
	 * void
	 */
	public void setAddressStatus(String addressStatus) {
		this.addressStatus = addressStatus;
	}
	/**
	 * Method: getInvoiceNumber
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	/**
	 * Method: setInvoiceNumber
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param invoiceNumber
	 * 
	 * void
	 */
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	/**
	 * Method: getPhoneNumber
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * Method: setPhoneNumber
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param phoneNumber
	 * 
	 * void
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * Method: getOrderAmount
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getOrderAmount() {
		return orderAmount;
	}
	/**
	 * Method: setOrderAmount
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param orderAmount
	 * 
	 * void
	 */
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	/**
	 * Method: getOrderTaxAmount
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getOrderTaxAmount() {
		return orderTaxAmount;
	}
	/**
	 * Method: setOrderTaxAmount
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param orderTaxAmount
	 * 
	 * void
	 */
	public void setOrderTaxAmount(String orderTaxAmount) {
		this.orderTaxAmount = orderTaxAmount;
	}
	/**
	 * Method: getShippingOption
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getShippingOption() {
		return shippingOption;
	}
	/**
	 * Method: setShippingOption
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param shippingOption
	 * 
	 * void
	 */
	public void setShippingOption(String shippingOption) {
		this.shippingOption = shippingOption;
	}
	/**
	 * Method: getCart
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * Cart
	 */
	public Cart getCart() {
		return cart;
	}
	/**
	 * Method: setCart
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param cart
	 * 
	 * void
	 */
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	/**
	 * Method: getSaleId
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getSaleId() {
		return saleId;
	}
	/**
	 * Method: setSaleId
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param saleId
	 * 
	 * void
	 */
	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}
	/**
	 * Method: getOrderDate
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getOrderDate() {
		return orderDate;
	}
	/**
	 * Method: setOrderDate
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param orderDate
	 * 
	 * void
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	/**
	 * @return the addressInfo
	 */
	public ShippingAddress getAddressInfo() {
		return addressInfo;
	}
	/**
	 * @param addressInfo the addressInfo to set
	 */
	public void setAddressInfo(ShippingAddress addressInfo) {
		this.addressInfo = addressInfo;
	}
	/**
	 * @return the sale
	 */
	public Sale getSale() {
		return sale;
	}
	/**
	 * @param sale the sale to set
	 */
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
