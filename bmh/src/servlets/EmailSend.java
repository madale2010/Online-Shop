package servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.EmailUtility;

/**
 * Servlet implementation class EmailSend
 */
//@WebServlet("/EmailSend")
public class EmailSend extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String host;
	    private String port;
	    private String user;
	    private String pass;
	 
	    public void init() {
	        // reads SMTP server setting from web.xml file
	        ServletContext context = getServletContext();
	        host = context.getInitParameter("host");
	        port = context.getInitParameter("port");
	        user = context.getInitParameter("user");
	        pass = context.getInitParameter("pass");
	    }
	 
	    protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	        // reads form fields
	        String recipient = request.getParameter("email");
	        String subject = request.getParameter("name");
	        String content = request.getParameter("message");
	 
	        String resultMessage = "";
	        String contactType = request.getHeader("referer")!=null ? request.getHeader("referer"): "";
	        if(contactType.contains("contact.jsp")) {
		        // reads form fields
		        recipient = "bluemountainholly@gmail.com";
		        subject = "Message from "+request.getParameter("name")+ " at "+request.getParameter("email");
		        String phone = "Phone Number: "+ request.getParameter("phone")!=null ? request.getParameter("phone")+"\n":"";
		        content = request.getParameter("message");
		        content=phone+content;
		        
	        }
	        try {
	            EmailUtility.sendEmail(host, port, user, pass, recipient, subject, content);
	            resultMessage = "The e-mail was sent successfully";
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            resultMessage = "There were an error: " + ex.getMessage();
	            request.setAttribute(ex.getClass().getName(), ex);
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	        } finally {
	            request.setAttribute("Message", resultMessage);
	            response.sendRedirect(request.getHeader("referer"));
	        }
	    }
}
