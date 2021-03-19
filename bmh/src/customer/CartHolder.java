package customer;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import product.Product;
import product.ProductHelper;
import utils.Cart;
import utils.FileHelper;

/**
 * Servlet implementation class ShopHome
 */
//@WebServlet("/CartHolder")
public class CartHolder extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartHolder() {
    	 super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		System.out.println(FileHelper.getMethodName());
		String action = request.getParameter("action")!=null ? request.getParameter("action"): "";
		if(action.startsWith("remove-")){
			String cartId=action.substring(action.indexOf("-")+1);
			Cart shoppingCart = (Cart) request.getSession().getAttribute("cart");
			for(int i=0; i<shoppingCart.getCartItems().size();i++){
				String productId =shoppingCart.getCartItems().get(i).getPid();
				if(productId.equalsIgnoreCase(cartId)){
					shoppingCart.removeItem(i);
				}
			}
			request.getSession().setAttribute("cartTotal", shoppingCart.getCartItems().size());
			response.sendRedirect(response.encodeRedirectURL(request.getHeader("referer")) );
		}
		else if(action.equalsIgnoreCase("addItem")){
			//Check for cart items
			Cart shoppingCart;
			 if(!request.getSession().isNew()){
				 HttpSession session = request.getSession();
				 shoppingCart = session.getAttribute("cart")!=null ? (Cart) session.getAttribute("cart") : new Cart();
				 session.setAttribute("cart", shoppingCart);
			 } 
			 else { //New session
				 shoppingCart = request.getAttribute("cart")!=null ? (Cart) request.getAttribute("cart") : new Cart();
			 }
			//Because we are on a instansent add we need to set the quanity down to only 1
			// This should only be set if the user selects more that one from the product page.
			String productId =request.getParameter("productId");
			Product item =ProductHelper.loadSingleProduct(productId);
			//Check for existing cart items
			if(!shoppingCart.checkforItem(productId)){
			//For later use we need to restrict to max items we have in stock
			item.setMaxQuantity(item.getQuantity());
			//User only selected 1 for now.
			item.setQuantity("1");
			shoppingCart.addToCart(item);
			} else {
				shoppingCart.updateExistingItem(productId);
			}
			
			
			request.setAttribute("cartTotal", shoppingCart.getCartItems().size());
			request.setAttribute("cart", shoppingCart);
			request.getSession().setAttribute("cartTotal", shoppingCart.getCartItems().size());
			request.getSession().setAttribute("cart", shoppingCart);
			response.sendRedirect(response.encodeRedirectURL(request.getHeader("referer")) );
		}
		else {
			Cart shoppingCart =  (Cart) request.getSession().getAttribute("cart");
			if(shoppingCart!=null){
				//Calculate any taxes and fees 
				shoppingCart.calculateTotals();
				request.setAttribute("cartItems", shoppingCart.getCartItems());
				request.setAttribute("cart", shoppingCart);
				request.getSession().setAttribute("cart", shoppingCart);
				//response.sendRedirect(response.encodeRedirectURL(request.getHeader("referer")) );
				}
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
