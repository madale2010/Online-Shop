package financial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Currency;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Sale;

import product.Product;
import product.ProductHelper;
import shippo.RateDetails;
import utils.Cart;
import utils.Constants;
import utils.CustomException;

/**
 * 
 */
public class InventoryHelper extends Constants {
	
	/**
	 * Constructor
	 */
	public InventoryHelper(){
		
	}
	/** 
	 * Method will go back and create a new cart for back end use from paypal
	 * @param itemList
	 * @return cart
	 */
	public static Cart rebuildShoppingCart(ItemList itemList) {
		Cart rebuildCart = new Cart();
		for(Item item: itemList.getItems()){
			String pid=item.getSku();
			Product product = ProductHelper.loadSingleProduct(pid);
			product.setQuantity(item.getQuantity());
			rebuildCart.addToCart(product);
		}
		return rebuildCart;
	}
	/**
	 * Populates sale object with result set from back end DB
	 * results.
	 * @param rs
	 * @return sale
	 * @throws SQLException  
	 */
	public static Sale getSaleFromDB(ResultSet rs) throws SQLException {
		Sale sale = new Sale();
		sale.setCreateTime(rs.getTimestamp("CREATE_TIME").toString());
		sale.setId(rs.getString("SALE_ID"));
		sale.setAmount(new Amount("USD",String.valueOf(rs.getDouble("AMOUNT"))));
		sale.setBillingAgreementId(rs.getString("BILLING_AGREEMENT_ID"));
		sale.setCreateTime(rs.getTimestamp("CREATE_TIME").toString());
		sale.setExchangeRate(rs.getString("EXCHANGE_RATE"));
		//sale.setFmfDetails(rs.getString("FMF_DETAILS"));
		//sale.setLinks(rs.getString("LINKS"));
		sale.setParentPayment(rs.getString("PARENT_PAYMENT"));
		//sale.setPaymentHoldReasons(rs.getString("PAYMENT_HOLD_REASONS"));
		sale.setPaymentHoldStatus(rs.getString("PAYMENT_HOLD_STATUS"));
		sale.setPaymentMode(rs.getString("PAYMENT_MODE"));
		//sale.setProcessorResponse(rs.getString("PROCESSOR_RESPONSE"));
		sale.setProtectionEligibility(rs.getString("PROTECTION_ELIGIBILITY"));
		sale.setProtectionEligibilityType(rs.getString("PROTECTION_ELIGIBILITY_TYPE"));
		sale.setPurchaseUnitReferenceId(rs.getString("PURCHASE_UNIT_REF_ID"));
		sale.setReasonCode(rs.getString("REASON_CODE"));
		sale.setReceiptId(rs.getString("RECEIPT_ID"));
		sale.setReceivableAmount(new Currency("USD", String.valueOf(rs.getDouble("RECEIVABLE_AMOUNT"))));
		sale.setState(rs.getString("STATE"));
		sale.setTransactionFee(new Currency("USD", String.valueOf(rs.getDouble("TRANSACTION_FEE"))));
		sale.setUpdateTime(rs.getTimestamp("UPDATE_TIME").toString());
		return sale;
	}
	/**
	 * Populates back end table with the newly created sale information
	 * from paypal.
	 * @param sale
	 */
	public static void insertNewSale(Sale sale){
		//First make sure we have the basic's to insert sale record.
		Connection conn = DB.getConnection();

		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(SqlQueries.INSERT_SALES_FULL);
			String id=sale.getId()!=null?sale.getId(): "";
			String purchaseUnitReferenceId=sale.getPurchaseUnitReferenceId()!=null ? sale.getPurchaseUnitReferenceId(): "";
			String amount=sale.getAmount().getTotal()!=null ? sale.getAmount().getTotal() : "0";
			String parentPayment=sale.getParentPayment()!=null ? sale.getParentPayment(): "";
			String paymentMode=sale.getPaymentMode()!=null ? sale.getPaymentMode(): "";
			String state=sale.getState()!=null ? sale.getState() : "";
			String reasonCode=sale.getReasonCode()!=null ? sale.getReasonCode(): "";
			String protectionEligibility=sale.getProtectionEligibility()!=null ? sale.getProtectionEligibility(): "";
			String protectionEligibilityType=sale.getProtectionEligibilityType()!=null ? sale.getProtectionEligibilityType(): "";
			String clearingTime=sale.getClearingTime()!=null ? sale.getClearingTime(): "";
			String paymentHoldStatus=sale.getPaymentHoldStatus()!=null ? sale.getPaymentHoldStatus(): "";
			String paymentHoldReasons=sale.getPaymentHoldReasons()!=null ? sale.getPaymentHoldReasons().toString(): "";
			String transactionFee=sale.getTransactionFee()!=null ? sale.getTransactionFee().getValue(): "0";
			String receivableAmount=sale.getReceivableAmount()!=null ? sale.getReceivableAmount().getValue(): "0";
			String exchangeRate=sale.getExchangeRate()!=null ? sale.getExchangeRate(): "";
			String fmfDetails=sale.getFmfDetails()!=null ? sale.getFmfDetails().getName(): "";
			String receiptId=sale.getReceiptId()!=null ? sale.getReceiptId(): "";
			String processorResponse=sale.getProcessorResponse()!=null?sale.getProcessorResponse().toString(): "";
			String billingAgreementId=sale.getBillingAgreementId()!=null ? sale.getBillingAgreementId(): "";
			String createTime=sale.getCreateTime()!=null ? sale.getCreateTime(): "";
			String updateTime=sale.getUpdateTime()!=null ? sale.getUpdateTime(): "";
			String links=sale.getLinks()!=null ? sale.getLinks().toString(): "";
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"); 
			Timestamp updateTimestamp = new java.sql.Timestamp(simpleDateFormat.parse(updateTime).getTime());
			Timestamp createTimestamp = new java.sql.Timestamp(simpleDateFormat.parse(createTime).getTime());

			statement.setString(1, id);
			statement.setDouble(2,Double.valueOf(amount));
			statement.setString(3,state);
			statement.setString(4,parentPayment);
			statement.setTimestamp(5,createTimestamp);
			statement.setString(6,purchaseUnitReferenceId);
			statement.setString(7,paymentMode);
			statement.setString(8,reasonCode);
			statement.setString(9,protectionEligibility);
			statement.setString(10,protectionEligibilityType);
			statement.setString(11,clearingTime);
			statement.setString(12,paymentHoldStatus);
			statement.setString(13,paymentHoldReasons);
			statement.setDouble(14,Double.valueOf(transactionFee));
			statement.setDouble(15,Double.valueOf(receivableAmount));
			statement.setString(16,exchangeRate);
			statement.setString(17,fmfDetails);
			statement.setString(18,receiptId);
			statement.setString(19,processorResponse);
			statement.setString(20,billingAgreementId);
			statement.setTimestamp(21,updateTimestamp);
			statement.setString(22,links);

			int rowsInserted = DB.runUpdateStatement(statement);
			
			if (rowsInserted > 0) {
			    System.out.println("Sale "+sale.getId()+" was inserted successfully!");
			}
		} catch (SQLException e) {
			CustomException.processError(e);
		} catch (ParseException e) {
			CustomException.processError(e);
		}
	}
	/**
	 * Method: insertNewOrder
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param order
	 * 
	 * void
	 */
	public static void insertNewOrder(Order order) {
		//First make sure we have the basic's to insert sale record.
				Connection conn = DB.getConnection();

				PreparedStatement statement;
				try {
					statement = conn.prepareStatement(SqlQueries.INSERT_ORDER);
					String id=order.getOrderId()!=null?order.getOrderId(): "";
					//Timestamp date=order.getOrderDate()!=null ? order.getOrderDate() : null;
					String buyerName=order.getFullName()!=null ? order.getFullName(): "";
					String fName = order.getFirstName()!=null ? order.getFirstName():"";
					String lName = order.getLastName()!=null ? order.getLastName():"";
					int numItems=order.getTotalItems();
					String shippingMethod=order.getShippingMethod()!=null ? order.getShippingMethod() : "";
					Timestamp shippingDate=order.getShipDate()!=null ? order.getShipDate(): null;
					String orderTotal = order.getOrderTotal()!=null ? order.getOrderTotal(): "";
					String paymentType = order.getPaymentMethod()!=null ? order.getPaymentMethod(): "";
					String orderStatus = order.getOrderStatus()!=null ? order.getOrderStatus(): "";
					String paymentStatus = order.getPaymentStatus()!=null ? order.getPaymentStatus():"";
					String transactionFee =order.getTransactionFee()!=null ? order.getTransactionFee():"0";
					String subTotal=order.getSubTotal()!=null ?order.getSubTotal():"0";
					String saleTax=order.getSalesTax()!=null ?order.getSalesTax():"0";
					String shippingAmount=order.getShippingCost()!=null ?order.getShippingCost():"0";
					String buyerId=order.getBuyerName()!=null ?order.getBuyerName():"";
					String address1=order.getShipStreet1()!=null ?order.getShipStreet1():"";
					String address2=order.getShipStreet2()!=null ?order.getShipStreet2():"";
					String city=order.getShipCity()!=null ?order.getShipCity():"";
					String state=order.getShipState()!=null ?order.getShipState():"";
					String zip=order.getShipZipCode()!=null ?order.getShipZipCode():"";
					String shipToName=order.getFullName()!=null ?order.getFullName():"";
					Timestamp orderDate = order.getOrderDate()!=null?order.getOrderDate(): null;
					RateDetails rateDetails= order.getRateDetails();
					String provider=rateDetails.getProvider()!=null?rateDetails.getProvider(): "";
					String serviceName = rateDetails.getServiceName() !=null ? rateDetails.getServiceName() : "";
					String durationTerms= rateDetails.getDurationTerms() !=null ? rateDetails.getDurationTerms() : "";
					String trackable= rateDetails.getTrackable() !=null ? rateDetails.getTrackable() : "";
					String insurance= rateDetails.getInsurance() !=null ? rateDetails.getInsurance() : "";
					Double insuranceAmount= rateDetails.getInsuranceAmount() !=null ? Double.valueOf(rateDetails.getInsuranceAmount()) : 0.00;
					String rateId=rateDetails.getRateObjectId() !=null ? rateDetails.getRateObjectId() : "";
					statement.setString(1,id);
					statement.setString(2,buyerName);
					statement.setString(3,shippingMethod);
					statement.setTimestamp(4,shippingDate);
					statement.setString(5,buyerName);
					statement.setString(6,fName);
					statement.setString(7,lName);
					statement.setInt(8,numItems);
					statement.setDouble(9,Double.valueOf(orderTotal));
					statement.setString(10,paymentType);
					statement.setString(11,orderStatus);
					statement.setString(12, paymentStatus);
					statement.setDouble(13, Double.valueOf(transactionFee));
					statement.setDouble(14, Double.valueOf(subTotal));
					statement.setDouble(15, Double.valueOf(saleTax));
					statement.setDouble(16, Double.valueOf(shippingAmount));
					statement.setString(17, buyerId);
					statement.setString(18, address1);
					statement.setString(19, address2);
					statement.setString(20, city);
					statement.setString(21, state);
					statement.setString(22, zip);
					statement.setString(23, shipToName);
					statement.setTimestamp(24, orderDate);
					statement.setString(25, provider);
					statement.setString(26, serviceName);
					statement.setString(27, durationTerms);
					statement.setString(28, trackable);
					statement.setString(29, insurance);
					statement.setDouble(30, insuranceAmount);
					statement.setString(31, rateId);
				
					int rowsInserted = statement.executeUpdate();
					
					if (rowsInserted > 0) {
					    System.out.println("order "+order.getOrderId()+" was inserted successfully!");
					}
				} catch (SQLException e) {
					CustomException.processError(e);
				}
		
	}
	/**
	 * Will return order from db and populate object.
	 * @param rs
	 * @return order
	 */
	public static Order getOrderFromDB(ResultSet rs){
		Order order = new Order();
		try {
			order.setOrderId(rs.getString("ORDER_ID"));
			order.setOrderDate(rs.getTimestamp("ORDER_DATE"));
			order.setBuyerName(rs.getString("BUYER_NAME"));
			order.setShippingMethod(rs.getString("SHIPPING_METHOD"));
			order.setShipDate(rs.getTimestamp("SHIPPING_DATE"));
			order.setFullName(rs.getString("FULL_NAME"));
			order.setFirstName(rs.getString("FIRST_NAME"));
			order.setLastName(rs.getString("LAST_NAME"));
			order.setTotalItems(rs.getInt("NUMBER_OF_ITEMS"));
			order.setCouponCode(rs.getString("COUPON_CODE"));
			order.setCouponDetails(rs.getString("COUPON_DETAILS"));
			order.setOrderTotal(rs.getString("ORDER_TOTAL"));
			order.setPaymentType(rs.getString("PAYMENT_TYPE"));
			order.setOrderStatus(rs.getString("ORDER_STATUS"));
			order.setPaymentStatus(rs.getString("PAYMENT_STATUS"));
			order.setTransactionFee(String.valueOf(rs.getInt("TRANSACTION_FEE")));
			order.setSubTotal(String.valueOf(rs.getInt("SUB_TOTAL")));
			order.setSalesTax(String.valueOf(rs.getInt("SALES_TAX")));
			order.setShippingCost(String.valueOf(rs.getInt("SHIPPING_AMOUNT")));
			order.setBuyerUserId(rs.getString("BUYER_ID"));
			order.setShipStreet1((rs.getString("SHIP_TO_ADDRESS1")));
			order.setShipStreet2(rs.getString("SHIP_TO_ADDRESS2"));
			order.setShipCity(rs.getString("SHIP_TO_CITY"));
			order.setShipState(rs.getString("SHIP_TO_STATE"));
			order.setShipZipCode(rs.getString("SHIP_TO_ZIP"));
			order.setShipToName(rs.getString("SHIP_TO_NAME"));
			RateDetails rateDetails = new RateDetails();
			rateDetails.setDurationTerms(rs.getString("SHIPPING_DURATION"));
			rateDetails.setInsurance(rs.getString("SHIPPING_INSURANCE"));
			rateDetails.setInsuranceAmount(String.valueOf(rs.getDouble("SHIPPING_INSURANCE_AMT")));
			rateDetails.setProvider(rs.getString("SHIPPING_PROVIDER"));
			rateDetails.setServiceName(rs.getString("SHIPPING_SERVICE"));
			rateDetails.setTrackable(rs.getString("SHIPPING_TRACKABLE"));
			rateDetails.setRateObjectId(rs.getString("SHIPPING_RATE_ID"));
			order.setRateDetails(rateDetails);
		} catch (SQLException e) {
			CustomException.processError(e);
		}
		return order;
	}
	/**
	 * Will return order from db and populate object.
	 * @param id 
	 * @return Order object
	 */
	public static Order getOrderById(String id){
		
		Connection conn = DB.getConnection();
		Order order=new Order();
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(SqlQueries.BMH_ORDERS_BY_ID);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				order.setOrderId(rs.getString("ORDER_ID"));
				order.setOrderDate(rs.getTimestamp("ORDER_DATE"));
				order.setBuyerName(rs.getString("BUYER_NAME"));
				order.setShippingMethod(rs.getString("SHIPPING_METHOD"));
				order.setShipDate(rs.getTimestamp("SHIPPING_DATE"));
				order.setFullName(rs.getString("FULL_NAME"));
				order.setFirstName(rs.getString("FIRST_NAME"));
				order.setLastName(rs.getString("LAST_NAME"));
				order.setTotalItems(rs.getInt("NUMBER_OF_ITEMS"));
				order.setCouponCode(rs.getString("COUPON_CODE"));
				order.setCouponDetails(rs.getString("COUPON_DETAILS"));
				order.setOrderTotal(rs.getString("ORDER_TOTAL"));
				order.setPaymentType(rs.getString("PAYMENT_TYPE"));
				order.setOrderStatus(rs.getString("ORDER_STATUS"));
				order.setPaymentStatus(rs.getString("PAYMENT_STATUS"));
				order.setTransactionFee(String.valueOf(rs.getInt("TRANSACTION_FEE")));
				order.setSubTotal(String.valueOf(rs.getInt("SUB_TOTAL")));
				order.setSalesTax(String.valueOf(rs.getInt("SALES_TAX")));
				order.setShippingCost(String.valueOf(rs.getInt("SHIPPING_AMOUNT")));
				order.setBuyerUserId(rs.getString("BUYER_ID"));
				order.setShipStreet1((rs.getString("SHIP_TO_ADDRESS1")));
				order.setShipStreet2(rs.getString("SHIP_TO_ADDRESS2"));
				order.setShipCity(rs.getString("SHIP_TO_CITY"));
				order.setShipState(rs.getString("SHIP_TO_STATE"));
				order.setShipZipCode(rs.getString("SHIP_TO_ZIP"));
				order.setShipToName(rs.getString("SHIP_TO_NAME"));
				RateDetails rateDetails = new RateDetails();
				rateDetails.setDurationTerms(rs.getString("SHIPPING_DURATION"));
				rateDetails.setInsurance(rs.getString("SHIPPING_INSURANCE"));
				rateDetails.setInsuranceAmount(String.valueOf(rs.getDouble("SHIPPING_INSURANCE_AMT")));
				rateDetails.setProvider(rs.getString("SHIPPING_PROVIDER"));
				rateDetails.setServiceName(rs.getString("SHIPPING_SERVICE"));
				rateDetails.setTrackable(rs.getString("SHIPPING_TRACKABLE"));
				rateDetails.setRateObjectId(rs.getString("SHIPPING_RATE_ID"));
				order.setRateDetails(rateDetails);
			}
			rs.close();
			statement.close();
			
		} catch (SQLException e) {
			CustomException.processError(e);
		}
		return order;
	}
}
