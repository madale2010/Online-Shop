package utils;

import java.io.File;
import java.util.ArrayList;

import utils.Constants.ClassPaths;

public class Blog {
	private String date;
	private String data;
	private String image;
	private String title;
	private String type;
	private String category;
	private String id;
	private ArrayList<String> blogImageList;
	private String blogHtml;
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param blogImage the image to set
	 */
	public void setImage(String blogImage) {
		this.image = blogImage;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * 
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the blogImageList
	 */
	public ArrayList<String> getBlogImageList() {
		
		return blogImageList;
	}
	/**
	 * @param blogImageList the blogImageList to set
	 */
	public void setBlogImageList(String id) {
		ArrayList<String> tempList = new ArrayList<String>();
		this.blogImageList= new ArrayList<String>();
		tempList = FileHelper.findBlogImagesByPid(id+"_", ClassPaths.getBlogLocation().toFile());
		//Here we will reset path
		if(tempList!=null){
		for(String temp:tempList){
			temp=temp.substring(temp.lastIndexOf("\\")+1,temp.length());
			this.blogImageList.add("images"+File.separator+"Blog"+File.separator+temp);
			//this.blogImageList.add(temp);

		}
		}
	}
	/**
	 * @return the blogHtml
	 */
	public String getBlogHtml() {
		return blogHtml;
	}
	/**
	 * @param blogHtml the blogHtml to set
	 */
	public void setBlogHtml(String blogHtml) {
		this.blogHtml = blogHtml;
	}

}
