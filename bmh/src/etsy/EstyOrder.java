package etsy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.Constants;

/**
 * 
 */
public class EstyOrder extends Constants {
	private String SALE_DATE ="";
	private String ORDER_ID="";
	private String ORDER_DATE="";
	private String TOTAL_ITEMS="";
	private String BUYER_USER_ID="";
	private String FULL_NAME="";
	private String FIRST_NAME="";
	private String LAST_NAME="";
	private int NUMBER_OF_ITEMS=0;
	private String PAYMENT_METHOD="";
	private String DATE_SHIPPED="";
	private String STREET_1="";
	private String STREET_2="";
	private String SHIP_CITY="";
	private String SHIP_STATE="";
	private String SHIP_ZIPCODE="";
	private String SHIP_COUNTRY="";
	private String CURRENCY="";
	private String ORDER_VALUE="";
	private String COUPON_CODE="";
	private String COUPON_DETAILS="";
	private String SHIPPING="";
	private String SALES_TAX="";
	private String ORDER_TOTAL="";
	private String STATUS="";
	private String CARD_PROCESSING_FEES="";
	private String ORDER_NET="";
	private String ADJUSTED_ORDER_TOTAL="";
	private String ADJUSTED_CARD_PROCESSING_FEES="";
	private String ADJUSTED_NET_ORDER_AMOUNT="";
	private String BUYER="";
	private String ORDER_TYPE="";
	private String PAYMENT_TYPE="";
	private String INPERSON_DISCOUNT="";
	private String INPERSON_LOCATION="";

	public EstyOrder(){
	 
 }

	/**
	 * @return the sALE_DATE
	 */
	public String getSaleDate() {
		return SALE_DATE;
	}

	/**
	 * @param sALE_DATE the sALE_DATE to set
	 */
	public void setSaleDate(String date) {
		SALE_DATE = date;
	}

	/**
	 * @return the oRDER_ID
	 */
	public String getOrderId() {
		return ORDER_ID;
	}

	/**
	 * @param oRDER_ID the oRDER_ID to set
	 */
	public void setOrderId(String id) {
		ORDER_ID = id;
	}

	/**
	 * @return the bUYER_USER_ID
	 */
	public String getBuyerId() {
		return BUYER_USER_ID;
	}

	/**
	 * @param bUYER_USER_ID the bUYER_USER_ID to set
	 */
	public void setBuyerId(String buyerId) {
		BUYER_USER_ID = buyerId;
	}

	/**
	 * @return the fULL_NAME
	 */
	public String getFullName() {
		return FULL_NAME;
	}

	/**
	 * @param fULL_NAME the fULL_NAME to set
	 */
	public void setFullName(String fullName) {
		FULL_NAME = fullName;
	}

	/**
	 * @return the fIRST_NAME
	 */
	public String getFName() {
		return FIRST_NAME;
	}

	/**
	 * @param fIRST_NAME the fIRST_NAME to set
	 */
	public void setFName(String fName) {
		FIRST_NAME = fName;
	}

	/**
	 * @return the lAST_NAME
	 */
	public String getLName() {
		return LAST_NAME;
	}

	/**
	 * @param lAST_NAME the lAST_NAME to set
	 */
	public void setLName(String lName) {
		LAST_NAME = lName;
	}

	/**
	 * @return the nUMBER_OF_ITEMS
	 */
	public int getNumItems() {
		return NUMBER_OF_ITEMS;
	}

	/**
	 * @param nUMBER_OF_ITEMS the nUMBER_OF_ITEMS to set
	 */
	public void setNumItems(int items) {
		NUMBER_OF_ITEMS = items;
	}

	/**
	 * @return the pAYMENT_METHOD
	 */
	public String getPaymentMethod() {
		return PAYMENT_METHOD;
	}

	/**
	 * @param pAYMENT_METHOD the pAYMENT_METHOD to set
	 */
	public void setPaymentMethod(String payMethod) {
		PAYMENT_METHOD = payMethod;
	}

	/**
	 * @return the dATE_SHIPPED
	 */
	public String getShipDate() {
		return DATE_SHIPPED;
	}

	/**
	 * @param dATE_SHIPPED the dATE_SHIPPED to set
	 */
	public void setShipDate(String shipDate) {
		DATE_SHIPPED = shipDate;
	}

	/**
	 * @return the sTREET_1
	 */
	public String getStreet1() {
		return STREET_1;
	}

	/**
	 * @param sTREET_1 the sTREET_1 to set
	 */
	public void setStreet1(String street) {
		STREET_1 = street;
	}

	/**
	 * @return the sTREET_2
	 */
	public String getStreet2() {
		return STREET_2;
	}

	/**
	 * @param sTREET_2 the sTREET_2 to set
	 */
	public void setStreet2(String street2) {
		STREET_2 = street2;
	}

	/**
	 * @return the sHIP_CITY
	 */
	public String getShipCity() {
		return SHIP_CITY;
	}

	/**
	 * @param sHIP_CITY the sHIP_CITY to set
	 */
	public void setShipCity(String shipCity) {
		SHIP_CITY = shipCity;
	}

	/**
	 * @return the sHIP_STATE
	 */
	public String getShipState() {
		return SHIP_STATE;
	}

	/**
	 * @param sHIP_STATE the sHIP_STATE to set
	 */
	public void setShipState(String shipState) {
		SHIP_STATE = shipState;
	}

	/**
	 * @return the sHIP_ZIPCODE
	 */
	public String getShipZip() {
		return SHIP_ZIPCODE;
	}

	/**
	 * @param sHIP_ZIPCODE the sHIP_ZIPCODE to set
	 */
	public void setShipZip(String shipZip) {
		SHIP_ZIPCODE = shipZip;
	}

	/**
	 * @return the sHIP_COUNTRY
	 */
	public String getShipCountry() {
		return SHIP_COUNTRY;
	}

	/**
	 * @param sHIP_COUNTRY the sHIP_COUNTRY to set
	 */
	public void setShipCountry(String shipCountry) {
		SHIP_COUNTRY = shipCountry;
	}

	/**
	 * @return the cURRENCY
	 */
	public String getCurrency() {
		return CURRENCY;
	}

	/**
	 * @param cURRENCY the cURRENCY to set
	 */
	public void setCurrency(String cur) {
		CURRENCY = cur;
	}

	/**
	 * @return the oRDER_VALUE
	 */
	public String getOrderValue() {
		return ORDER_VALUE;
	}

	/**
	 * @param oRDER_VALUE the oRDER_VALUE to set
	 */
	public void setOrderValue(String orderValue) {
		ORDER_VALUE = orderValue;
	}

	/**
	 * @return the cOUPON_CODE
	 */
	public String getCouponCode() {
		return COUPON_CODE;
	}

	/**
	 * @param cOUPON_CODE the cOUPON_CODE to set
	 */
	public void setCouponCode(String couponCode) {
		COUPON_CODE = couponCode;
	}

	/**
	 * @return the cOUPON_DETAILS
	 */
	public String getCouponDetails() {
		return COUPON_DETAILS;
	}

	/**
	 * @param cOUPON_DETAILS the cOUPON_DETAILS to set
	 */
	public void setCouponDetails(String couponDetails) {
		COUPON_DETAILS = couponDetails;
	}

	/**
	 * @return the sHIPPING
	 */
	public String getShipping() {
		return SHIPPING;
	}

	/**
	 * @param sHIPPING the sHIPPING to set
	 */
	public void setShipping(String shipping) {
		SHIPPING = shipping;
	}

	/**
	 * @return the sALES_TAX
	 */
	public String getSalesTax() {
		return SALES_TAX;
	}

	/**
	 * @param sALES_TAX the sALES_TAX to set
	 */
	public void setSalesTax(String sTax) {
		SALES_TAX = sTax;
	}

	/**
	 * @return the oRDER_TOTAL
	 */
	public String getOrderTotal() {
		return ORDER_TOTAL;
	}

	/**
	 * @param oRDER_TOTAL the oRDER_TOTAL to set
	 */
	public void setOrderTotal(String oTotal) {
		ORDER_TOTAL = oTotal;
	}

	/**
	 * @return the sTATUS
	 */
	public String getStatus() {
		return STATUS;
	}

	/**
	 * @param sTATUS the sTATUS to set
	 */
	public void setStatus(String status) {
		STATUS = status;
	}

	/**
	 * @return the cARD_PROCESSING_FEES
	 */
	public String getCardFees() {
		return CARD_PROCESSING_FEES;
	}

	/**
	 * @param cARD_PROCESSING_FEES the cARD_PROCESSING_FEES to set
	 */
	public void setCardFees(String cardFees) {
		CARD_PROCESSING_FEES = cardFees;
	}

	/**
	 * @return the oRDER_NET
	 */
	public String getNetAmount() {
		return ORDER_NET;
	}

	/**
	 * @param oRDER_NET the oRDER_NET to set
	 */
	public void setNetAmount(String netAmt) {
		ORDER_NET = netAmt;
	}

	/**
	 * @return the aDJUSTED_ORDER_TOTAL
	 */
	public String getAdjOrderTotal() {
		return ADJUSTED_ORDER_TOTAL;
	}

	/**
	 * @param aDJUSTED_ORDER_TOTAL the aDJUSTED_ORDER_TOTAL to set
	 */
	public void setAdjOrderTotal(String adjOrderAmt) {
		ADJUSTED_ORDER_TOTAL = adjOrderAmt;
	}

	/**
	 * @return the aDJUSTED_CARD_PROCESSING_FEES
	 */
	public String getAdjCardFees() {
		return ADJUSTED_CARD_PROCESSING_FEES;
	}

	/**
	 * @param aDJUSTED_CARD_PROCESSING_FEES the aDJUSTED_CARD_PROCESSING_FEES to set
	 */
	public void setAdjCardFees(String adjCardFees) {
		ADJUSTED_CARD_PROCESSING_FEES = adjCardFees;
	}

	/**
	 * @return the aDJUSTED_NET_ORDER_AMOUNT
	 */
	public String getAdjNetOrderAmt() {
		return ADJUSTED_NET_ORDER_AMOUNT;
	}

	/**
	 * @param aDJUSTED_NET_ORDER_AMOUNT the aDJUSTED_NET_ORDER_AMOUNT to set
	 */
	public void setAdjNetOrderAmt(String ajdNetAmt) {
		ADJUSTED_NET_ORDER_AMOUNT = ajdNetAmt;
	}

	/**
	 * @return the bUYER
	 */
	public String getBuyer() {
		return BUYER;
	}

	/**
	 * @param bUYER the bUYER to set
	 */
	public void setBuyer(String buyer) {
		BUYER = buyer;
	}

	/**
	 * @return the oRDER_TYPE
	 */
	public String getOrderType() {
		return ORDER_TYPE;
	}

	/**
	 * @param oRDER_TYPE the oRDER_TYPE to set
	 */
	public void setOrderType(String orderType) {
		ORDER_TYPE = orderType;
	}

	/**
	 * @return the pAYMENT_TYPE
	 */
	public String getPaymentType() {
		return PAYMENT_TYPE;
	}

	/**
	 * @param pAYMENT_TYPE the pAYMENT_TYPE to set
	 */
	public void setPaymentType(String payType) {
		PAYMENT_TYPE = payType;
	}

	/**
	 * @return the iNPERSON_DISCOUNT
	 */
	public String getPerDiscount() {
		return INPERSON_DISCOUNT;
	}

	/**
	 * @param iNPERSON_DISCOUNT the iNPERSON_DISCOUNT to set
	 */
	public void setPerDiscount(String dis) {
		INPERSON_DISCOUNT = dis;
	}

	/**
	 * @return the iNPERSON_LOCATION
	 */
	public String getPerLocation() {
		return INPERSON_LOCATION;
	}

	/**
	 * @param iNPERSON_LOCATION the iNPERSON_LOCATION to set
	 */
	public void setPerLocation(String loc) {
		INPERSON_LOCATION = loc;
	}

	public void insertNewOrder() {
		Connection conn = DB.getConnection();
		String sql = "INSERT INTO BMH_ORDERS (ORDER_ID,ORDER_DATE,BUYER_NAME,NUMBER_OF_ITEMS,SHIPPING_DATE,FULL_NAME,FIRST_NAME,LAST_NAME,COUPON_CODE,COUPON_DETAILS,ORDER_TOTAL,PAYMENT_TYPE) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, this.ORDER_ID);
			statement.setString(2, this.ORDER_DATE);
			statement.setString(3, this.BUYER);
			statement.setString(4, this.TOTAL_ITEMS);
			statement.setString(5, this.DATE_SHIPPED);
			statement.setString(6,this.FULL_NAME);
			statement.setString(7,this.FIRST_NAME);
			statement.setString(8,this.LAST_NAME);
			statement.setString(9,this.COUPON_CODE);
			statement.setString(10,this.COUPON_DETAILS);
			statement.setString(11,this.ORDER_TOTAL);
			statement.setString(12,this.PAYMENT_TYPE);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
			    System.out.println("A new order was inserted successfully!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
 
}
