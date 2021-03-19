package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paypal.api.payments.Error;
import com.paypal.api.payments.ErrorDetails;
import com.paypal.base.rest.PayPalRESTException;

import utils.Constants.Emails;
import utils.Constants.Urls;
import utils.EmailUtility;
import utils.FileHelper;

@WebServlet("/ExceptionHandler")
public class ExceptionHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processError(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processError(request, response);
	}

	private void processError(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println(FileHelper.getMethodName());
		System.out.print("Proccessing New Exception ");
		// Analyze the servlet exception
		Throwable throwable = request.getAttribute("javax.servlet.error.exception")!=null?(Throwable) request.getAttribute("javax.servlet.error.exception"): null;
		int statusCode =request.getAttribute("javax.servlet.error.status_code")!=null ? (Integer) request.getAttribute("javax.servlet.error.status_code") : 500 ;		
		String servletName = request.getAttribute("javax.servlet.error.servlet_name")!=null ? (String) request.getAttribute("javax.servlet.error.servlet_name"):"Unknown";
		String requestUri = request.getAttribute("javax.servlet.error.request_uri")!=null ?(String) request.getAttribute("javax.servlet.error.request_uri"):"Unknown";
		//Request information from user
		String remoteAddress=request.getRemoteAddr();
		String remoteHost=request.getRemoteHost();
		String remotePort=String.valueOf(request.getRemotePort());
		String remoteUser=request.getRemoteUser();
		
		
		String subjectLine = "Servlet: "+servletName+" code="+statusCode;
		StringBuilder messageBody = new StringBuilder();
		if(throwable!=null){
			String cause=throwable.getClass()!=null?throwable.getClass().getSimpleName():"null";
			String detailmessage=throwable.getMessage()!=null?throwable.getMessage():"null";
			String stackTrace = formatStackTrace(throwable);
			messageBody.append("User Info\n");
			messageBody.append("Name :"+remoteUser+"\n");
			messageBody.append("Host :"+remoteHost+"\n Port :"+remotePort+"\n");
			messageBody.append("Address :"+remoteAddress+"\n\n");
			messageBody.append("URL :"+requestUri+"\n");
			
			messageBody.append(subjectLine+"\n");
			messageBody.append("Cause: "+cause+"\n");
			messageBody.append("Detail Message:"+detailmessage+"\n");
			messageBody.append("Stack Trace :\n"+stackTrace+"\n\n");
		}
		System.out.println("code "+statusCode +"\n"+messageBody.toString());
		System.out.println("URI :"+requestUri);
		// Because we want to monitor any errors on the system we need to send notice to email admin.
		// Add filter method to reduce bots error or debug errors
		if(emailFlag(servletName,statusCode,remoteHost)){
			sendEmailError("User :"+request.getRemoteHost()+" "+subjectLine, messageBody.toString());
			System.out.print("Email Notification Sent. ");
		}
		// Set response content type
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Exception/Error Details</title></head><body>");
		if (statusCode == 400 && servletName.contains("LoadCheckoutProcess")|| servletName.contains("ProcessCheckoutRequest")){
			//Check to see if its paypal error
			PayPalRESTException error = (PayPalRESTException) request.getAttribute("com.paypal.base.rest.PayPalRESTException");
			Error details = error.getDetails();
			List<ErrorDetails> detailList = error.getDetails().getDetails();
			
			if(error!=null){
				out.println("<h3>Exception Details</h3>");
				out.println("<ul><li>Servlet Name: " + servletName + "</li>");
				out.println("<li>Exception Name: " + details.getName()+ "</li>");
				out.println("<li>Detail Code: " + details.getCode() + "</li>");
				out.println("<li>Responsecode Code: " + error.getResponsecode() + "</li>");
				out.println("<li>Detail Message: " + details.getMessage() + "</li>");
				for(ErrorDetails d: detailList){
					out.println("<li>Field : " + d.getField() + "</li>");
					out.println("<li>Issues Message: " + d.getIssue() + "</li>");
				}
				out.println("</ul>");
			}
		}
		else if (statusCode != 500) {
			out.println("<h3>Error Details</h3>");
			out.println("<strong>Status Code</strong>:" + statusCode + "<br>");
			out.println("<strong>Requested URI</strong>:" + requestUri);
		} else {
			out.println("<h3>Exception Details</h3>");
			out.println("<ul><li>Servlet Name:" + servletName + "</li>");
			out.println("<li>Exception Name:" + throwable.getClass().getName() + "</li>");
			out.println("<li>Requested URI:" + requestUri + "</li>");
			out.println("<li>Exception Message:" + throwable.getMessage() + "</li>");
			out.println("</ul>");
		}

		out.println("<br><br>");
		out.println("<a href=\"index.jsp\">Home Page</a>");
		out.println("</body></html>");
		out.close();
		
	}

	private boolean emailFlag(String servletName, int statusCode, String remoteHost) {
		if(!remoteHost.equals(Urls.REMOTE_HOST)){
			return false;
		}
		else if(statusCode==404 && servletName.equals("default")){
			return false;
		}
		else {
			return true;
		}
	}

	private String formatStackTrace(Throwable throwable) {
		if(throwable.getStackTrace()!=null){
			StackTraceElement[] stack=throwable.getStackTrace();
			StringBuilder stackBuilder = new StringBuilder();
			for(StackTraceElement e:stack){
				stackBuilder.append(e.toString()+"\n");
			}
			return stackBuilder.toString();
		} 
		else {
			return "";
		}
	}

	private void sendEmailError(String subject, String body) {
		// Do to the important issue around payments we will send a direct email
		// to admin to let them know what happened.
			ServletContext context = getServletContext();
			String host = context.getInitParameter("host");
			String port = context.getInitParameter("port");
			String user = context.getInitParameter("user");
			String pass = context.getInitParameter("pass");
			EmailUtility.sendEmail(host, port, user, pass, Emails.EMAIL_TO, subject,body);
	}
	
}