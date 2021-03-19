package customer;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DBManager;
import product.Product;
import product.ProductHelper;
import utils.Cart;
import utils.ConfigData;
import utils.Constants.SqlQueries;
import utils.CustomException;
import utils.FileHelper;
import utils.NavigationHelper;

/**
 * Servlet implementation class Product
 */
@MultipartConfig(fileSizeThreshold=1024*1024*10,    // 10 MB 
maxFileSize=1024*1024*50,          // 50 MB
maxRequestSize=1024*1024*100)  

public class RequestProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager db;
	/**
	 * Default constructor.
	 */
	public RequestProducts() {
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
		
		
		System.out.println(FileHelper.getMethodName());
		 db = (DBManager) getServletContext().getAttribute("db");
		HttpSession session = request.getSession();
		String category=request.getParameter("category")!=null? request.getParameter("category"):"";
		String subCategory=request.getParameter("subCategory")!=null? request.getParameter("subCategory"):"";
		request.setAttribute("start", "0");
		request.setAttribute("end", "7");
		String action = request.getParameter("action")!=null ? request.getParameter("action"):"";
		String featuredFlag = request.getParameter("featured")!=null? request.getParameter("featured"):"";
			if(action.equalsIgnoreCase("addItem")){
				//Check for cart items
				Cart shoppingCart;
				 if(!request.getSession().isNew()){
					 
					 shoppingCart = session.getAttribute("cart")!=null ? (Cart) session.getAttribute("cart") : new Cart();
					 session.setAttribute("cart", shoppingCart);
				 } 
				 else { //New session
					 shoppingCart = request.getAttribute("cart")!=null ? (Cart) request.getAttribute("cart") : new Cart();
				 }
				//Because we are on a instansent add we need to set the quanity down to only 1
				// This should only be set if the user selects more that one from the product page.
				String productId =request.getParameter("productId");
				Product item =ProductHelper.loadSingleProduct(productId);
				//Check for existing cart items
				if(!shoppingCart.checkforItem(productId)){
				//For later use we need to restrict to max items we have in stock
				item.setMaxQuantity(item.getQuantity());
				//User only selected 1 for now.
				item.setQuantity("1");
				shoppingCart.addToCart(item);
				} else {
					shoppingCart.updateExistingItem(productId);
				}
				
				
				request.setAttribute("cartTotal", shoppingCart.getCartItems().size());
				request.setAttribute("cart", shoppingCart);
				request.getSession().setAttribute("cartTotal", shoppingCart.getCartItems().size());
				request.getSession().setAttribute("cart", shoppingCart);
				System.out.println("DEBUG   request for add cart item");
				
			}
			//Case statement to determine which products load
			if(featuredFlag!=""){
				//Load default configurations needed for slider
				 ConfigData config = new ConfigData();
				 config.loadDefaults();
				 ArrayList<String> temp = new ArrayList<String>();
				 for(String item: config.getCarouselList()) {
					 temp.add("images//"+item);
				 }
				 ArrayList<Product> productList = loadFeaturedProducts();
				request.setAttribute("carouselList", temp);
				request.setAttribute("productList", productList);
				session.setAttribute("productList", productList);
				request.setAttribute("start", "0");
				request.setAttribute("end", "7");
			}
			else if(action.equalsIgnoreCase("changePage")) {
				//Last handle any page changes
				request=NavigationHelper.pageHandler(request);
			}
			else if(action.equalsIgnoreCase("single")){
				request.setAttribute("product", ProductHelper.loadSingleProduct(request.getParameter("pid")));
			} else {

				ArrayList<Product> productList =loadProductByCategory(category, subCategory);
				request.setAttribute("category", category);
				request.setAttribute("subCategory", subCategory);
				int numOfItems=productList.size();
				int pagesNeeded=numOfItems/8;
				if(numOfItems%8!=0) {
					pagesNeeded+=1;
				}
				request.setAttribute("pagesNeeded", pagesNeeded);
				request.setAttribute("productList", productList);
				session.setAttribute("productList", productList);
				session.setAttribute("category", category);
				session.setAttribute("subCategory", category);
			}
	
		;
		
		}


	//**************************************************END OF SERVLET CALL************************************************
		
		/**
		 * 
		 * @param conn
		 * @return
		 * @throws ServletException 
		 * @throws SQLException
		 */
		protected ArrayList<Product> loadFeaturedProducts() throws ServletException {
			 System.out.println(FileHelper.getMethodName());
			 
			ArrayList<Product> featuredProductList = new ArrayList<Product>();
			try {
				ResultSet resultSet = db.runPreparedStatement(SqlQueries.FEATURED_PRODUCTS_SELECT);
			    while (resultSet.next()) {
			    	Product product = new Product();
			    	String productId = resultSet.getString("PID");
					product.setPid(productId);
					product.setName(resultSet.getString("PRODUCT_NAME"));
					product.setDescription(resultSet.getString("DESCRIPTION"));
					product.setPrice(resultSet.getString("PRICE"));
					product.setQuantity(resultSet.getString("QUANTITY"));
					product.setShortDescription(resultSet.getString("SHORT_DESCRIPTION"));
					product.setCurCode(resultSet.getString("CURRENCY_CODE"));
					//String encodedImage = FileHelper.getEncodedImage(resultSet.getString("PRIMARY_IMAGE"));
					product.setImageURL(resultSet.getString("PRIMARY_IMAGE"));
					//product.setProductAvaliable(resultSet.getString("AVAILABLE"));
					//String encodedImage = "data:image/jpg;base64, "+FileHelper.getEncodedImage(resultSet.getBytes("PRIMARY_IMAGE"));
					//product.setDbImageURL(encodedImage);
			    	featuredProductList.add(product);	
		    	}
			    resultSet.close();
			} catch (SQLException e) {
				CustomException.processError(e);
			}
		return featuredProductList;
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}
		protected ArrayList<Product> loadProductByCategory(String cat, String sub) throws ServletException {
			System.out.println(FileHelper.getMethodName());
			String category=cat;
			String subcategory=sub;
			ArrayList<Product> productList = new ArrayList<Product>();
			try {
				String sqlStatement =SqlQueries.PRODUCTS_SELECT;
				if(category!=null && subcategory==null){
					//System.out.println("Single Category="+category);
					if(category.equals("&amp")){
						category.replace("&amp", "&");
					}
					sqlStatement = "SELECT PROD.PID, PROD.PRODUCT_NAME, PROD.DESCRIPTION, PROD.PRICE, PROD.QUANTITY, PROD.SHORT_DESCRIPTION, PROD.CURRENCY_CODE, IMG.PRIMARY_IMAGE "+
							"FROM BMH_PRODUCTS PROD "+
							"LEFT JOIN BMH_IMAGE_NAMES IMG "+
							"ON PROD.PID=IMG.PID "+
							"WHERE PROD.category like '%"+category+"%' "+
							" AND PROD.DISPLAY_FLAG='Y' "
							+ "ORDER BY PID ";
				}
				if(category!=null && subcategory!=null){
					//System.out.println("Double Category="+category+"\n\tSub Category="+subcategory);
					if(category.equals("&amp")){
						category.replace("&amp", "&");
					}
					if(subcategory.equals("&amp")){
						subcategory.replace("&amp", "&");
					}
					sqlStatement = "SELECT PROD.PID, PROD.PRODUCT_NAME, PROD.DESCRIPTION, PROD.PRICE, PROD.QUANTITY, PROD.SHORT_DESCRIPTION, PROD.CURRENCY_CODE, IMG.PRIMARY_IMAGE "+
							"FROM BMH_PRODUCTS PROD "+
							"LEFT JOIN BMH_IMAGE_NAMES IMG "+
							"ON PROD.PID=IMG.PID "+
							"WHERE PROD.category like '%"+category+"%' and PROD.SUB_category like '%"+subcategory+"%' "
							+" AND PROD.DISPLAY_FLAG='Y' "
							+ "ORDER BY PID ";
				}

				sqlStatement=sqlStatement.replace("&amp;", "&");
				ResultSet resultSet = db.runPreparedStatement(sqlStatement);
				while (resultSet.next()) {
					Product product = new Product();
					product.setPid(resultSet.getString("PID"));
					product.setName(resultSet.getString("PRODUCT_NAME"));
					product.setPrice(resultSet.getString("PRICE"));
					product.setQuantity(resultSet.getString("QUANTITY"));
					product.setShortDescription(resultSet.getString("SHORT_DESCRIPTION"));
					product.setCurCode(resultSet.getString("CURRENCY_CODE"));
					//String encodedImage = FileHelper.getEncodedImage(resultSet.getBytes("PRIMARY_IMAGE"));
					product.setImageURL(resultSet.getString("PRIMARY_IMAGE"));
					productList.add(product);
				}
				resultSet.close();
			} catch (SQLException e) {
				CustomException.processError(e);
			}
			return productList;
		}
	}
