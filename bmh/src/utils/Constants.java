package utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import com.shippo.model.Address;

import database.DBManager;

/**
 * 
 */
public class Constants {
	
	public static final DBManager DB = new DBManager("jdbc:oracle:thin:@//10.10.10.1:1521/orcl", "javabot","zzzz");
	
	
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");
	
	public static final String USPS_ID = "083BLUEM3750";
	public static final String PAYPAY_CHECKOUT = "";
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String BMH_PRODUCT_IMAGES = "BMH_PRODUCT_IMAGES";
	public static final String BMH_BLOGS = "BMH_BLOGS";
	public static final String BMH_REPORT_ETSY_ORDERS = "BMH_REPORT_ETSY_ORDERS";
	public static final String BMH_REPORT_ETSY_SOLD_ITEM = "BMH_REPORT_ETSY_SOLD_ITEM";
	public static final String BMH_REPORT_ETSY_DIRECT_CK = "BMH_REPORT_ETSY_DIRECT_CK";
	public static final String BMH_REPORT_ETSY_DEPOSITS = "BMH_REPORT_ETSY_DEPOSITS";
	public static final String BMH_PRODUCTS = "BMH_PRODUCTS";
	public static final String BMH_IMAGE_NAMES = "BMH_IMAGE_NAMES";
	public static final String EMAIL_HOST = "smtp.gmail.com";
	public static final String USER_EMAIL = "madboxsolutions@gmail.com";
	public static final String USER_PASSWORD = "483generated876^";
	public static final String EMAIL_PORT = "587";
	/**
	 * Contains all the address info needed
	 * @author madal
	 *
	 */
	public static class Mail {
		
		public static Address getReturnAddress(){
			Address returnAddress = new Address();
			returnAddress.setEmail("bluemountainholly@gmail.com");
			returnAddress.setStreet1("Test Street Dr");
			returnAddress.setStreet2("");
			returnAddress.setCity("Abingdon");
			returnAddress.setState("VA");
			returnAddress.setZip("24210");
			returnAddress.setPhone("(276) 555-4564");
			returnAddress.setCompany("Blue Mountain Holly");
			returnAddress.setCountry("US");
			returnAddress.setName("Holly Dale");
			returnAddress.setObject_purpose("QUOTE");
			return returnAddress;
		}
	}
	/**
	 * Contains all the email addresses used.
	 * @author madal
	 *
	 */
	public static class Emails {
		public final static String EMAIL_FROM = "admin@madbox-solutions.com";
		public final static String EMAIL_FROM_NAME = "Admin Madbox-solutions";
		public final static String EMAIL_TO = "madale2010@gmail.com";
		public final static String EMAIL_TO_NAME = "Blue Mountain Holly";
	}
	/**
	 * Contains all the urls used.
	 * @author madal
	 *
	 */
	public static class Urls {
		public final static String SERVER_NAME = "www.bluemountainholly.com";
		public final static String REMOTE_HOST = "10.10.10.1";
		public final static String RETURN_URL = "http://www.bluemountainholly.com/orderConfirm.jsp";
		public final static String CANCEL_URL = "http://www.bluemountainholly.com";
		public final static String PAYPAL_CHECKOUT_TEST = "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=";
		public final static String USPS_URL = "http://production.shippingapis.com/ShippingApi.dll";
		public final static String USPS_URL_SSL = "https://secure.shippingapis.com/ShippingAPI.dll";
	}
	/**
	 * Contains all etsy info 
	 */
	public static class Etsy {
		public final static String client_id="";
		
		public final static String client_secret ="";
		public final static String sandbox_client_id="t2qbqzvw1tifiavlksre1h7c";
		public final static String sandbox_client_secret ="o0jae4urs6";
		public final static String sandbox_client_token ="9504632a6c8fbad46970d7d5af6949";
		public final static String sandbox_client_token_secret="19ca528b49";
	}
	/**
	 * Contains all the table columns in array format.
	 * @author madal
	 *
	 */
	public static class TableHeaders {
		public final static String[] BLOG_HEADERS = { "Blog Id", "Category", "Title", "Date", "Data" };
		public final static String[] SALE_HEADERS = {"id", "purchaseUnitReferenceId", "amount", "paymentMode", "state", "reasonCode", "protectionEligibility", "protectionEligibilityType", "clearingTime", "paymentHoldStatus", "paymentHoldReasons", "transactionFee", "receivableAmount", "exchangeRate", "fmfDetails", "receiptId", "billingAgreementId", "createTime", "updateTime", "links"};
	}
	/**
	 * Contains all the dq sql select,update,delete queries needed.
	 * @author madal
	 *
	 */
	public class SqlQueries {
		//Selects
		public static final String BMH_ORDERS_BY_ID = "SELECT * FROM BMH_ORDERS WHERE ORDER_ID=?";
		public static final String BMH_ORDERS_SELECT = "SELECT * FROM BMH_ORDERS ";
		public static final String BMH_SHIPPING_METHOD = "SELECT * FROM BMH_SHIPPING_DETAILS WHERE PID=?";
		public static final String BMH_SALES_SELECT = "SELECT * "+
				 										 "FROM BMH_SALES ";
		public static final String BMH_ALERTS_SELECT = "SELECT * "+
				 									  "FROM BMH_ALERTS WHERE ALERT_TYPE !='CLOSED' ";
		public static final String BMH_PRODUCTS_SELECT = "SELECT * "+
														 "FROM BMH_PRODUCTS ";
		public static final String BMH_BLOGS_SELECT = "SELECT * "+
													  "FROM BMH_BLOGS "+ 
													  "ORDER BY BLOG_DATE DESC ";
		public static final String BMH_PRODUCT_IMAGES_SELECT = "SELECT * " + 
															   "FROM BMH_IMAGE_NAMES ";
		public static final String BMH_PRODUCT_IMAGES_BYID = "SELECT * " + 
															 "FROM BMH_PRODUCT_IMAGES " + 
															 "WHERE PID='?'";
		public static final String BMH_PRODUCTS_BYID = "SELECT PROD.PRODUCT_NAME, PROD.DESCRIPTION, PROD.PRICE, IMG.PRIMARY_IMAGE "+
													   "FROM BMH_PRODUCTS PROD " +
													   "LEFT JOIN BMH_IMAGE_NAMES IMG "+ 
													   "ON PROD.PID=IMG.PID "+
													   "WHERE PROD.PID='?' ";
		public static final String MENU_ITEMS_SELECT = "SELECT DISTINCT CATEGORY, SUB_CATEGORY "+ 
													   "FROM BMH_PRODUCTS "+
													   "WHERE CATEGORY IS NOT NULL " + 
													   "ORDER BY CATEGORY ";
		public static final String SIMPLE_MENU_SELECT = "SELECT DISTINCT CATEGORY, SUB_CATEGORY "+
														"FROM BMH_PRODUCTS "+
														"WHERE CATEGORY IS NOT NULL AND CATEGORY not like('%,%') and sub_category not like('%,%') "+
														"ORDER BY CATEGORY ";
		public static final String PRODUCTS_SELECT = "SELECT PROD.PID, PROD.PRODUCT_NAME, PROD.DESCRIPTION, PROD.PRICE, PROD.QUANTITY, PROD.SHORT_DESCRIPTION, PROD.CURRENCY_CODE,PROD.AVAILABLE, IMG.PRIMARY_IMAGE "+
													 "FROM BMH_PRODUCTS PROD LEFT JOIN BMH_IMAGE_NAMES IMG ON PROD.PID=IMG.PID " +
													 "WHERE DISPLAY_FLAG='Y' "+
													 "ORDER BY PID ";
		public static final String BLOG_SELECT = "SELECT BLOG_TITLE, BLOG_DATE, BLOG_DATA, BLOG_IMAGE, BLOG_IMAGE_EXT, BLOG_ID, BLOG_CATEGORY, BLOG_HTML "+
				 								 "FROM BMH_BLOGS " +
				 								 "ORDER BY BLOG_DATE ";
		public static final String FEATURED_PRODUCTS_SELECT = "SELECT PROD.PID, PROD.PRODUCT_NAME, PROD.DESCRIPTION, PROD.PRICE, PROD.QUANTITY, PROD.SHORT_DESCRIPTION, PROD.CURRENCY_CODE, PROD.AVAILABLE, IMG.PRIMARY_IMAGE "+
															  "FROM BMH_PRODUCTS PROD LEFT JOIN BMH_IMAGE_NAMES IMG ON PROD.PID=IMG.PID " + 
															  "WHERE FEATURED='Y' AND DISPLAY_FLAG='Y' ";
		public static final String DEBUG_FEATURED_PRODUCTS_SELECT = "SELECT PROD.PID, DBIMG.PRIMARY_IMAGE, PROD.PRODUCT_NAME, PROD.DESCRIPTION, PROD.PRICE, PROD.QUANTITY, PROD.SHORT_DESCRIPTION, PROD.CURRENCY_CODE, IMG.PRIMARY_IMAGE "+
																	"FROM BMH_PRODUCTS PROD " + 
																	"JOIN BMH_IMAGE_NAMES IMG ON PROD.PID=IMG.PID "+
				 													"JOIN BMH_PRODUCT_IMAGES DBIMG ON PROD.PID=DBIMG.PID " + 
																	"WHERE FEATURED='Y' ";
		public static final String LOAD_SINGLE_PRODUCT="SELECT PROD.FEATURED, PROD.PID, PROD.CATEGORY,PROD.SUB_CATEGORY,PROD.PRODUCT_NAME, PROD.DESCRIPTION, PROD.PRICE, PROD.QUANTITY, PROD.SHORT_DESCRIPTION, PROD.CURRENCY_CODE, PROD.AVAILABLE, PROD.DISPLAY_FLAG, PROD.TAGS, IMG.PRIMARY_IMAGE, IMG.ADDITIONAL_IMAGES,SHIP.WEIGHT_LBS,SHIP.WEIGHT_OZ,SHIP.SIZE_LENGTH,SHIP.SIZE_WIDTH,SHIP.SIZE_HEIGHT,SHIP.SHIPPING_PROFILE_ID,PROF.PROFILE_NAME "+ 
													   "FROM BMH_PRODUCTS PROD "+ 
													   "LEFT JOIN BMH_IMAGE_NAMES IMG "+ 
													   "ON PROD.PID=IMG.PID "+
													   "JOIN BMH_SHIPPING_DETAILS SHIP "+
													   "ON SHIP.PID=PROD.PID "+
													   "JOIN BMH_SHIPPING_PROFILE PROF "+
													   "ON SHIP.SHIPPING_PROFILE_ID=PROF.ID "+
													   "WHERE PROD.PID=? ";
		public static final String LOAD_ALL_PRODUCTS="SELECT PROD.FEATURED, PROD.PID, PROD.CATEGORY,PROD.SUB_CATEGORY,PROD.PRODUCT_NAME, PROD.DESCRIPTION, PROD.PRICE, PROD.QUANTITY, PROD.SHORT_DESCRIPTION, PROD.CURRENCY_CODE, PROD.AVAILABLE, PROD.DISPLAY_FLAG, IMG.PRIMARY_IMAGE, IMG.ADDITIONAL_IMAGES,SHIP.WEIGHT_LBS,SHIP.WEIGHT_OZ,SHIP.SIZE_LENGTH,SHIP.SIZE_WIDTH,SHIP.SIZE_HEIGHT,SHIP.SHIPPING_PROFILE_ID,PROF.PROFILE_NAME "+ 
				   									 "FROM BMH_PRODUCTS PROD "+ 
				   									 "LEFT JOIN BMH_IMAGE_NAMES IMG "+ 
				   									 "ON PROD.PID=IMG.PID "+
				   									 "JOIN BMH_SHIPPING_DETAILS SHIP "+
				   									 "ON SHIP.PID=PROD.PID "+
													 "JOIN BMH_SHIPPING_PROFILE PROF "+
													 "ON SHIP.SHIPPING_PROFILE_ID=PROF.ID ";
		public static final String LOAD_SHIPPING_PROFILES="SELECT * FROM BMH_SHIPPING_PROFILE";
		//Inserts
		public static final String INSERT_ORDER ="INSERT INTO BMH_ORDERS (ORDER_ID,"
				+ "BUYER_NAME,"
				+ "SHIPPING_METHOD,"
				+ "SHIPPING_DATE,"
				+ "FULL_NAME,"
				+ "FIRST_NAME,"
				+ "LAST_NAME,"
				+ "NUMBER_OF_ITEMS,"
				+ "ORDER_TOTAL,"
				+ "PAYMENT_TYPE,"
				+ "ORDER_STATUS,"
				+"PAYMENT_STATUS,"
				+"TRANSACTION_FEE," 
				+"SUB_TOTAL," 
				+"SALES_TAX," 
				+"SHIPPING_AMOUNT," 
				+"BUYER_ID," 
				+"SHIP_TO_ADDRESS1," 
				+"SHIP_TO_ADDRESS2," 
				+"SHIP_TO_CITY," 
				+"SHIP_TO_STATE," 
				+"SHIP_TO_ZIP," 
				+"SHIP_TO_NAME,"
				+"ORDER_DATE,"
				+"SHIPPING_PROVIDER,"
				+"SHIPPING_SERVICE,"
				+"SHIPPING_DURATION,"
				+"SHIPPING_TRACKABLE,"
				+"SHIPPING_INSURANCE,"
				+"SHIPPING_INSURANCE_AMT,"
				+ "SHIPPING_RATE_ID)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		public static final String INSERT_SALES_FULL = "INSERT INTO BMH_SALES (SALE_ID,"
				+ "AMOUNT,"
				+ "STATE,"
				+ "PARENT_PAYMENT,"
				+ "CREATE_TIME,"
				+ "PURCHASE_UNIT_REF_ID,"
				+ "PAYMENT_MODE,"
				+ "REASON_CODE,"
				+ "PROTECTION_ELIGIBILITY,"
				+ "PROTECTION_ELIGIBILITY_TYPE,"
				+ "CLEARING_TIME,"
				+ "PAYMENT_HOLD_STATUS,"
				+ "PAYMENT_HOLD_REASONS,"
				+ "TRANSACTION_FEE,"
				+ "RECEIVABLE_AMOUNT,"
				+ "EXCHANGE_RATE,"
				+ "FMF_DETAILS,"
				+ "RECEIPT_ID,"
				+ "PROCESSOR_RESPONSE,"
				+ "BILLING_AGREEMENT_ID,"
				+ "UPDATE_TIME,"
				+ "LINKS) "+
				 									   "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		//Updates
		public static final String UPDATE_PRODUCTS_SELECT = "SELECT PROD.PID, PROD.FEATURED, PROD.CATEGORY, PROD.SUB_CATEGORY, PROD.TITLE_TAGS, PROD.TAGS, "+
																"PROD.PRODUCT_NAME, PROD.DESCRIPTION, PROD.PRICE, PROD.QUANTITY, PROD.SHORT_DESCRIPTION, PROD.CURRENCY_CODE, PROD.AVAILABLE, PROD.DISPLAY_FLAG, "+
																"IMG.PRIMARY_IMAGE, IMG.ADDITIONAL_IMAGES, PROD.LAST_UPDATE " + 
															"FROM BMH_PRODUCTS PROD "+
															"LEFT JOIN BMH_IMAGE_NAMES IMG " + 
															"ON PROD.PID=IMG.PID " + 
															"ORDER BY PROD.LAST_UPDATE DESC";
		public static final String UPDATE_PRODUCT_DETAILS_ALL = "UPDATE BMH_SHIPPING_DETAILS SET WEIGHT_LBS=?,WEIGHT_OZ=?,SIZE_LENGTH=?,SIZE_WIDTH=?,SIZE_HEIGHT=? "+
																"WHERE PID=? ";
		//Deletes
		public static final String DELETE_PRODUCT = "DELETE FROM BMH_PRODUCTS WHERE PID = '?' ";
		public static final String DELETE_PRODUCT_IMAGES = "DELETE FROM BMH_IMAGE_NAMES WHERE PID = '?' ";
		
		



	}
	/**
	 * ClassPaths needed for processing.
	 * @author madal
	 *
	 */
	public static class ClassPaths {
		public static final Path LOG_MONITOR = getLogMonitor();		
		public static final Path BASE = Paths.get(File.separator+"opt"+File.separator+"external"+File.separator+"bmh");
		public static final Path IMAGE_LOCATION = Paths.get(BASE+File.separator+"images");
		public static final Path BLOG_IMAGE_LOCATION = Paths.get(IMAGE_LOCATION+File.separator+"Blog");
		public static final Path PRODUCT_IMAGE_LOCATION = Paths.get(IMAGE_LOCATION+File.separator+"Products");
		public static final Path UPLOAD_LOCATION = Paths.get(IMAGE_LOCATION+File.separator+"upload");
		public static final Path DOWNLOAD_LOCATION = Paths.get(BASE+File.separator+"public");
		public static final Path getBlogLocation() {
			if (OS_NAME.contains("Win")) {
				return Paths.get("C:"+File.separator+"opt"+File.separator+"external"+File.separator+"bmh"+File.separator+"images"+File.separator+"Blog");
			} else {
				return BLOG_IMAGE_LOCATION;
			}
		}
		public static final Path getLogMonitor() {
			 if (OS_NAME.contains("Win")) {
					return Paths.get("C:"+File.separator+"opt"+File.separator+"external"+File.separator+"logs");
				} else {
					return Paths.get(File.separator+"opt"+File.separator+"external"+File.separator+"logs");
				}
		}
		/**
		 * Method: getBasePath
		 * Author: madal
		 * 
		 * Description: 
		 *
		 * @return
		 * 
		 * Path
		 */
		public static final Path getBasePath() {
			if (OS_NAME.contains("Win")) {
				return Paths.get("C:"+File.separator+"opt"+File.separator+"external"+File.separator+"bmh"+File.separator+"images");
			} else {
				return Paths.get(BASE.toString()+File.separator+"images");
			}
		}
		/**
		 * Method: getImageLocation
		 * Author: madal
		 * 
		 * Description: 
		 *
		 * @return
		 * 
		 * Path
		 */
		public static final Path getImageLocation() {
			if (OS_NAME.contains("Win")) {
				return Paths.get("C:"+File.separator+"opt"+File.separator+"external"+File.separator+"bmh"+File.separator+"images"+File.separator+"Products");
			} else {
				return PRODUCT_IMAGE_LOCATION;
			}
		}
		public static final Path getUploadLocation() {
			if (OS_NAME.contains("Win")) {
				return Paths.get("C:"+File.separator+"opt"+File.separator+"external"+File.separator+"bmh"+File.separator+"images"+File.separator+"upload");
			} else {
				return UPLOAD_LOCATION;
			}
		}
		public static final Path getDownLoadLocation() {
			if (OS_NAME.contains("Win")) {
				return Paths.get("C:"+File.separator+"opt"+File.separator+"external"+File.separator+"bmh"+File.separator+"public");
			} else {
				return DOWNLOAD_LOCATION;
			}
		}
	}
}
