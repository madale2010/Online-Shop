package customer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.Cart;
import utils.ConfigData;
import utils.MenuObject;

/**
 * Servlet implementation class LoadMenu
 */
@WebServlet("/LoadMenu")
public class LoadMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//Get any configuration setup needed.
		ConfigData config = new ConfigData();
		MenuObject menuObj = MenuObject.createNewMenu();
		if(session.getAttribute("cart")!=null){
			Cart shoppingCart = (Cart) session.getAttribute("cart");
			int cartTotal = shoppingCart.getCartItems().size();
			if(cartTotal>0){
				request.setAttribute("cartTotal", cartTotal);
			} else {
				request.removeAttribute("cartTotal");
			}
		}
		request.setAttribute("mainMenu", menuObj.getMenuMap());
		session.setAttribute("logoImage", config.getLogoName());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
