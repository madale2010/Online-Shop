package product;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import usps.PackageRequest;
import usps.ShippingProfile;
import utils.Constants;
import utils.CustomException;
import utils.FileHelper;
import utils.HTMLParser;

public class ProductHelper extends Constants {
	/**
	 * Method will update shipping details for all products
	 * @param productDetailsList
	 */
	public static void updateAllProductDetails(ArrayList<ProductShippingDetails> productDetailsList){
		StringBuilder results= new StringBuilder();
		for(ProductShippingDetails details: productDetailsList){

			try {
				Connection conn = DB.getConnection();
				PreparedStatement statement = conn.prepareStatement(SqlQueries.UPDATE_PRODUCT_DETAILS_ALL);
				statement.setDouble(1, details.getPounds());
				statement.setDouble(2, details.getOunces());
				statement.setDouble(3, details.getLength());
				statement.setDouble(4, details.getWidth());
				statement.setDouble(5, details.getHeight());
				statement.setDouble(6, details.getProductId());
				int rowsInserted=DB.runUpdateStatement(statement);
				if (rowsInserted > 0) {
					results.append("Shipping details for "+details.getProductId()+" where updated.\n");
				}
			} catch (SQLException e) {
				CustomException.processError(e);
			}
		}
		
		System.out.println(results.toString());
	}
	/**
	 * Returns all the profile names.
	 * @return
	 */
	public static ArrayList<String> getShippingProfileNames(){
		ArrayList<ShippingProfile> profileList =getShippingProfiles();
		ArrayList<String> profileNameList = new ArrayList<String>();
		for(ShippingProfile item:profileList){
			profileNameList.add(item.getProfileName());
		}
		return profileNameList;
	}
	/**
	 * Gets all the shipping profiles.
	 * @return
	 */
	public static ArrayList<ShippingProfile> getShippingProfiles(){
		ArrayList<ShippingProfile> profileList = new ArrayList<ShippingProfile>();
		try {
			ResultSet resultSet = DB.runPreparedStatement(SqlQueries.LOAD_SHIPPING_PROFILES);
			while (resultSet.next()) {
				ShippingProfile profile = new ShippingProfile();
				profile.setProfileId(resultSet.getInt("ID"));
				profile.setProfileName(resultSet.getString("PROFILE_NAME"));
				profile.setProcessTime(resultSet.getString("PROCESS_TIME"));
				profile.setFreeShippingDomestic(resultSet.getString("FREE_SHIPPING_DOMESTIC"));
				profile.setFreeShippingInter(resultSet.getString("FREE_SHIPPING_INTER"));
				profile.setHandlingFee(resultSet.getDouble("HANDLING_FEE"));
				profile.setOriginZip(resultSet.getInt("ORIGIN_ZIP"));
				profile.setShipToCountries(resultSet.getString("SHIP_TO_COUNTRIES"));
				profile.setShippingServices(resultSet.getString("SHIPPING_SERVICES"));
				profileList.add(profile);
			}
			resultSet.close();
		} catch(SQLException e){
			CustomException.processError(e);
		}
	
		return profileList;
	}
	/**
	 * Loads all products and returns them in a list
	 * @return
	 */
	public static ArrayList<Product> loadAllProducts(){
		ArrayList<Product> productList = new ArrayList<Product>();
		try {
			ResultSet resultSet = DB.runPreparedStatement(SqlQueries.LOAD_ALL_PRODUCTS);
			while (resultSet.next()) {
				Product product = new Product();
				product.setPid(resultSet.getString("PID"));
				product.setName(resultSet.getString("PRODUCT_NAME"));
				product.setDescription(resultSet.getString("DESCRIPTION"));
				product.setCategory(resultSet.getString("CATEGORY"));
				product.setSubCategory(resultSet.getString("SUB_CATEGORY"));
				product.setPrice(resultSet.getString("PRICE"));
				product.setQuantity(resultSet.getString("QUANTITY"));
				product.setShortDescription(resultSet.getString("SHORT_DESCRIPTION"));
				product.setCurCode(resultSet.getString("CURRENCY_CODE"));
				product.setFeatured(resultSet.getString("FEATURED"));
				product.setImageURL(resultSet.getString("PRIMARY_IMAGE"));
				product.setImageList(resultSet.getString("ADDITIONAL_IMAGES"));
				product.setProductAvaliable(resultSet.getString("AVAILABLE"));
				ProductShippingDetails details = new ProductShippingDetails();
				details.setHeight(resultSet.getInt("SIZE_HEIGHT"));
				details.setLength(resultSet.getInt("SIZE_LENGTH"));
				details.setWidth(resultSet.getInt("SIZE_WIDTH"));
				details.setOunces(resultSet.getInt("WEIGHT_OZ"));
				details.setPounds(resultSet.getInt("WEIGHT_LBS"));
				details.setProfileId(resultSet.getInt("SHIPPING_PROFILE_ID"));
				details.setProfileName(resultSet.getString("PROFILE_NAME"));
				product.setShipDetails(details);
				productList.add(product);
			}
			resultSet.close();
		} catch (SQLException e) {
			CustomException.processError(e);
		}
		
		return productList;
	}
	/**
	 *  Single page load of product object so we need to query all relative data for this.
	 * @param pid
	 * @return 
	 */
	public static Product loadSingleProduct(String pid) {
		 System.out.println(FileHelper.getMethodName());
		Product product = new Product();
		try {


		ResultSet resultSet = DB.runPreparedStatement(SqlQueries.LOAD_SINGLE_PRODUCT, Integer.valueOf(pid));
		while (resultSet.next()) {
			product.setPid(resultSet.getString("PID"));
			product.setTags(resultSet.getString("TAGS"));
			product.setName(resultSet.getString("PRODUCT_NAME"));
			product.setDescription(HTMLParser.toHtml(resultSet.getString("DESCRIPTION")));
			product.setCategory(resultSet.getString("CATEGORY"));
			product.setSubCategory(resultSet.getString("SUB_CATEGORY"));
			product.setPrice(resultSet.getString("PRICE"));
			product.setQuantity(resultSet.getString("QUANTITY"));
			product.setShortDescription(resultSet.getString("SHORT_DESCRIPTION"));
			product.setCurCode(resultSet.getString("CURRENCY_CODE"));
			product.setFeatured(resultSet.getString("FEATURED"));
			product.setShowItem(resultSet.getString("DISPLAY_FLAG"));
			product.setImageURL(resultSet.getString("PRIMARY_IMAGE"));
			product.setImageList(resultSet.getString("ADDITIONAL_IMAGES"));
			product.setProductAvaliable(resultSet.getString("AVAILABLE"));
			ProductShippingDetails details = new ProductShippingDetails();
			details.setHeight(resultSet.getInt("SIZE_HEIGHT"));
			details.setLength(resultSet.getInt("SIZE_LENGTH"));
			details.setWidth(resultSet.getInt("SIZE_WIDTH"));
			details.setOunces(resultSet.getInt("WEIGHT_OZ"));
			details.setPounds(resultSet.getInt("WEIGHT_LBS"));
			details.setProfileId(resultSet.getInt("SHIPPING_PROFILE_ID"));
			product.setShipDetails(details);
		}

		resultSet.close();
		} catch (SQLException e) {
			CustomException.processError(e);;
		}
		
		return product;
	
}

	public static void insertProduct(Product product) {
		// System.out.println(FileHelper.getMethodName());
		Connection conn = DB.getConnection();
		try {				
	
		String sql = "INSERT INTO BMH_PRODUCTS (IMG_NAME,PRODUCT_NAME,CATEGORY,SUB_CATEGORY,TITLE_TAGS,SHORT_DESCRIPTION,DESCRIPTION,PRICE,CURRENCY_CODE,QUANTITY,TAGS,FEATURED, LAST_UPDATE,AVAILABLE) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		 
		PreparedStatement statement = conn.prepareStatement(sql);
		
		statement.setString(1, product.getImageURL());
		statement.setString(2, product.getName());
		statement.setString(3, product.getCategory());
		statement.setString(4, product.getSubCategory());
		statement.setString(5, product.getTitleTags());
		statement.setString(6, product.getShortDescription());
		statement.setString(7, product.getDescription());
		statement.setBigDecimal(8, product.getUnformatedPrice());
		statement.setString(9, product.getCurCode());
		statement.setInt(10, Integer.valueOf(product.getQuantity()));
		statement.setString(11, product.getTags());
		statement.setString(12, product.getFeatured());

		Date date = new Date();
		long t = date.getTime();
		java.sql.Date sqlDate = new java.sql.Date(t);
		statement.setDate(13, sqlDate);
		statement.setString(14, product.getProductAvaliable());
		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
		    System.out.println("A new product "+product.getPid()+" was inserted successfully!");
		}
		} catch (SQLException e) {
			CustomException.processError(e);
		} 
		
	}
	/**
	 * Delete single record from Product and Image tables.
	 * 
	 * @param deleteProductId
	 */
	public static void deleteRecord(String deleteProductId) {
		try {
			Connection conn = null;
			conn = DB.getConnection();
			String formatSql= SqlQueries.DELETE_PRODUCT.replace("?", deleteProductId);
			PreparedStatement statement = conn.prepareStatement(formatSql);
			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
			    System.out.println("Product "+deleteProductId+" was successfully deleted.");
			}
			formatSql=SqlQueries.DELETE_PRODUCT_IMAGES.replace("?", deleteProductId);
			statement = conn.prepareStatement(formatSql);
			rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				 System.out.println("Product Images for "+deleteProductId+" was successfully deleted.");
			}
		} catch (SQLException e) {
			CustomException.processError(e);
		}
		
	}

	public static void updateProduct(Product updatedProduct) {
		Connection conn = null;
		try {				
			conn = DB.getConnection();
			String sql = "UPDATE BMH_PRODUCTS SET IMG_NAME=?,PRODUCT_NAME=?,CATEGORY=?,SUB_CATEGORY=?,TITLE_TAGS=?,"+
					     	"SHORT_DESCRIPTION=?,DESCRIPTION=?,PRICE=?,CURRENCY_CODE=?,QUANTITY=?,TAGS=?,FEATURED=?, LAST_UPDATE=?, AVAILABLE=?, DISPLAY_FLAG=? "+
					     "WHERE PID=? ";
					
			 
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, updatedProduct.getImageURL());
			statement.setString(2, updatedProduct.getName());
			statement.setString(3, updatedProduct.getCategory());
			statement.setString(4, updatedProduct.getSubCategory());
			statement.setString(5, updatedProduct.getTitleTags());
			statement.setString(6, updatedProduct.getShortDescription());
			statement.setString(7, updatedProduct.getDescription());
			statement.setBigDecimal(8, updatedProduct.getUnformatedPrice());
			statement.setString(9, updatedProduct.getCurCode());
			statement.setInt(10, Integer.valueOf(updatedProduct.getQuantity()));
			statement.setString(11, updatedProduct.getTags());
			statement.setString(12, updatedProduct.getFeatured());
	
			Date date = new Date();
			long t = date.getTime();
			java.sql.Date sqlDate = new java.sql.Date(t);
			statement.setDate(13, sqlDate);
			statement.setString(14, updatedProduct.getProductAvaliable());
			statement.setString(15, updatedProduct.getShowItem());
			statement.setString(16, updatedProduct.getPid());
			
			int rowsInserted = DB.runUpdateStatement(statement);
			if (rowsInserted > 0) {
			    System.out.println("Product "+updatedProduct.getPid()+" was updated successfully!");
			}
		} catch (SQLException e) {
			CustomException.processError(e);
		} 
		
	}

	public static void updateProductImageLocation(String pid, String primary, String other) {
		Connection conn = null;
		try {				
		conn = DB.getConnection();
		String sql = "UPDATE BMH_IMAGE_NAMES SET PRIMARY_IMAGE=?,ADDITIONAL_IMAGES=? "+
				     "WHERE PID=? ";
				
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, primary);
		statement.setString(2, other);
		statement.setString(3, pid);


		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
		    System.out.println("Image location for Product "+pid+" was updated successfully!");
		}
		} catch (SQLException e) {
			CustomException.processError(e);
		} 

	}

	public static void insertProductImageLocation(String pid, String primary, String otherImages) {
		Connection conn = null;
		try {				
		conn = DB.getConnection();
		String sql = "INSERT INTO BMH_IMAGE_NAMES (PID,PRIMARY_IMAGE,ADDITIONAL_IMAGES) "
				+ "VALUES (?,?,?) ";
		if(primary.contains(File.separator)){
			primary=primary.substring(primary.lastIndexOf(File.separator)+1);
		}
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, pid);
		statement.setString(2, primary);
		statement.setString(3, otherImages);

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
		    System.out.println("A new image location for "+pid+" was inserted successfully!");
		}
		} catch (SQLException e) {
			CustomException.processError(e);
		}

	}
	/**
	 * Will get shipping profile for product and setup data needed
	 * to request shipping estimate from usps.
	 * @param productId
	 * @param inputZip 
	 * @return
	 */
	public static PackageRequest getShippingMethod(String productId, String inputZip) {
		PackageRequest packageObj = new PackageRequest();
		try {
			ResultSet rs= DB.runPreparedStatement(SqlQueries.BMH_SHIPPING_METHOD, Integer.valueOf(productId));
			int profileId=0;
			String pounds = null,ounces = null,width = null,length = null,height = null;
			while(rs.next()){
				profileId=rs.getInt("SHIPPING_PROFILE_ID");
				pounds=String.valueOf(rs.getInt("WEIGHT_LBS"));
				ounces=String.valueOf(rs.getInt("WEIGHT_OZ"));
				width=String.valueOf(rs.getInt("SIZE_WIDTH"));
				length=String.valueOf(rs.getInt("SIZE_LENGTH"));
				height=String.valueOf(rs.getInt("SIZE_HEIGHT"));
			}
			rs.close();
			if(profileId==1){
				
				packageObj.setService("First Class");
				packageObj.setFirstClassMailType("PARCEL");
				packageObj.setZipOrigination("24210");
				packageObj.setZipDestination(inputZip);
				packageObj.setPounds(pounds);
				packageObj.setOunces(ounces);
				packageObj.setContainer("Rectangular");
				packageObj.setSize("REGULAR");
				packageObj.setWidth(width);
				packageObj.setLength(length);
				packageObj.setHeight(height);
				packageObj.setGirth("0");
				packageObj.setID("0");
				packageObj.setMachinable("TRUE");
			}
		} catch(SQLException e){
			CustomException.processError(e);
		}

		// TODO Auto-generated method stub
		return packageObj;
	}
	/**
	 * @param shipDetails
	 */
	public static void insertProductShipDetails(ProductShippingDetails shipDetails) {
		Connection conn = null;
		try {				
		conn = DB.getConnection();
		String sql = "INSERT INTO BMH_SHIPPING_DETAILS (PID,WEIGHT_LBS,WEIGHT_OZ,SIZE_LENGTH,SIZE_WIDTH,SIZE_HEIGHT,SHIPPING_PROFILE_ID) "
				+ "VALUES (?,?,?,?,?,?,?) ";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, shipDetails.getProductId());
		statement.setDouble(2, shipDetails.getPounds());
		statement.setDouble(3, shipDetails.getOunces());
		statement.setDouble(4, shipDetails.getLength());
		statement.setDouble(5, shipDetails.getWidth());
		statement.setDouble(6, shipDetails.getHeight());
		statement.setInt(7, shipDetails.getProfileId());

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
		    System.out.println("Added shipping details for "+shipDetails.getProductId()+" was successful.");
		}
		} catch (SQLException e) {
			CustomException.processError(e);
		} 

	}
	/**
	 * @param shipDetails
	 */
	public static void updateProductShipDetails(ProductShippingDetails shipDetails) {
		Connection conn = null;
		try {				
		conn = DB.getConnection();
		String sql = "UPDATE BMH_SHIPPING_DETAILS SET WEIGHT_LBS=?,WEIGHT_OZ=?,SIZE_LENGTH=?,SIZE_WIDTH=?,SIZE_HEIGHT=?,SHIPPING_PROFILE_ID=? WHERE PID=? ";

		PreparedStatement statement = conn.prepareStatement(sql);
		
		statement.setDouble(1, shipDetails.getPounds());
		statement.setDouble(2, shipDetails.getOunces());
		statement.setDouble(3, shipDetails.getLength());
		statement.setDouble(4, shipDetails.getWidth());
		statement.setDouble(5, shipDetails.getHeight());
		statement.setInt(6, shipDetails.getProfileId());
		statement.setInt(7, shipDetails.getProductId());
		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
		    System.out.println("Updated shipping details for "+shipDetails.getProductId()+".");
		}
		} catch (SQLException e) {
			CustomException.processError(e);
		} 
		
	}
}
