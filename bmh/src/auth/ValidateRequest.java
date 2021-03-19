package auth;

import javax.servlet.http.HttpSession;

/**
 * 
 */
public class ValidateRequest {
	/**
	 * Method: isAutherized
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param session
	 * @return
	 * 
	 * boolean
	 */
	public static boolean isAutherized(HttpSession session) {
		//System.out.println(FileHelper.getMethodName());
		return session.getAttribute("authUser")!=null ? (boolean) session.getAttribute("authUser") : false;
	}
}
