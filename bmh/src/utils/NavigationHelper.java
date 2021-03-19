package utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 */
public class NavigationHelper {
 /**
 * Method: pageHandler
 * Author: madal
 * 
 * Description: 
 *
 * @param request
 * @return
 * 
 * HttpServletRequest
 */
public static HttpServletRequest pageHandler(HttpServletRequest request){
	 String action = request.getParameter("action")!=null ? request.getParameter("action"):"";
	 HttpSession session = request.getSession();
	 if(action.equalsIgnoreCase("changePage")){
			System.out.println(FileHelper.getMethodName());
			String page = request.getParameter("page");
			int startIndex=request.getAttribute("start")!=null ? Integer.valueOf((String) request.getAttribute("start")): 0;
			int endIndex =request.getAttribute("end")!=null ? Integer.valueOf((String) request.getAttribute("end")): 0;
				switch (page) {
				case "N":
					request.setAttribute("start", startIndex+8);
					request.setAttribute("end", endIndex+8);
					request.setAttribute("productList", session.getAttribute("productList"));
					request.setAttribute("category", session.getAttribute("category"));
					request.setAttribute("subCategory", session.getAttribute("subCategory"));
					break;
				case "P": 
					request.setAttribute("start", startIndex-8);
					request.setAttribute("end", endIndex-8);
					request.setAttribute("productList", session.getAttribute("productList"));
					request.setAttribute("category", session.getAttribute("category"));
					request.setAttribute("subCategory", session.getAttribute("subCategory"));
					break;
				default :
					int pagerIndex=request.getParameter("page")!=null ? Integer.valueOf((String) request.getParameter("page")):0;
					int newEndIndex=pagerIndex*endIndex;
					//because we only wish to display the 9 items we will simply subtract from end number
					int newStartIndex=newEndIndex-8;
					request.setAttribute("start", newStartIndex);
					request.setAttribute("end", newEndIndex);
					request.setAttribute("productList", session.getAttribute("productList"));
					request.setAttribute("category", session.getAttribute("category"));
					request.setAttribute("subCategory", session.getAttribute("subCategory"));
				}
			}
		return request;
 }
}
