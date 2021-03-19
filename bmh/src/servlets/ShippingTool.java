package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import product.Product;
import product.ProductHelper;
import product.ProductShippingDetails;
import shippo.RateDetails;
import shippo.ShippingEngine;
import usps.PackageRequest;
import usps.PackageResponse;
import usps.RateV4Request;
import usps.UspsServices;
import utils.Cart;
import utils.FileHelper;

/**
 * Servlet implementation class ShippingTool
 */
//@WebServlet("/ShippingTool")
public class ShippingTool extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShippingTool() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(FileHelper.getMethodName());
		String action = request.getParameter("action")!=null ? request.getParameter("action"):"";
		String jspCalled=request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/")+1);
		if(action.equalsIgnoreCase("updateCartTotals")){
			HttpSession session = request.getSession();
			Cart shoppingCart = (Cart) session.getAttribute("cart");
			@SuppressWarnings("unchecked")
			ArrayList<RateDetails> rateList = (ArrayList<RateDetails>) session.getAttribute("rateList");
			//Get the shipping method choosen
			String shippingMethod=request.getParameter("shippingMethod");
			for(RateDetails rate:rateList){
				if(shippingMethod.equalsIgnoreCase(rate.getServiceName())){
					shoppingCart.setRateDetails(rate);
					shoppingCart.setShippingTotal(new BigDecimal(rate.getAmount()));
				}
			}
			
			shoppingCart.calculateTotals();
			session.setAttribute("cart", shoppingCart);
			request.setAttribute("cart", shoppingCart);
			session.setAttribute("shippingMethod", shippingMethod);
			request.setAttribute("shippingMethod", shippingMethod);
			
		}
		if(jspCalled.equalsIgnoreCase("ShippingTool") && action.equalsIgnoreCase("cartCalculate")){
			//Grap the cart item, selected shipping method and zip and redo totals
			HttpSession session = request.getSession();
			Cart shoppingCart = (Cart) session.getAttribute("cart");
			if(!request.getParameter("inputZip").isEmpty()){
				int inputZip = Integer.valueOf(request.getParameter("inputZip"));
				session.setAttribute("inputZip", inputZip);
				if(shoppingCart!=null){
				shoppingCart.reCalculateShipping(inputZip);
				shoppingCart.setRateDetails(shoppingCart.getRateObject().get(0));
				session.setAttribute("rateList", shoppingCart.getRateObject());
				session.setAttribute("cart", shoppingCart);
				}
			}
			System.out.println("DEBUG   request for shipping cart calculate");
			response.sendRedirect(response.encodeRedirectURL(request.getHeader("referer")) );
		}
		if(jspCalled.equalsIgnoreCase("shippingTool.jsp") && request.getAttribute("productList")==null){
			ArrayList<Product> productList = ProductHelper.loadAllProducts();
			ArrayList<String> profileNameList = ProductHelper.getShippingProfileNames();
 			request.setAttribute("productList", productList);
 			request.setAttribute("profileNameList", profileNameList);
		}
		if(jspCalled.equalsIgnoreCase("ShippingTool") && action.equalsIgnoreCase("update")){
			//Collect all rows and peice them back into an object
			String[] pidNames =request.getParameterValues("pid");
			ArrayList<ProductShippingDetails> productDetailsList =new ArrayList<ProductShippingDetails>();
			
			for(String id:pidNames){
				String pounds=request.getParameter(id+"_pounds");
				String ounces=request.getParameter(id+"_ounces");
				String length=request.getParameter(id+"_length");
				String width=request.getParameter(id+"_width");
				String height=request.getParameter(id+"_height");
				String profileName=request.getParameter(id+"_profile_select");
				ProductShippingDetails productDetails = new ProductShippingDetails();
				productDetails.setHeight(Integer.valueOf(height));
				productDetails.setLength(Integer.valueOf(length));
				productDetails.setOunces(Integer.valueOf(ounces));
				productDetails.setPounds(Integer.valueOf(pounds));
				productDetails.setProductId(Integer.valueOf(id));
				productDetails.setProfileId(Integer.valueOf("1"));
				productDetails.setProfileName(profileName);
				productDetails.setWidth(Integer.valueOf(width));
				productDetailsList.add(productDetails);
			}
			ProductHelper.updateAllProductDetails(productDetailsList);
			System.out.println(request.getHeader("referer"));
			response.sendRedirect(response.encodeRedirectURL(request.getHeader("referer")) );
		}
		if(action.equalsIgnoreCase("calculate")){
			if(request.getParameter("pid")!=null){
				String productId=request.getParameter("pid");
				String inputZip=request.getParameter("inputZip");
				
				request.getSession().setAttribute("rateList",ShippingEngine.getShippingRates(productId, inputZip));
			}
			response.sendRedirect(response.encodeRedirectURL(request.getHeader("referer")) );
		}
		if(action.equalsIgnoreCase("check")){
			UspsServices uspsService = new UspsServices();
			RateV4Request rate = new RateV4Request();
			PackageRequest packageObj = new PackageRequest();
			packageObj.setService(request.getParameter("service_select"));
			packageObj.setFirstClassMailType(request.getParameter("mail_type_select"));
			packageObj.setZipOrigination("24210");
			packageObj.setZipDestination(request.getParameter("ZipDestination"));
			packageObj.setPounds(request.getParameter("Pounds"));
			packageObj.setOunces(request.getParameter("Ounces"));
			packageObj.setContainer(request.getParameter("container_select"));
			packageObj.setSize(request.getParameter("size_select"));
			packageObj.setWidth(request.getParameter("Width"));
			packageObj.setLength(request.getParameter("Length"));
			packageObj.setHeight(request.getParameter("Height"));
			packageObj.setGirth(request.getParameter("Girth"));
			packageObj.setID(request.getParameter("PackageId"));
			packageObj.setMachinable(request.getParameter("machine_select"));
			ArrayList<PackageRequest> list = new ArrayList<PackageRequest>();
			list.add(packageObj);
			rate.setPackageObj(list);
			PackageResponse resultPackage=uspsService.requestShipping(packageObj);
			
			if(resultPackage!=null && resultPackage.getError()==null){
				System.out.println("Shipping Cost :"+resultPackage.getPostage().getRate());
				request.getSession().setAttribute("amount", resultPackage.getPostage().getRate());
			} else if(resultPackage.getError()!=null) {
				request.setAttribute("error", resultPackage.getError().toFormatedXML());
				request.setAttribute("errorMessage", resultPackage.getError().getDescription());
				System.out.println(resultPackage.getError().getNumber());
				System.out.println(resultPackage.getError().getDescription());
			}
			System.out.println(request.getHeader("referer"));
			response.sendRedirect(response.encodeRedirectURL(request.getHeader("referer")));
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
