package management;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBManager;
import utils.Blog;
import utils.BlogHelper;
import utils.Constants.SqlQueries;
import utils.CustomException;
import utils.FileHelper;

/**
 * Servlet implementation class UpdateBlog
 */
@MultipartConfig(fileSizeThreshold=1024*1024*10,    // 10 MB 
maxFileSize=1024*1024*50,          // 50 MB
maxRequestSize=1024*1024*100)  
public class UpdateBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBlog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(FileHelper.getMethodName());
		db = (DBManager) getServletContext().getAttribute("db");
		String action = request.getParameter("action")!=null ?request.getParameter("action"):"";
		if(request.getParameter("action")!=null){
			action = request.getParameter("action").toString();
			if(action.equalsIgnoreCase("save")) {
				//Get data need or what we need to insert
				String blogId = request.getParameter("blogId");
				Blog blogObject= blogId!=null ? BlogHelper.getBlog(blogId): new Blog();
				String blogTitle = request.getParameter("blogTitle");
				String blogDate =  request.getParameter("blogDate");
				String blogCategory = request.getParameter("blogCategory");
				String blogHtml = request.getParameter("tinymce");
				//One check here if date is empty just enter the current date
				if(blogDate==null || blogDate.isEmpty()){
					Date today = new Date();
					SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");
					blogDate = DATE_FORMAT.format(today);					
				}
				blogObject.setTitle(blogTitle);
				blogObject.setCategory(blogCategory);
				blogObject.setDate(blogDate);
				blogObject.setBlogHtml(blogHtml);
				
				if(blogId.isEmpty()) {
					BlogHelper.insertBlogEntry(blogObject);
					
				}
				else {
					BlogHelper.updateBlogEntry(blogObject);
				}
				//Last thing is redirect back to page listing
				response.sendRedirect("updateBlog.jsp");
			}
			if(action.equalsIgnoreCase("forward")){
				//request.getRequestDispatcher("blogPost.jsp").forward(request, response);
			}
			if(action.equalsIgnoreCase("updateManager")){
				Blog currentBlog= BlogHelper.getBlog(request.getParameter("blogId"));
				request.setAttribute("blog", currentBlog);
				request.getRequestDispatcher("updateSingleBlog.jsp").forward(request, response);
				 
			}
			if(action.equalsIgnoreCase("delete")){
				String blogId = request.getParameter("blogId");
				BlogHelper.deleteBlogEntry(blogId);
				//Last thing is redirect back to page listing
				response.sendRedirect("updateBlog.jsp");
			}
		} 
		else {
			//Otherwise we must be pulling from main page we need to query the results and send back
			try {
				
				Connection conn = db.getConnection();
				
				request.setAttribute("blogCategories", BlogHelper.getBlogCategories());
				PreparedStatement statement = conn.prepareStatement(SqlQueries.BMH_BLOGS_SELECT);
				ResultSet resultSet = statement.executeQuery();
				ArrayList<Blog> blogger = new ArrayList<Blog>();
				while (resultSet.next()) {
					Blog blogInstance = new Blog();
					blogInstance.setTitle(resultSet.getString("BLOG_TITLE"));
					
					
					blogInstance.setBlogHtml( resultSet.getString("BLOG_HTML"));
					blogInstance.setCategory(resultSet.getString("BLOG_CATEGORY"));
					blogInstance.setId(resultSet.getString("BLOG_ID"));
					Date blogDate=resultSet.getDate("BLOG_DATE");
					SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");
					blogInstance.setDate(DATE_FORMAT.format(blogDate));
	
				
					blogger.add(blogInstance);
				}
				resultSet.close();
				statement.close();
				request.setAttribute("blogger", blogger);
				//request.getSession().setAttribute("blogger", blogger);
			   
			}
			catch (SQLException e) {
				CustomException.processError(e);
			}
		}
		
		System.out.println(FileHelper.getMethodName());
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}
