package database;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class TableMaps {
 private Map<String, Object> orderTable=new HashMap<String, Object>();
 private Map<String, Object> alertsTable=new HashMap<String, Object>();
 private Map<String, Object> blogCommentTable=new HashMap<String, Object>();
 private Map<String, Object> blogTable=new HashMap<String, Object>();
 private Map<String, Object> imageNamesTable=new HashMap<String, Object>();
 private Map<String, Object> productTable=new HashMap<String, Object>();
 private Map<String, Object> etsyDepositsTable=new HashMap<String, Object>();
 private Map<String, Object> etsyDirectCkTable=new HashMap<String, Object>();
 private Map<String, Object> etsyOrdersTable=new HashMap<String, Object>();
 private Map<String, Object> salesTable=new HashMap<String, Object>();
 private Map<String, Object> shippingDetailsTable=new HashMap<String, Object>();
 private Map<String, Object> shippingProfilesTable=new HashMap<String, Object>();
/**
 * Constructor
 */
public TableMaps(){
	alertsTable.put("ALERT_ID", null);
	alertsTable.put("ALERT_TYPE", null);
	alertsTable.put("ALERT_NAME", null);
	alertsTable.put("ALERT_DATE", null);
	alertsTable.put("ALERT_DESCRIPTION", null);
	
	blogCommentTable.put("CID", null);
	blogCommentTable.put("BLOG_COMMENT", null);
	blogCommentTable.put("BLOG_ID", null);
	blogCommentTable.put("COMMENT_DATE", null);
	
	blogTable.put("BLOG_TITLE", null);
	blogTable.put("BLOG_DATE", null);
	blogTable.put("BLOG_DATA", null);
	blogTable.put("BLOG_IMAGE", null);
	blogTable.put("BLOG_IMAGE_EXT", null);
	blogTable.put("BLOG_CATEGORY", null);
	
	imageNamesTable.put("PID", null);
	imageNamesTable.put("PRIMARY_IMAGE", null);
	imageNamesTable.put("ADDITIONAL_IMAGES", null);
	
	productTable.put("PID", null);
	productTable.put("IMG_NAME", null);
	productTable.put("PRODUCT_NAME", null);
	productTable.put("CATEGORY", null);
	productTable.put("SUB_CATEGORY", null);
	productTable.put("TITLE_TAGS", null);
	productTable.put("SHORT_DESCRIPTION", null);
	productTable.put("DESCRIPTION", null);
	productTable.put("PRICE", null);
	productTable.put("CURRENCY_CODE", null);
	productTable.put("QUANTITY", null);
	productTable.put("TAGS", null);
	productTable.put("FEATURED", null);
	productTable.put("LAST_UPDATE", null);
	productTable.put("AVAILABLE", null);
	
	etsyDepositsTable.put("DEPOSIT_DATE", null);
	etsyDepositsTable.put("AMOUNT", null);
	etsyDepositsTable.put("CURRENCY", null);
	etsyDepositsTable.put("STATUS", null);
	etsyDepositsTable.put("BANK_ACCOUNT_ENDING_DIGITS", null);
	
	etsyDirectCkTable.put("PAYMENT_ID", null);
	etsyDirectCkTable.put("BUYER_USERNAME", null);
	etsyDirectCkTable.put("BUYER_NAME", null);
	etsyDirectCkTable.put("ORDER_ID", null);
	etsyDirectCkTable.put("GROSS_AMOUNT", null);
	etsyDirectCkTable.put("FEES", null);
	etsyDirectCkTable.put("NET_AMOUNT", null);
	etsyDirectCkTable.put("POSTED_GROSS", null);
	etsyDirectCkTable.put("POSTED_FEES", null);
	etsyDirectCkTable.put("POSTED_NET", null);
	etsyDirectCkTable.put("ADJUSTED_GROSS", null);
	etsyDirectCkTable.put("ADJUSTED_FEES", null);
	etsyDirectCkTable.put("ADJUSTED_NET", null);
	etsyDirectCkTable.put("CURRENCY", null);
	etsyDirectCkTable.put("LISTING_AMOUNT", null);
	etsyDirectCkTable.put("LISTING_CURRENCY", null);
	etsyDirectCkTable.put("EXCHANGE_RATE", null);
	etsyDirectCkTable.put("VAT_AMOUNT", null);
	etsyDirectCkTable.put("GIFT_CARD_APPLIED", null);
	etsyDirectCkTable.put("STATUS", null);
	etsyDirectCkTable.put("FUNDS_AVAILABLE", null);
	etsyDirectCkTable.put("ORDER_DATE", null);
	etsyDirectCkTable.put("BUYER", null);
	etsyDirectCkTable.put("ORDER_TYPE", null);
	etsyDirectCkTable.put("PAYMENT_TYPE", null);
	etsyDirectCkTable.put("REFUND_AMOUNT", null);
	
	etsyOrdersTable.put("SALE_DATE", null);
	etsyOrdersTable.put("ORDER_ID", null);
	etsyOrdersTable.put("BUYER_USER_ID", null);
	etsyOrdersTable.put("FULL_NAME", null);
	etsyOrdersTable.put("FIRST_NAME", null);
	etsyOrdersTable.put("LAST_NAME", null);
	etsyOrdersTable.put("NUMBER_OF_ITEMS", null);
	etsyOrdersTable.put("PAYMENT_METHOD", null);
	etsyOrdersTable.put("DATE_SHIPPED", null);
	etsyOrdersTable.put("STREET_1", null);
	etsyOrdersTable.put("STREET_2", null);
	etsyOrdersTable.put("SHIP_CITY", null);
	etsyOrdersTable.put("SHIP_STATE", null);
	etsyOrdersTable.put("SHIP_ZIPCODE", null);
	etsyOrdersTable.put("SHIP_COUNTRY", null);
	etsyOrdersTable.put("CURRENCY", null);
	etsyOrdersTable.put("ORDER_VALUE", null);
	etsyOrdersTable.put("COUPON_CODE", null);
	etsyOrdersTable.put("COUPON_DETAILS", null);
	etsyOrdersTable.put("SHIPPING", null);
	etsyOrdersTable.put("SALES_TAX", null);
	etsyOrdersTable.put("ORDER_TOTAL", null);
	etsyOrdersTable.put("STATUS", null);
	etsyOrdersTable.put("CARD_PROCESSING_FEES", null);
	etsyOrdersTable.put("ORDER_NET", null);
	etsyOrdersTable.put("ADJUSTED_ORDER_TOTAL", null);
	etsyOrdersTable.put("ADJUSTED_CARD_PROCESSING_FEES", null);
	etsyOrdersTable.put("ADJUSTED_NET_ORDER_AMOUNT", null);
	etsyOrdersTable.put("BUYER", null);
	etsyOrdersTable.put("ORDER_TYPE", null);
	etsyOrdersTable.put("PAYMENT_TYPE", null);
	etsyOrdersTable.put("INPERSON_DISCOUNT", null);
	etsyOrdersTable.put("INPERSON_LOCATION", null);
	
	orderTable.put("ORDER_ID", null);
	orderTable.put("ORDER_DATE", null);
	orderTable.put("BUYER_NAME", null);
	orderTable.put("PRODUCT_ID", null);
	orderTable.put("QUANTITY_ORDERED", null);
	orderTable.put("SHIPPING_METHOD", null);
	orderTable.put("SHIPPING_DATE", null);
	orderTable.put("FULL_NAME", null);
	orderTable.put("FIRST_NAME", null);
	orderTable.put("LAST_NAME", null);
	orderTable.put("NUMBER_OF_ITEMS", null);
	orderTable.put("COUPON_CODE", null);
	orderTable.put("COUPON_DETAILS", null);
	orderTable.put("ORDER_TOTAL", null);
	orderTable.put("PAYMENT_TYPE", null);
	orderTable.put("ORDER_STATUS", null);
	orderTable.put("PAYMENT_STATUS", null);
	orderTable.put("TRANSACTION_FEE", null);
	orderTable.put("SUB_TOTAL", null);
	orderTable.put("SALES_TAX", null);
	orderTable.put("SHIPPING_AMOUNT", null);
	orderTable.put("BUYER_ID", null);
	orderTable.put("SHIP_TO_ADDRESS1", null);
	orderTable.put("SHIP_TO_ADDRESS2", null);
	orderTable.put("SHIP_TO_CITY", null);
	orderTable.put("SHIP_TO_STATE", null);
	orderTable.put("SHIP_TO_ZIP", null);
	orderTable.put("SHIP_TO_NAME", null);
	
	salesTable.put("SALE_ID", null);
	salesTable.put("AMOUNT", null);
	salesTable.put("STATE", null);
	salesTable.put("PARENT_PAYMENT", null);
	salesTable.put("CREATE_TIME", null);
	salesTable.put("PURCHASE_UNIT_REF_ID", null);
	salesTable.put("PAYMENT_MODE", null);
	salesTable.put("REASON_CODE", null);
	salesTable.put("PROTECTION_ELIGIBILITY", null);
	salesTable.put("PROTECTION_ELIGIBILITY_TYPE", null);
	salesTable.put("CLEARING_TIME", null);
	salesTable.put("PAYMENT_HOLD_STATUS", null);
	salesTable.put("PAYMENT_HOLD_REASONS", null);
	salesTable.put("TRANSACTION_FEE", null);
	salesTable.put("RECEIVABLE_AMOUNT", null);
	salesTable.put("EXCHANGE_RATE", null);
	salesTable.put("FMF_DETAILS", null);
	salesTable.put("RECEIPT_ID", null);
	salesTable.put("PROCESSOR_RESPONSE", null);
	salesTable.put("BILLING_AGREEMENT_ID", null);
	salesTable.put("UPDATE_TIME", null);
	salesTable.put("LINKS", null);
	
	shippingDetailsTable.put("PID", null);
	shippingDetailsTable.put("WEIGHT_LBS", null);
	shippingDetailsTable.put("WEIGHT_OZ", null);
	shippingDetailsTable.put("SIZE_LENGTH", null);
	shippingDetailsTable.put("SIZE_WIDTH", null);
	shippingDetailsTable.put("SIZE_HEIGHT", null);
	shippingDetailsTable.put("SHIPPING_PROFILE_ID", null);

	shippingProfilesTable.put("ID", null);
	shippingProfilesTable.put("PROFILE_NAME", null);
	shippingProfilesTable.put("ORIGIN_ZIP", null);
	shippingProfilesTable.put("PROCESS_TIME", null);
	shippingProfilesTable.put("SHIP_TO_COUNTRIES", null);
	shippingProfilesTable.put("SHIPPING_SERVICES", null);
	shippingProfilesTable.put("FREE_SHIPPING_DOMESTIC", null);
	shippingProfilesTable.put("FREE_SHIPPING_INTER", null);
	shippingProfilesTable.put("HANDLING_FEE", null);
}
}

