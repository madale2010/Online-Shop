package customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.BlogHelper;

/**
 * Servlet implementation class Blogger
 */
//@WebServlet("/Blogger")
public class Blogger extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Blogger() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get blog categories
		String action = request.getParameter("action")!=null ? request.getParameter("action") : "";
		String url = request.getRequestURL().toString();
	    String queryString = request.getQueryString();

	    String fullUrl = url + (queryString==null ? "" : ("?" + queryString));
		request.setAttribute("currentUrl",fullUrl);
		if(action.isEmpty()){
			request.setAttribute("blogCategories", BlogHelper.getBlogCategories());
			request=BlogHelper.displayBlogEntries(request);
		}
		if(action.equals("single")){
			request=BlogHelper.pullBlogPost(request);
		}
		if(action.equals("insertComment")){
			String blogId = request.getParameter("blogId")!=null ? request.getParameter("blogId"):"";
			String commentData = request.getParameter("commentData")!=null ? request.getParameter("commentData"):"";
			BlogHelper.insertBlogComment(commentData,blogId);
			request=BlogHelper.pullBlogPost(request);
			response.sendRedirect(request.getHeader("referer"));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
