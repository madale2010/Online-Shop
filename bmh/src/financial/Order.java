package financial;

import java.sql.Timestamp;
import java.util.List;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.FmfDetails;
import com.paypal.api.payments.Links;

import shippo.RateDetails;
import utils.Constants;

/**
 * 
 */
public class Order extends Constants {
	//Order details
	private String orderId="";
	private Timestamp orderDate;
	private String orderStatus;
	private String orderType="";
	private String paymentStatus;
	private String paymentMethod="";
	private String paymentType="";
	private Timestamp paymentDate;
	private String couponCode="";
	private String couponDetails="";
	private int totalItems=0;
	private String currency="";
	private String shippingCost="";
	private String salesTax="";
	private String subTotal="";
	private String orderTotal="";
	private String cardProcessingFees="";
	private String transactionFee="";
	private String inPersonDiscount="";
	private String inPersonLocation="";
	
	//Customer details
	private String buyerUserId="";
	private String buyerName="";
	private String fullName="";
	private String firstName="";
	private String lastName="";
	private String phone="";
	private String email="";
	
	//Shipping details
	private String shippingMethod="";
	private String shipToName;
	private Timestamp shipDate;
	private String shipStreet1="";
	private String shipStreet2="";
	private String shipCity="";
	private String shipState="";
	private String shipZipCode="";
	private String shipCountry="";
	private String shippingStatus="";
	private RateDetails rateDetails;
	/**
	 * Amount being collected.
	 */
	private Amount amount;

	/**
	 * specifies payment mode of the transaction
	 */
	private String paymentMode;

	/**
	 * State of the order transaction.
	 */
	private String state;

	/**
	 * Reason code for the transaction state being Pending or Reversed. Only supported when the `payment_method` is set to `paypal`.
	 */
	private String reasonCode;

	/**
	 * The level of seller protection in force for the transaction.
	 */
	private String protectionEligibility;

	/**
	 * The kind of seller protection in force for the transaction. This property is returned only when the `protection_eligibility` property is set to `ELIGIBLE`or `PARTIALLY_ELIGIBLE`. Only supported when the `payment_method` is set to `paypal`. Allowed values:<br> `ITEM_NOT_RECEIVED_ELIGIBLE`- Sellers are protected against claims for items not received.<br> `UNAUTHORIZED_PAYMENT_ELIGIBLE`- Sellers are protected against claims for unauthorized payments.<br> One or both of the allowed values can be returned.
	 */
	private String protectionEligibilityType;

	/**
	 * ID of the Payment resource that this transaction is based on.
	 */
	private String parentPayment;

	/**
	 * Fraud Management Filter (FMF) details applied for the payment that could result in accept/deny/pending action.
	 */
	private FmfDetails fmfDetails;

	/**
	 * Time the resource was created in UTC ISO8601 format.
	 */
	private String createTime;

	/**
	 * Time the resource was last updated in UTC ISO8601 format.
	 */
	private String updateTime;

	private List<Links> links;

	/**
	 * Constructor
	 */
	public Order(){}

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the orderDate
	 */
	public Timestamp getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the totalItems
	 */
	public int getTotalItems() {
		return totalItems;
	}

	/**
	 * @param totalItems the totalItems to set
	 */
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	/**
	 * @return the buyerUserId
	 */
	public String getBuyerUserId() {
		return buyerUserId;
	}

	/**
	 * @param buyerUserId the buyerUserId to set
	 */
	public void setBuyerUserId(String buyerUserId) {
		this.buyerUserId = buyerUserId;
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

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * @return the shipCity
	 */
	public String getShipCity() {
		return shipCity;
	}

	/**
	 * @param shipCity the shipCity to set
	 */
	public void setShipCity(String shipCity) {
		this.shipCity = shipCity;
	}

	/**
	 * @return the shipState
	 */
	public String getShipState() {
		return shipState;
	}

	/**
	 * @param shipState the shipState to set
	 */
	public void setShipState(String shipState) {
		this.shipState = shipState;
	}

	/**
	 * @return the shipZipcode
	 */
	public String getShipZipCode() {
		return shipZipCode;
	}

	/**
	 * @param shipZipcode the shipZipcode to set
	 */
	public void setShipZipCode(String shipZipcode) {
		this.shipZipCode = shipZipcode;
	}

	/**
	 * @return the shipCountry
	 */
	public String getShipCountry() {
		return shipCountry;
	}

	/**
	 * @param shipCountry the shipCountry to set
	 */
	public void setShipCountry(String shipCountry) {
		this.shipCountry = shipCountry;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the couponCode
	 */
	public String getCouponCode() {
		return couponCode;
	}

	/**
	 * @param couponCode the couponCode to set
	 */
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	/**
	 * @return the couponDetails
	 */
	public String getCouponDetails() {
		return couponDetails;
	}

	/**
	 * @param couponDetails the couponDetails to set
	 */
	public void setCouponDetails(String couponDetails) {
		this.couponDetails = couponDetails;
	}

	/**
	 * @return the salesTax
	 */
	public String getSalesTax() {
		return salesTax;
	}

	/**
	 * @param salesTax the salesTax to set
	 */
	public void setSalesTax(String salesTax) {
		this.salesTax = salesTax;
	}

	/**
	 * @return the orderTotal
	 */
	public String getOrderTotal() {
		return orderTotal;
	}

	/**
	 * @param orderTotal the orderTotal to set
	 */
	public void setOrderTotal(String orderTotal) {
		this.orderTotal = orderTotal;
	}

	/**
	 * @return the cardProcessingFees
	 */
	public String getCardProcessingFees() {
		return cardProcessingFees;
	}

	/**
	 * @param cardProcessingFees the cardProcessingFees to set
	 */
	public void setCardProcessingFees(String cardProcessingFees) {
		this.cardProcessingFees = cardProcessingFees;
	}

	/**
	 * @return the buyer
	 */
	public String getBuyerName() {
		return buyerName;
	}

	/**
	 * @param buyer the buyer to set
	 */
	public void setBuyerName(String buyer) {
		this.buyerName = buyer;
	}

	/**
	 * @return the orderType
	 */
	public String getOrderType() {
		return orderType;
	}

	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * @return the inPersonDiscount
	 */
	public String getInPersonDiscount() {
		return inPersonDiscount;
	}

	/**
	 * @param inPersonDiscount the inPersonDiscount to set
	 */
	public void setInPersonDiscount(String inPersonDiscount) {
		this.inPersonDiscount = inPersonDiscount;
	}

	/**
	 * @return the inPersonLocation
	 */
	public String getInPersonLocation() {
		return inPersonLocation;
	}

	/**
	 * @param inPersonLocation the inPersonLocation to set
	 */
	public void setInPersonLocation(String inPersonLocation) {
		this.inPersonLocation = inPersonLocation;
	}

	/**
	 * @return the amount
	 */
	public Amount getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}

	/**
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the reasonCode
	 */
	public String getReasonCode() {
		return reasonCode;
	}

	/**
	 * @param reasonCode the reasonCode to set
	 */
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	/**
	 * @return the protectionEligibility
	 */
	public String getProtectionEligibility() {
		return protectionEligibility;
	}

	/**
	 * @param protectionEligibility the protectionEligibility to set
	 */
	public void setProtectionEligibility(String protectionEligibility) {
		this.protectionEligibility = protectionEligibility;
	}

	/**
	 * @return the protectionEligibilityType
	 */
	public String getProtectionEligibilityType() {
		return protectionEligibilityType;
	}

	/**
	 * @param protectionEligibilityType the protectionEligibilityType to set
	 */
	public void setProtectionEligibilityType(String protectionEligibilityType) {
		this.protectionEligibilityType = protectionEligibilityType;
	}

	/**
	 * @return the parentPayment
	 */
	public String getParentPayment() {
		return parentPayment;
	}

	/**
	 * @param parentPayment the parentPayment to set
	 */
	public void setParentPayment(String parentPayment) {
		this.parentPayment = parentPayment;
	}

	/**
	 * @return the fmfDetails
	 */
	public FmfDetails getFmfDetails() {
		return fmfDetails;
	}

	/**
	 * @param fmfDetails the fmfDetails to set
	 */
	public void setFmfDetails(FmfDetails fmfDetails) {
		this.fmfDetails = fmfDetails;
	}

	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the links
	 */
	public List<Links> getLinks() {
		return links;
	}

	/**
	 * @param links the links to set
	 */
	public void setLinks(List<Links> links) {
		this.links = links;
	}


	/**
	 * @return the shippingMethod
	 */
	public String getShippingMethod() {
		return shippingMethod;
	}

	/**
	 * @param shippingMethod the shippingMethod to set
	 */
	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	/**
	 * @return the transactionFee
	 */
	public String getTransactionFee() {
		return transactionFee;
	}

	/**
	 * @param transactionFee the transactionFee to set
	 */
	public void setTransactionFee(String transactionFee) {
		this.transactionFee = transactionFee;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the subTotal
	 */
	public String getSubTotal() {
		return subTotal;
	}

	/**
	 * @param subTotal the subTotal to set
	 */
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * @return the payamentStatus
	 */
	public String getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * @param paymentStatus 
	 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	/**
	 * @return the shipToName
	 */
	public String getShipToName() {
		return shipToName;
	}

	/**
	 * @param shipToName the shipToName to set
	 */
	public void setShipToName(String shipToName) {
		this.shipToName = shipToName;
	}

	/**
	 * @return the shippingCost
	 */
	public String getShippingCost() {
		return shippingCost;
	}

	/**
	 * @param shippingCost the shippingCost to set
	 */
	public void setShippingCost(String shippingCost) {
		this.shippingCost = shippingCost;
	}

	/**
	 * @return the shipDate
	 */
	public Timestamp getShipDate() {
		return shipDate;
	}

	/**
	 * @param shipDate the shipDate to set
	 */
	public void setShipDate(Timestamp shipDate) {
		this.shipDate = shipDate;
	}

	/**
	 * @return the shipStreet2
	 */
	public String getShipStreet2() {
		return shipStreet2;
	}

	/**
	 * @param shipStreet2 the shipStreet2 to set
	 */
	public void setShipStreet2(String shipStreet2) {
		this.shipStreet2 = shipStreet2;
	}

	/**
	 * @return the shipStreet1
	 */
	public String getShipStreet1() {
		return shipStreet1;
	}

	/**
	 * @param shipStreet1 the shipStreet1 to set
	 */
	public void setShipStreet1(String shipStreet1) {
		this.shipStreet1 = shipStreet1;
	}

	/**
	 * @return the paymentDate
	 */
	public Timestamp getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * @return the shippingStatus
	 */
	public String getShippingStatus() {
		return shippingStatus;
	}

	/**
	 * @param shippingStatus the shippingStatus to set
	 */
	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	/**
	 * @return the ratedetails
	 */
	public RateDetails getRateDetails() {
		return rateDetails;
	}

	/**
	 * @param ratedetails the ratedetails to set
	 */
	//@XmlElement(name="ratedetails")
	public void setRateDetails(RateDetails ratedetails) {
		this.rateDetails = ratedetails;
	}

	 
 }
