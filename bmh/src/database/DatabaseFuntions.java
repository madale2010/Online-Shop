/**
 * Project: bmh
 * Package: database
 * File: DatabaseFuntions.java
 * 
 * Author: madal
 * Date: Jul 9, 2016
 * 
 * Description: 
 * 
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.Constants;

/**
 * 
 */
public class DatabaseFuntions {

	/**
	 * Method: updateConfigOptions
	 * Author: madal
	 * 
	 * Description: 
	 * @param option 
	 * @param data 
	 *
	 * void
	 */
	public static void updateConfigOptions(String option, String data) {
		Connection conn=Constants.DB.getConnection();
		
		String sql = "UPDATE BMH_CONFIG_OPTIONS SET DATA=? WHERE TYPE=? ";
		try {
			
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, data);
			statement.setString(2, option);
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
			    System.out.println("Configuration for "+option+" was updated successfully!");
			}
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
