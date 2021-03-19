package utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * 
 */

/**
 * @author madal
 *
 */
public class MenuObject extends Constants {
	private ArrayList<String> mainItems = new ArrayList<String>();
	private static ArrayList<String> subItems = new ArrayList<String>();
	private static ArrayList<String> tempArray = null;
	private static HashMap<String, ArrayList<String>> menuMap = new HashMap<String, ArrayList<String>>();
	private static ArrayList<String> uniqueCategories = new ArrayList<String>();
	private static ArrayList<String> uniqueSubCategories = new ArrayList<String>();
	
	
 /**
 * Constructor
 * @param main
 * @param sub
 */
public MenuObject(ArrayList<String> main, ArrayList<String> sub) {
	 if(main.size()!=0){
			setMainCatArray(main,sub);
			menuMap=removeDuplicates(menuMap);
	 }
 }
 /**
 * Method: getMenuMap
 * Author: madal
 * 
 * Description: 
 *
 * @return
 * 
 * HashMap<String,ArrayList<String>>
 */
public HashMap<String, ArrayList<String>> getMenuMap(){
	 return menuMap;
 }
/**
 * @return the mainCatArray
 */
	public ArrayList<String> getMainCatArray() {
		return mainItems;
	}
/**
 * @return the mainSubCatArray
 */
	public ArrayList<String> getSubCatArray() {
		return subItems;
	}
	/**
	 * 
	 * @param mainCatArray
	 * @param subCatArray
	 */
	public static void setMainCatArray(ArrayList<String> mainCatArray, ArrayList<String> subCatArray) {
		//Put each main item in hash so we don't have duplicates
		
		for(int i =0; i<mainCatArray.size();i++){
			//Check for existing key not there add it
			String mainCatString=mainCatArray.get(i).trim();
			if(!menuMap.containsKey(mainCatString)){
				String tempSubIndex = subCatArray.get(i).trim();
				if(mainCatArray.get(i).contains(",")){
					menuMap=handleSpecial(mainCatString, tempSubIndex);
				} else {
					ArrayList<String> tempArray= new ArrayList<String>();
					tempArray.add(tempSubIndex);
					menuMap.put(mainCatString, tempArray);
					tempArray=new ArrayList<String>();
				}
			} else {
				//We have an existing record we only update
				String tempIndex = mainCatArray.get(i).trim();
				String tempSubIndex = subCatArray.get(i).trim();
				tempArray=menuMap.get(tempIndex);
				//Before we add we must handle multiple sub menus
				if(tempSubIndex.contains(",")){
					menuMap.replace(tempIndex, handleSubMenu(tempSubIndex, tempArray));
					
				} else {
					tempArray.add(tempSubIndex);
				}
				tempArray=new ArrayList<String>();
			}
		}
	}
	/**
	 * Take current arrays and go a head and fix any rules
	 * @param mainCatArray
	 * @param subcategory
	 * @return map
	 */
	public static HashMap<String, ArrayList<String>> handleSpecial(String mainCatArray, String subcategory){
		String[] tempMain = mainCatArray.split(",");
		String[] tempSub = subcategory.split(",");
		for(int i =0; i<tempMain.length;i++){
			String curTempMenu=tempMain[i].trim();
			String curTempSup=tempSub[i].trim();
			subItems.add(curTempSup);
			if(menuMap!=null && !menuMap.containsKey(curTempMenu)){
				menuMap.put(curTempMenu, subItems);
				subItems=new ArrayList<String>();
			} else {
				ArrayList<String> temp = new ArrayList<String>();
				temp=menuMap.get(curTempMenu);
				temp.add(curTempSup);
				menuMap.put(curTempMenu, temp);
			}
		}
		return menuMap;
	}
	/**
	 * 
	 * @param subString
	 * @param tempArray
	 * @return subMenu
	 */
	public static ArrayList<String> handleSubMenu(String subString, ArrayList<String> tempArray){

		String[] currentList = subString.split(",");
		for(int i=0; i<currentList.length; i++){
			String currentMenuName=currentList[i].trim();
			tempArray.add(currentMenuName);
		}
		HashSet<String> set = new HashSet<String>(tempArray);
		//Constructing listWithoutDuplicateElements using set
		ArrayList<String> listWithoutDuplicateElements = new ArrayList<String>(set);


		
		return listWithoutDuplicateElements;
	}
	/**
	 * 
	 * @param map
	 * @return map
	 */
	public static HashMap<String, ArrayList<String>> removeDuplicates(HashMap<String, ArrayList<String>> map){
		Iterator<Entry<String, ArrayList<String>>> iter = menuMap.entrySet().iterator();
		HashMap<String, ArrayList<String>> cleanedMap= new HashMap<String, ArrayList<String>>();
		while (iter.hasNext()) {
			 Entry<String, ArrayList<String>> currentItem = iter.next();
			 ArrayList<String> tempArray = currentItem.getValue();
			 HashSet<String> set = new HashSet<String>(tempArray);
				//Constructing listWithoutDuplicateElements using set
				ArrayList<String> listWithoutDuplicateElements = new ArrayList<String>(set);
				cleanedMap.put(currentItem.getKey(), listWithoutDuplicateElements);
		 }
		return cleanedMap;
	}
	/**
	 * Remove duplicates from arraylist
	 * @param list
	 * @return map
	 */
	public static ArrayList<String> removeDuplicates(ArrayList<String> list) {
		HashSet<String> set = new HashSet<String>(list);
	        //Constructing listWithoutDuplicateElements using set
	        ArrayList<String> listWithoutDuplicateElements = new ArrayList<String>(set);
   
		return listWithoutDuplicateElements;
	}
	/**
	 * Print the has map
	 */
	public static void printMenuHash(){
		if(menuMap==null){
			System.out.println("Nothing");
			return;
		}
		 Iterator<Entry<String, ArrayList<String>>> iter = menuMap.entrySet().iterator();

		 while (iter.hasNext()) {
			 Entry<String, ArrayList<String>> currentItem = iter.next();
			 System.out.println("Menu: "+currentItem.getKey());
	
			 Iterator<String> subIter = currentItem.getValue().iterator();
			 while(subIter.hasNext()){
				 System.out.println("\tSub Menu: "+subIter.next()); 
			 }
		 }
	}
	/**
	 * Method: createNewMenu
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * MenuObject
	 */
	public static MenuObject createNewMenu() {
		Connection conn;
		try {
			

			conn=DB.getConnection();
			PreparedStatement ps = conn.prepareStatement(SqlQueries.MENU_ITEMS_SELECT);
			ResultSet resultSet = ps.executeQuery();
			ArrayList<String> main = new ArrayList<String>();
			ArrayList<String> sub = new ArrayList<String>();
			while (resultSet.next()) {
				String mainString = resultSet.getString("category")!=null ? resultSet.getString("category"):"";
				String subString = resultSet.getString("sub_category")!=null ? resultSet.getString("sub_category"):"";
				main.add(mainString);
				sub.add(subString);

			}
			resultSet.close();
			ps.close();
			
			uniqueCategories=main;
			uniqueSubCategories=sub;
			return new MenuObject(main,sub);
		} catch (SQLException e) {
			CustomException.processError(e);
		}
		return null;
	}
	/**
	 * Method: createSimpleMenu
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * MenuObject
	 */
	public static MenuObject createSimpleMenu() {
		Connection conn;
		try {
			

			conn=DB.getConnection();
			PreparedStatement ps = conn.prepareStatement(SqlQueries.SIMPLE_MENU_SELECT);
			ResultSet resultSet = ps.executeQuery();
			ArrayList<String> main = new ArrayList<String>();
			ArrayList<String> sub = new ArrayList<String>();
			while (resultSet.next()) {
				String mainString = resultSet.getString("category")!=null ? resultSet.getString("category"):"";
				String subString = resultSet.getString("sub_category")!=null ? resultSet.getString("sub_category"):"";
				main.add(mainString);
				sub.add(subString);

			}
			resultSet.close();
			ps.close();
			
			uniqueCategories=main;
			uniqueSubCategories=sub;
			return new MenuObject(main,sub);
		} catch (SQLException e) {
			CustomException.processError(e);
		}
		return null;
	}
	/**
	 * Method: getUniqueCategories
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * ArrayList<String>
	 */
	public ArrayList<String> getUniqueCategories() {
		return removeDuplicates(uniqueCategories);
	}

	/**
	 * Method: setUniqueCategories
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param uniqueCategories
	 * 
	 * void
	 */
	public static void setUniqueCategories(ArrayList<String> uniqueCategories) {
		MenuObject.uniqueCategories = uniqueCategories;
	}
	/**
	 * Method: getUniqueSubCategories
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * ArrayList<String>
	 */
	public ArrayList<String> getUniqueSubCategories() {
	
		return removeDuplicates(uniqueSubCategories);
	}
	/**
	 * Method: setUniqueSubCategories
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param uniqueSubCategories
	 * 
	 * void
	 */
	public static void setUniqueSubCategories(ArrayList<String> uniqueSubCategories) {
		MenuObject.uniqueSubCategories = uniqueSubCategories;
	}	
}
