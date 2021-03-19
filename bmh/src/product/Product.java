package product;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Product {

	private String name;
	private String showItem;
	private String description;
	private String shortDescription;
	private String imageURL;
	private String dbImageURL;
	private String price;
	private String quantity;
	private String curCode;
	private String pid;
	private String featured;
	private String category;
	private String subCategory;
	private String tags;
	private String titleTags;
	private String maxQuantity;
	private String available;
	private ProductShippingDetails shipDetails;
	
	private ArrayList<String> imageList;
    public Product(){
    }
   
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescriptiion) {
		this.shortDescription = shortDescriptiion;
	}

	public String getImageURL() {
		return imageURL;
	}

	/**
	 * For now this is pulling images from local file system.
	 * To prevent possible typo's we will do a little searching
	 * here when we pull the primary image for shop display we need
	 * to pull whatever name is linked to the pid.
	 * @param imageURL
	 */
	public void setImageURL(String imageURL) {
		if(imageURL==null){
			this.imageURL=null;
		} 
		else if(imageURL.equalsIgnoreCase("")){
			this.imageURL=null;
		}	
		else {
			this.imageURL=Paths.get("images//Products//"+imageURL).toString();
		}
	}


	public static boolean validateImageExists(String actualPath) {
		if(actualPath==null){
			return false;
		}
		File temp = new File(actualPath);
		if(!temp.isFile()){
			System.out.println("File not found: "+temp.getAbsolutePath());
			return false;
		} else {
			return true;
		}
	}

	public String getPrice() {
		return price;
	}
	public BigDecimal getUnformatedPrice(){
		String formatedPrice=price.replace("$", "");
		return new BigDecimal(formatedPrice);
	}
	public void setPrice(String price) {
		//Default to zero if price wasn't set
		this.price = formatMoney(price);
	}

	private String formatMoney(String price2) {
		if(price2!=null){
			 BigDecimal amount = BigDecimal.valueOf(Double.valueOf(price2));
			 BigDecimal rounded = amount.setScale(2, BigDecimal.ROUND_CEILING);

		return "$"+String.valueOf(rounded);
		}
		return price2;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getCurCode() {
		return curCode;
	}

	public void setCurCode(String curCode) {
		this.curCode = curCode;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public void setFeatured(String featured) {
		// TODO Auto-generated method stub
		this.featured=featured;
	}
	public String getFeatured() {
		if(featured==null){
			featured="N";
		}
		return featured;
	}
	public void setCategory(String category) {
		// TODO Auto-generated method stub
		this.category=category;
	}
	public String getCategory() {
		return category;
	}
	public void setSubCategory(String subCategory) {
		// TODO Auto-generated method stub
		this.subCategory=subCategory;
	}
	public String getSubCategory() {
		return subCategory;
	}

	public void setTitleTags(String titleTags) {
		// TODO Auto-generated method stub
		this.titleTags=titleTags;
	}
	public String getTitleTags(){
		return titleTags;
	}
	public void setTags(String tags) {
		// TODO Auto-generated method stub
		this.tags=tags;
	}
	public String getTags(){
		return tags;
	}
	/**
	 * @return the maxQuantity
	 */
	public String getMaxQuantity() {
		return maxQuantity;
	}

	/**
	 * @param maxQuantity the maxQuantity to set
	 */
	public void setMaxQuantity(String maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	/**
	 * @return the dbImageURL
	 */
	public String getDbImageURL() {
		return dbImageURL;
	}

	/**
	 * @param dbImageURL the dbImageURL to set
	 */
	public void setDbImageURL(String dbImageURL) {
		this.dbImageURL = dbImageURL;
	}
	/**
	 * @return the aDDITIONAL_IMAGES
	 */
	public ArrayList<String> getImageList(){
		return imageList;
	}
	/**
	 * @param aDDITIONAL_IMAGES the aDDITIONAL_IMAGES to set
	 */
	public void setImageList(String imgList) {
		if(imgList!=null){
			imageList=new ArrayList<String>();
			String[] splitList=imgList.split(";");
			for(String image:splitList){
				imageList.add("images//Products//"+image);
			}
		}
	}

	public String getProductAvaliable() {
		return available;
	}

	public void setProductAvaliable(String productAvaliable) {
		this.available = productAvaliable;
	}

	/**
	 * @return the shipDetails
	 */
	public ProductShippingDetails getShipDetails() {
		return shipDetails;
	}

	/**
	 * @param shipDetails the shipDetails to set
	 */
	public void setShipDetails(ProductShippingDetails shipDetails) {
		this.shipDetails = shipDetails;
	}

	/**
	 * @return the showItem
	 */
	public String getShowItem() {
		return showItem;
	}

	/**
	 * @param showItem the showItem to set
	 */
	//@XmlElement(name="showItem")
	public void setShowItem(String showItem) {
		this.showItem = showItem;
	}
}
