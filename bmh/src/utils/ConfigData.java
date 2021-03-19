package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 */
public class ConfigData extends Constants {
	private String logoName;
	private String activeFont;
	private List<String> fontsList;
	private List<String> carouselList;
	private List<String> category;
	private List<String> subCategory;
	
	/**
	 * Constructor
	 */
	public ConfigData(){
		loadDefaults();
	}
	/**
	 * @return the logoName
	 */
	public String getLogoName() {
		return logoName;
	}
	/**
	 * @param logoName the logoName to set
	 */
	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}
	/**
	 * @return the activeFont
	 */
	public String getActiveFont() {
		return activeFont;
	}
	/**
	 * @param activeFont the activeFont to set
	 */
	public void setActiveFont(String activeFont) {
		this.activeFont = activeFont;
	}
	/**
	 * @return the fontsList
	 */
	public List<String> getFontsList() {
		return fontsList;
	}
	/**
	 * @param fontsList the fontsList to set
	 */
	public void setFontsList(ArrayList<String> fontsList) {
		this.fontsList = fontsList;
	}
	/**
	 * @return the carouselList
	 */
	public List<String> getCarouselList() {
		for(String item: carouselList) {
			item="image/"+item;
		}
		return carouselList;
	}
	/**
	 * @param carouselList the carouselList to set
	 */
	public void setCarouselList(ArrayList<String> carouselList) {
		this.carouselList = carouselList;
	}
	/**
	 * Method: loadDefaults
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * 
	 * void
	 */
	public void loadDefaults() {
		try {
			Connection conn = DB.getConnection();
			String sql = "SELECT * FROM BMH_CONFIG_OPTIONS ";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String type = resultSet.getString("TYPE");
				String data = resultSet.getString("DATA");
				switch(type) {
					case "CAROUSEL": 
						carouselList = data!=null?Arrays.asList(data.split(";")):new ArrayList<String>();
						break;
					case "FONTS" :
						fontsList = data!=null?Arrays.asList(data.split(";")):new ArrayList<String>();
						break;
					case "CATEGORIES" :
						category = data!=null?Arrays.asList(data.split(";")):new ArrayList<String>();
						break;
					case "SUBCATEGORIES":
						subCategory = data!=null?Arrays.asList(data.split(";")):new ArrayList<String>();
						break;
					case "LOGO" :
						logoName="images/"+data;
						break;
				}	
			}
			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			CustomException.processError(e);
		}
		
	}
	/**
	 * @return the category
	 */
	public List<String> getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(ArrayList<String> category) {
		this.category = category;
	}
	/**
	 * @return the subCategory
	 */
	public List<String> getSubCategory() {
		return subCategory;
	}
	/**
	 * @param subCategory the subCategory to set
	 */
	public void setSubCategory(ArrayList<String> subCategory) {
		this.subCategory = subCategory;
	}
	/**
	 * Method: addCategory
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param categoryName
	 * 
	 * void
	 */
	public void addCategory(String categoryName) {
		this.category.add(categoryName);
	}
	
	/**
	 * Method: addSubCategory
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param subCatName
	 * 
	 * void
	 */
	public void addSubCategory(String subCatName) {
		this.subCategory.add(subCatName);
	}
	/**
	 * Method: getCategoryString
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getCategoryString() {
		StringBuffer tempBuf = new StringBuffer();
		for(String item: category) {
			tempBuf.append(item+";");
		}
		return tempBuf.toString();
	}
	/**
	 * Method: getSubCategoryString
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getSubCategoryString() {
		StringBuffer tempBuf = new StringBuffer();
		for(String item: subCategory) {
			tempBuf.append(item+";");
		}
		return tempBuf.toString();
	}
}
