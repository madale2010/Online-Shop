package management;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import database.DatabaseFuntions;
import utils.ConfigData;
import utils.Constants;
import utils.FileHelper;
import utils.MenuObject;

/**
 * Servlet implementation class ConfigSettings
 */
@MultipartConfig(fileSizeThreshold=1024*1024*10,    // 10 MB 
maxFileSize=1024*1024*50,          // 50 MB
maxRequestSize=1024*1024*100) 

public class ConfigSettings extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfigSettings() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action")!=null ? request.getParameter("action"):"";
		MenuObject menuObj = MenuObject.createNewMenu();
		//Request config data on load
		ConfigData config = new ConfigData();
		request.setAttribute("fontsList", config.getFontsList());
		request.setAttribute("carouselList", config.getCarouselList());
		request.setAttribute("logoImage", config.getLogoName());
		request.setAttribute("categoriesList", config.getCategory());
		request.setAttribute("subCategoriesList", config.getSubCategory());
		
		if(request.getParameter("addCategoryName")!=null){
			ArrayList<String> newList=new ArrayList<String>(config.getCategory());
			newList.add(request.getParameter("addCategoryName"));
			config.setCategory(newList);
			updateMenuOptions(config.getCategoryString(), "");
			request.setAttribute("categoriesList", config.getCategory());
			response.sendRedirect("pageProperties.jsp");
			
		}
		else if(request.getParameter("addSubCategoryName")!=null){
			ArrayList<String> newList=new ArrayList<String>(config.getSubCategory());
			newList.add(request.getParameter("addSubCategoryName"));
			config.setSubCategory(newList);
			updateMenuOptions("", config.getSubCategoryString());
			request.setAttribute("subCategoriesList", config.getSubCategory());
			response.sendRedirect("pageProperties.jsp");
		}
		else if(action.equalsIgnoreCase("save")){
			Part logoPart=request.getPart("logoImage");
			updateLogoOptions(logoPart);
			updateFontOptions();
			//updateMenuOptions();
			updateCarouselOptions(request.getParts());
			response.sendRedirect("management.jsp");
			
		}
		else {

			request.setAttribute("mainMenu", menuObj.getMenuMap());
			request.setAttribute("category", menuObj.getUniqueCategories());
			request.setAttribute("subCategory", menuObj.getUniqueSubCategories());
			response.sendRedirect(request.getHeader("referer"));
			
		}
	}

	/**
	 * @param parts 
	 * @throws IOException 
	 * 
	 */
	private void updateCarouselOptions(Collection<Part> parts) throws IOException {
		StringBuffer tempString = new StringBuffer();
        for (Part part : parts) {
            String absoluteFileName = FileHelper.extractFileName(part);
            String partName=part.getName();
            String fileName = new File(absoluteFileName).getName();
            if(!absoluteFileName.isEmpty() && "carouselImages".equalsIgnoreCase(partName)){
            	//Copy the file over
            	fileName=FileHelper.copyFileToServer(Constants.ClassPaths.getBasePath(), part, "carousel");
            	//Update config table for reference
            	tempString.append(fileName+";");
            }
        }
        String longString=tempString.toString();
        if(!longString.isEmpty()) {
        	DatabaseFuntions.updateConfigOptions("CAROUSEL",longString);
        }
		
	}

	/**
	 * @param subCategory 
	 * @param category 
	 * 
	 */
	private void updateMenuOptions(String category, String subCategory) {
		// Update on item at a time
		if(!category.isEmpty()) {
			DatabaseFuntions.updateConfigOptions("CATEGORIES",category);
		} else {
			//update sub add sub
			DatabaseFuntions.updateConfigOptions("SUBCATEGORIES",subCategory);
		}
		
	}

	/**
	 * 
	 */
	private void updateFontOptions() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param logoPart 
	 * @throws IOException 
	 * 
	 */
	private void updateLogoOptions(Part logoPart) throws IOException {
		//Copy the file over
    	String fileName = FileHelper.copyFileToServer(Constants.ClassPaths.getBasePath(), logoPart, "LOGO");
    	if(!fileName.isEmpty()) {
    		DatabaseFuntions.updateConfigOptions("LOGO",fileName);
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
