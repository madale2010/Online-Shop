package utils;

import java.io.IOException;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class BlogHelper extends Constants {
	
	public BlogHelper(){
		
	}
	public static HttpServletRequest pullBlogPost(HttpServletRequest request) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	String blogId = request.getParameter("blogId")!=null ? request.getParameter("blogId"):"";
		if(!blogId.isEmpty()){
			//Load categories and comments
			request.setAttribute("blogCategories", getBlogCategories());
			request.setAttribute("commentList", getBlogComments(blogId));
			Blog rawBlog = getBlog(blogId);
			request.setAttribute("blog", rawBlog);
			request.setAttribute("blogId", rawBlog.getId());
		}
		return request;
	}
	public static void checkForCommentOnBlog(HttpServletRequest request, String requestURI) {
		// TODO Auto-generated method stub
		String actionId = request.getParameter("action")!=null ? request.getParameter("action"): "";
		String blogId="";
		if(!actionId.isEmpty() && actionId.equalsIgnoreCase("insertComment")){
			//Insert new comment
			String blogComment = request.getAttribute("commentData")!=null ? (String) request.getAttribute("commentData"): "";
			insertBlogComment(blogComment, blogId);
			request.setAttribute("blogCategories", getBlogCategories());
			request.setAttribute("commentList", getBlogComments(blogId));
			Blog rawBlog = getBlog(blogId);
			request.setAttribute("blog", rawBlog);
		}
	}
	public static void insertBlogComment(String comment, String blogId) {
		// TODO Auto-generated method stub
		try {
			Connection conn = DB.getConnection();
			String sql = "INSERT INTO BMH_BLOG_COMMENTS (BLOG_COMMENT,BLOG_ID,COMMENT_DATE) VALUES (?,?,?) ";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, comment);
			statement.setString(2, blogId);
			statement.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
			    System.out.println("A new blog comment was inserted successfully!");
			}
		} catch (SQLException e) {
			CustomException.processError(e);
		}
	}

	/**
	 * Method: testInsertBlogEntry
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param blogObject
	 * @return
	 * 
	 * String
	 */
	public static String insertBlogEntry(Blog blogObject){
		//Need to insert into the db the new blog entry.
		try {
			Connection conn = DB.getConnection();
			String sql = "INSERT INTO BMH_BLOGS (BLOG_TITLE, BLOG_DATE, BLOG_CATEGORY, BLOG_HTML) VALUES (?, ?, ?, ?)";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, blogObject.getTitle());
			statement.setString(2, blogObject.getDate());
			statement.setString(3, blogObject.getCategory());
			Clob temp = DB.getConnection().createClob();
			temp.setString(1, blogObject.getBlogHtml());
			statement.setClob(4,temp);
			int rowsInserted = statement.executeUpdate();
			
			if (rowsInserted > 0) {
			    System.out.println("A new blog was inserted successfully!");
			}
			sql = "select bmh_blogs_seq.CURRVAL from dual ";
			statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				blogObject.setId(String.valueOf(rs.getInt(1)));
			}
			rs.close();
			statement.close();
			
		}
		catch (SQLException e) {
			CustomException.processError(e);
		}
		return blogObject.getId();
	}
	/**
	 * Method: updateBlogEntry
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param blogObject
	 * 
	 * void
	 */
	public static void updateBlogEntry(Blog blogObject){
		System.out.println(FileHelper.getMethodName());
		try {
			Connection conn = DB.getConnection();
			String sql = "UPDATE BMH_BLOGS SET BLOG_TITLE=?, BLOG_DATE=?, BLOG_CATEGORY=?, BLOG_HTML=? "+
					 	 "WHERE BLOG_ID="+blogObject.getId()+"";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, blogObject.getTitle());
			statement.setString(2, blogObject.getDate());
			statement.setString(3, blogObject.getCategory());
			statement.setString(4, blogObject.getBlogHtml());
			int rowsUpdated = statement.executeUpdate();
			
			if (rowsUpdated > 0) {
			    System.out.println("Blog "+blogObject.getId()+" was updated.");
			}
			statement.close();
			
		}
		catch (SQLException e) {
			CustomException.processError(e);
		}
		System.out.println(FileHelper.getMethodName());
	}
	public static void deleteBlogEntry(String blogId) {
		// TODO Auto-generated method stub
		try {
			Connection conn = DB.getConnection();
			String sql = "DELETE FROM BMH_BLOGS WHERE BLOG_ID="+blogId+"";
			PreparedStatement statement = conn.prepareStatement(sql);
			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
			    System.out.println("A blog entry "+blogId+" was deleted.");
			}
		
		} catch (SQLException e) {
			CustomException.processError(e);
		}
	}
	public static Blog getBlog(String blogId) {
		System.out.println(FileHelper.getMethodName());
		Blog currentBlog = new Blog();
		try {
			
			Connection conn = DB.getConnection();
			String sql = "SELECT * FROM BMH_BLOGS WHERE BLOG_ID='"+blogId+"' ";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				currentBlog.setId(resultSet.getString("BLOG_ID"));
				Date commentDate=resultSet.getDate("BLOG_DATE");
				SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");
				currentBlog.setDate(DATE_FORMAT.format(commentDate));
				currentBlog.setCategory(resultSet.getString("BLOG_CATEGORY"));
				currentBlog.setData(resultSet.getString("BLOG_DATA"));
				currentBlog.setBlogHtml(resultSet.getString("BLOG_HTML"));
				currentBlog.setTitle(resultSet.getString("BLOG_TITLE"));
				currentBlog.setType(resultSet.getString("BLOG_IMAGE_EXT"));
				String blogImage = resultSet.getBytes("BLOG_IMAGE")!=null ? Base64.getEncoder().encodeToString(resultSet.getBytes("BLOG_IMAGE")):"";
				currentBlog.setImage(blogImage);
			}
			resultSet.close();
			statement.close();
		
			currentBlog.setBlogImageList(blogId);
			
		} catch (Exception e) {
			CustomException.processError(e);
		}
		System.out.println(FileHelper.getMethodName());
		return currentBlog;
	}

	/**
     * Gather all comments related to this blog id.
     * @param blogId
     * @return
     */
	public static ArrayList<BlogComment> getBlogComments(String blogId) {
		System.out.println(FileHelper.getMethodName());
		ArrayList<BlogComment> commentList = new ArrayList<BlogComment>();
		try {
			
			Connection conn = DB.getConnection();
			String sql = "SELECT BLOG_COMMENT, COMMENT_DATE FROM BMH_BLOG_COMMENTS WHERE BLOG_ID='"+blogId+"' ";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				BlogComment commentObject = new BlogComment();
				commentObject.setComment(resultSet.getString("BLOG_COMMENT"));
				Date commentDate=resultSet.getDate("COMMENT_DATE");
				SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");
				commentObject.setDate(DATE_FORMAT.format(commentDate));
				commentList.add(commentObject);
			}
			resultSet.close();
			statement.close();
			
		} catch (SQLException e) {
			CustomException.processError(e);
		}
		System.out.println(FileHelper.getMethodName());
		return commentList;
	}

	/**
     * Return all the categories for blogs.
     * @return
     */
	public static ArrayList<String> getBlogCategories() {
		System.out.println(FileHelper.getMethodName());
		ArrayList<String> categoryList = new ArrayList<String>();
		ArrayList<String> cleanCategoryList=new ArrayList<String>();
		try {
			
			Connection conn = DB.getConnection();
			String sql = "SELECT BLOG_CATEGORY FROM BMH_BLOGS ";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				categoryList.add(resultSet.getString("BLOG_CATEGORY"));
			}
			
			resultSet.close();
			statement.close();
			
			//Remove any duplicates before sending back
			HashSet<String> set = new HashSet<String>(categoryList);
	        cleanCategoryList = new ArrayList<String>(set);

		} catch (SQLException e) {
			CustomException.processError(e);
		}
		System.out.println(FileHelper.getMethodName());
		return cleanCategoryList;
	}
	public static void pullBlogRecords(HttpServletRequest request){
		System.out.println(FileHelper.getMethodName());
		
		try {
			Connection conn = DB.getConnection();

			ArrayList<Blog> updateBlogList = new ArrayList<Blog>();
			ArrayList<String> blogListHeader = new ArrayList<String>();
			blogListHeader.addAll(Arrays.asList(TableHeaders.BLOG_HEADERS));
			PreparedStatement ps = conn.prepareStatement(SqlQueries.BLOG_SELECT);
			ResultSet resultSet = ps.executeQuery();
		    while (resultSet.next()) {
		    	Blog blogEntry = new Blog();
		    	blogEntry.setData(resultSet.getString("BLOG_HTML"));
		    	blogEntry.setData(resultSet.getString("BLOG_DATA"));
		    	blogEntry.setDate(Constants.DATE_FORMAT.format(resultSet.getDate("BLOG_DATE")));
		    	blogEntry.setCategory(resultSet.getString("BLOG_CATEGORY"));
		    	blogEntry.setId(resultSet.getString("BLOG_ID"));
		    	blogEntry.setTitle(resultSet.getString("BLOG_TITLE"));

		    	updateBlogList.add(blogEntry);
		    }
		    resultSet.close();
		    ps.close();
		   
		    request.setAttribute("blogListHeader", blogListHeader);
		    request.setAttribute("updateBlogList", updateBlogList);
		}
		catch (SQLException e) {
			CustomException.processError(e);
		}
		System.out.println(FileHelper.getMethodName());
		
	}
	public static HttpServletRequest displayBlogEntries(HttpServletRequest request){
		System.out.println(FileHelper.getMethodName());
		try {
			
			Connection conn = DB.getConnection();
			
			request.setAttribute("blogCategories", BlogHelper.getBlogCategories());
			PreparedStatement statement = conn.prepareStatement(SqlQueries.BMH_BLOGS_SELECT);
			ResultSet resultSet = statement.executeQuery();
			ArrayList<Blog> blogger = new ArrayList<Blog>();
			while (resultSet.next()) {
				Blog blogInstance = new Blog();
				blogInstance.setTitle(resultSet.getString("BLOG_TITLE"));
				blogInstance.setData(resultSet.getString("BLOG_DATA"));
				blogInstance.setBlogHtml(resultSet.getString("BLOG_HTML"));
				blogInstance.setCategory(resultSet.getString("BLOG_CATEGORY"));
				blogInstance.setId(resultSet.getString("BLOG_ID"));
				Date blogDate=resultSet.getDate("BLOG_DATE");
				SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");
				
				blogInstance.setDate(DATE_FORMAT.format(blogDate));
				blogInstance.setType(resultSet.getString("BLOG_IMAGE_EXT"));
				
				String blogImage = resultSet.getBytes("BLOG_IMAGE")!=null ? Base64.getEncoder().encodeToString(resultSet.getBytes("BLOG_IMAGE")):"";
				blogInstance.setImage(blogImage);
				
				blogger.add(blogInstance);
			}
			resultSet.close();
			statement.close();
			
			request.setAttribute("blogger", blogger);
		}
		catch (SQLException e) {
			CustomException.processError(e);
		}
		System.out.println(FileHelper.getMethodName());
		return request;
		
	}
}
