package utils;


import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 */
public class CustomException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public CustomException()
	{
	}

	/**
	 * Constructor
	 * @param message
	 */
	public CustomException(String message)
	{
		super(message);
	}

	/**
	 * Constructor
	 * @param cause
	 */
	public CustomException(Throwable cause)
	{
		super(cause);
	}

	/**
	 * Constructor
	 * @param message
	 * @param cause
	 */
	public CustomException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Constructor
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
	

	/**
	 * @param e
	 */
	public static void processError(Exception e) {
		//throw new CustomException(e);
		try {
			throw new ServletException(e);
		} catch (ServletException e1) {
			
			Logger logger = Logger.getLogger(CustomException.class.getName());
			logger.log(Level.SEVERE, e.getMessage(), e);
			   StringBuilder stringBuilder = new StringBuilder();
			   
			    for(StackTraceElement stackTraceElement : e.getStackTrace()) {
			      stringBuilder.append(stackTraceElement);
			      stringBuilder.append("\n");
			    }
			    
			//EmailUtility.sendEmail(Constants.Emails.EMAIL_TO, "Internal BMH Error", stringBuilder.toString());
		}
	}
	/**
	 * @param e
	 * @param request 
	 * @param response 
	 */
	public static void processError(Exception e,HttpServletRequest request, HttpServletResponse response){
		try {
			throw new CustomException(e);
		} catch (CustomException e1) {
			
			Logger logger = Logger.getLogger(CustomException.class.getName());
			logger.log(Level.SEVERE, e.getMessage(), e);
			   StringBuilder stringBuilder = new StringBuilder();
			   
			    for(StackTraceElement stackTraceElement : e.getStackTrace()) {
			      stringBuilder.append(stackTraceElement);
			      stringBuilder.append("\n");
			    }
			    
			//EmailUtility.sendEmail(Constants.Emails.EMAIL_TO, "BMH Error "+request.getRequestURL(), stringBuilder.toString());
		}
	}
}
