package management;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import database.DBManager;
import product.Product;
import product.ProductHelper;
import product.ProductShippingDetails;
import utils.Constants.ClassPaths;
import utils.Constants.SqlQueries;
import utils.CustomException;
import utils.FileHelper;
import utils.MenuObject;

/**
 * Servlet implementation class UpdateProduct
 */
//@WebServlet("/UpdateProduct")
@MultipartConfig(fileSizeThreshold=1024*1024*10,    // 10 MB 
maxFileSize=1024*1024*50,          // 50 MB
maxRequestSize=1024*1024*100)  

public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager db;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 System.out.println(FileHelper.getMethodName());
		 db = (DBManager) getServletContext().getAttribute("db");
		String action = request.getParameter("action")!=null ? request.getParameter("action"):"";
		//Check for update of product
		String updateProductId = request.getParameter("update")!=null ? request.getParameter("update"): "";
		String deleteProductId = request.getParameter("delete")!=null ? request.getParameter("delete"): "";
		
		if(action.equalsIgnoreCase("fastUpdate")){
			//Here we only want to do a quick update and push back to the same page
			quickProductUpdate(request, response);
			response.sendRedirect("updateProducts.jsp");
		} 
		else if(action.equalsIgnoreCase("add")){
			try {
				requestNewProduct(request);
			} catch (SQLException e) {
				CustomException.processError(e, request,response);
			}
			//response.sendRedirect("updateSingleProduct.jsp");
			request.getRequestDispatcher("updateSingleProduct.jsp").forward(request, response);
		}
		else if (action.equalsIgnoreCase("insert")) {
			// insert or update new product
			insertProductRecord(request);
			response.sendRedirect("updateProducts.jsp");
		}
		else if (action.equalsIgnoreCase("update")){
			//update new product
			updateProductRecord(request);
			response.sendRedirect("updateProducts.jsp");
		}
		else if(action.equalsIgnoreCase("cancel")){
			//String pathTrace = request.getHeader("referer");
			response.sendRedirect("updateProducts.jsp");
		}
		else if(!deleteProductId.isEmpty()){
			deleteSingleRecord(deleteProductId);
			response.sendRedirect("updateProducts.jsp");
		}
		else if(!updateProductId.equals("")){
			loadProductToUpdate(updateProductId, request, response);
		}
		else {
			// No action so we will pull all records for display
			pullProductRecords(request);
		}
	

	}
	/**
	 * 
	 * @param request
	 * @throws SQLException 
	 */
	private void requestNewProduct(HttpServletRequest request) throws SQLException {
		 System.out.println(FileHelper.getMethodName());
			Product product = new Product();
		
			Connection conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement("select BMH_PRODUCTS_SEQ.NEXTVAL from dual");
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				product.setPid(String.valueOf(resultSet.getInt(1)+1));
			}
			resultSet.close();
			ps.close();
		
		
			MenuObject menuObj = MenuObject.createSimpleMenu();
			request.setAttribute("mainMenu", menuObj);
			request.setAttribute("category", menuObj.getUniqueCategories());
			request.setAttribute("subCategory", menuObj.getUniqueSubCategories());
		
		request.setAttribute("productRecord", product);
		
	}

	/**
	 * We need to pull together the current record on file and set them to the current changes
	 * submited on page.
	 * @param request
	 * @throws IOException
	 * @throws ServletException
	 */
	private void updateProductRecord(HttpServletRequest request) throws IOException, ServletException {
		 System.out.println(FileHelper.getMethodName());
		
		String pid,featured,category,subCategory,name,description,price,quantity,shortDesc,tags,showItem,shipHeight,shipLength,shipWidth,shipPounds,shipOunces;
		pid=request.getParameter("PID");
		featured=request.getParameter("FEATURED")!=null?request.getParameter("FEATURED"):"N";
		showItem=request.getParameter("SHOW")!=null?request.getParameter("SHOW"):"N";
		category=request.getParameter("category");
		subCategory=request.getParameter("subCategories");
		name=request.getParameter("PRODUCT_NAME");
		description=request.getParameter("DESCRIPTION");
		price=request.getParameter("PRICE");
		quantity=request.getParameter("QUANTITY");
		shortDesc=request.getParameter("SHORT_DESCRIPTION");
		tags=request.getParameter("TAGS");
		shipHeight=!request.getParameter("SHIP_HEIGHT").equalsIgnoreCase("")?request.getParameter("SHIP_HEIGHT"):"0.0";
		shipLength=!request.getParameter("SHIP_LENGTH").equalsIgnoreCase("")?request.getParameter("SHIP_LENGTH"):"0.0";
		shipWidth=!request.getParameter("SHIP_WIDTH").equalsIgnoreCase("")?request.getParameter("SHIP_WIDTH"):"0.0";
		shipPounds=!request.getParameter("SHIP_POUNDS").equalsIgnoreCase("")?request.getParameter("SHIP_POUNDS"):"0.0";
		shipOunces=!request.getParameter("SHIP_OUNCES").equalsIgnoreCase("")?request.getParameter("SHIP_OUNCES"):"0.0";
		Part primaryImage;
		primaryImage=request.getPart("PRIMARY_IMAGE");
		

		Product updatedProduct = ProductHelper.loadSingleProduct(pid);
		String formatedCategory=new String(category.getBytes("ISO-8859-1"), "UTF-8");
		String formatedSubCategory=new String(subCategory.getBytes("ISO-8859-1"), "UTF-8");
		String formatedName=new String(name.getBytes("ISO-8859-1"), "UTF-8");
		String formatedDescription=new String(description.getBytes("ISO-8859-1"), "UTF-8");
		updatedProduct.setShowItem(showItem);
		updatedProduct.setFeatured(featured);
		updatedProduct.setCategory(formatedCategory);
		updatedProduct.setSubCategory(formatedSubCategory);
		updatedProduct.setName(formatedName);
		updatedProduct.setDescription(formatedDescription);
		updatedProduct.setPrice(price.replace("$", ""));
		updatedProduct.setQuantity(quantity);
		updatedProduct.setShortDescription(shortDesc);
		updatedProduct.setTags(tags);
		//Shipping information
		ProductShippingDetails shipDetails = new ProductShippingDetails();
		shipDetails.setHeight(Double.valueOf(shipHeight));
		shipDetails.setLength(Double.valueOf(shipLength));
		shipDetails.setWidth(Double.valueOf(shipWidth));
		shipDetails.setOunces(Double.valueOf(shipOunces));
		shipDetails.setPounds(Double.valueOf(shipPounds));
		shipDetails.setProductId(Integer.valueOf(pid));
		updatedProduct.setShipDetails(shipDetails);
		
		//Copy image over
		String primaryImageLocation=FileHelper.copyPrimaryImage(ClassPaths.getImageLocation(), primaryImage, pid);
		String otherImages=FileHelper.copyFilesByPart(ClassPaths.getImageLocation(), request.getParts(), pid, "otherImages");
		if(!primaryImageLocation.isEmpty()) {
			updatedProduct.setImageURL(primaryImageLocation.substring(primaryImageLocation.lastIndexOf(File.separator)+1));
		}
		ProductHelper.updateProduct(updatedProduct);
		ProductHelper.updateProductShipDetails(shipDetails);
		if(!primaryImageLocation.isEmpty() && !otherImages.isEmpty()) {
			ProductHelper.updateProductImageLocation(pid,primaryImageLocation,otherImages);
		}
	}

	/**
	 * Delete a single recorded based on user's request.
	 * @param deleteProductId 
	 * @param request
	 */
	private void deleteSingleRecord(String deleteProductId) {
		// TODO Auto-generated method stub
		ProductHelper.deleteRecord(deleteProductId);
	}

	/**
	 * If we are going to update a single product we need to call the same page as
	 * insert but have the data ready to populate the page.
	 * @param updateProductId
	 * @param request
	 * @param response 
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void loadProductToUpdate(String updateProductId, HttpServletRequest request, ServletResponse response) throws ServletException, IOException {
		 System.out.println(FileHelper.getMethodName());
		 
		if(!updateProductId.isEmpty()){
			MenuObject menuObj = MenuObject.createNewMenu();
			Product product = ProductHelper.loadSingleProduct(updateProductId);
			request.setAttribute("productRecord", product);
			request.setAttribute("category", menuObj.getUniqueCategories());
			request.setAttribute("subCategory", menuObj.getUniqueSubCategories());
			request.getRequestDispatcher("updateSingleProduct.jsp").forward(request, response);
		}
	}
	/**
	 * 
	 * @param updateProductId
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void quickProductUpdate(HttpServletRequest request, ServletResponse response) throws ServletException, IOException {
		String selectedItemId = request.getParameter("pid")!=null?request.getParameter("pid"):"";

		if(!selectedItemId.isEmpty()){
			Product product = ProductHelper.loadSingleProduct(selectedItemId);
			if(request.getParameter("featuredVal")!=null){
				product.setFeatured(request.getParameter("featuredVal"));
			} else {
				product.setShowItem(request.getParameter("showItemVal"));
			}
			
			ProductHelper.updateProduct(product);
			
			
		}
	}
	/**
	 * 
	 * @param request
	 */
	private void pullProductRecords(HttpServletRequest request){
		 System.out.println(FileHelper.getMethodName());
		try {
			Connection conn = db.getConnection();

			ArrayList<Product> updateProductList = new ArrayList<Product>();
			ArrayList<String> productListHeader = new ArrayList<String>();
			String[] tempHeader = {"Featured","PID","Category", "Sub Category","Name","Price",
					"Quantity"};
			productListHeader.addAll(Arrays.asList(tempHeader));
			PreparedStatement ps = conn.prepareStatement(SqlQueries.UPDATE_PRODUCTS_SELECT);
			ResultSet resultSet = ps.executeQuery();
		    while (resultSet.next()) {
		    	Product product = new Product();
				product.setPid(resultSet.getString("PID"));
				product.setFeatured(resultSet.getString("FEATURED"));
				product.setShowItem(resultSet.getString("DISPLAY_FLAG"));
				product.setCategory(resultSet.getString("CATEGORY"));
				product.setSubCategory(resultSet.getString("SUB_CATEGORY"));
				product.setName(resultSet.getString("PRODUCT_NAME"));
				product.setDescription(resultSet.getString("DESCRIPTION"));
				product.setPrice(resultSet.getString("PRICE"));
				product.setQuantity(resultSet.getString("QUANTITY"));
				product.setShortDescription(resultSet.getString("SHORT_DESCRIPTION"));
				product.setImageURL(resultSet.getString("PRIMARY_IMAGE"));
				product.setTitleTags(resultSet.getString("TITLE_TAGS"));
				product.setTags(resultSet.getString("TAGS"));
				
		    	updateProductList.add(product);
		    }
		    resultSet.close();
		    ps.close();
		    request.setAttribute("productListHeader", productListHeader);
		    request.setAttribute("productList", updateProductList);
		    
		}
		catch (SQLException e) {
			CustomException.processError(e);
		}
		
	}
	/**
	 * 
	 * @param request
	 * @throws IOException
	 * @throws ServletException
	 */
	private void insertProductRecord(HttpServletRequest request) throws IOException, ServletException {
		 System.out.println(FileHelper.getMethodName());
		Product product = new Product();
		String pid,featured,category,subCategory,name,description,price,quantity,shortDesc,tags,showItem,shipHeight,shipLength,shipWidth,shipPounds,shipOunces;
		pid=request.getParameter("PID");
		featured=request.getParameter("FEATURED")!=null?request.getParameter("FEATURED"):"N";
		showItem=request.getParameter("SHOW")!=null?request.getParameter("SHOW"):"N";
		category=request.getParameter("category");
		subCategory=request.getParameter("subCategories");
		name=request.getParameter("PRODUCT_NAME");
		description=request.getParameter("DESCRIPTION");
		price=request.getParameter("PRICE");
		quantity=request.getParameter("QUANTITY");
		shortDesc=request.getParameter("SHORT_DESCRIPTION");
		tags=request.getParameter("TAGS");
		shipHeight=!request.getParameter("SHIP_HEIGHT").equalsIgnoreCase("")?request.getParameter("SHIP_HEIGHT"):"0.0";
		shipLength=!request.getParameter("SHIP_LENGTH").equalsIgnoreCase("")?request.getParameter("SHIP_LENGTH"):"0.0";
		shipWidth=!request.getParameter("SHIP_WIDTH").equalsIgnoreCase("")?request.getParameter("SHIP_WIDTH"):"0.0";
		shipPounds=!request.getParameter("SHIP_POUNDS").equalsIgnoreCase("")?request.getParameter("SHIP_POUNDS"):"0.0";
		shipOunces=!request.getParameter("SHIP_OUNCES").equalsIgnoreCase("")?request.getParameter("SHIP_OUNCES"):"0.0";
		Part primaryImage;
		primaryImage=request.getPart("PRIMARY_IMAGE");
		product.setPid(pid);
		product.setFeatured(featured);
		product.setShowItem(showItem);
		String formatedCategory=new String(category.getBytes("ISO-8859-1"), "UTF-8");
		String formatedSubCategory=new String(subCategory.getBytes("ISO-8859-1"), "UTF-8");
		String formatedName=new String(name.getBytes("ISO-8859-1"), "UTF-8");
		String formatedDescription=new String(description.getBytes("ISO-8859-1"), "UTF-8");
		product.setCategory(formatedCategory);
		product.setSubCategory(formatedSubCategory);
		product.setName(formatedName);
		product.setDescription(formatedDescription);
		product.setPrice(price.replace("$", ""));
		product.setQuantity(quantity);
		product.setShortDescription(shortDesc);
		product.setTags(tags);
		//Shipping information
		ProductShippingDetails shipDetails = new ProductShippingDetails();
		shipDetails.setHeight(Double.valueOf(shipHeight));
		shipDetails.setLength(Double.valueOf(shipLength));
		shipDetails.setWidth(Double.valueOf(shipWidth));
		shipDetails.setOunces(Double.valueOf(shipOunces));
		shipDetails.setPounds(Double.valueOf(shipPounds));
		shipDetails.setProductId(Integer.valueOf(pid));
		product.setShipDetails(shipDetails);
		
		String primaryImageLocation=FileHelper.copyPrimaryImage(ClassPaths.getImageLocation(), primaryImage, pid);
		String otherImages=FileHelper.copyFilesByPart(ClassPaths.getImageLocation(), request.getParts(), pid, "otherImages");
		product.setImageURL(primaryImageLocation.substring(primaryImageLocation.lastIndexOf(File.separator)+1));
		ProductHelper.insertProduct(product);
		ProductHelper.insertProductShipDetails(shipDetails);
		ProductHelper.insertProductImageLocation(pid,primaryImageLocation,otherImages);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
