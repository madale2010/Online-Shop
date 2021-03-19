package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * 
 */
public class Alert extends Constants {
	private int alertId;
	private String alertType;
	private String alertName;
	private Timestamp alertDate;
	private String alertDescription;
	private String alertLink;
	/**
	 * Default Constructor
	 */
	public Alert() {
	}
	/**
	 * Constructor
	 * @param name
	 * @param desc
	 * @param type
	 */
	public Alert(String name, String desc, String type){
		this.alertDate= new java.sql.Timestamp(System.currentTimeMillis());
		this.alertName=name;
		this.alertType=type;
		this.alertDescription=desc;
		
	}
	/**
	 * Method: getAlertType
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getAlertType() {
		return alertType;
	}
	/**
	 * Method: setAlertType
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param alertType
	 * 
	 * void
	 */
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}
	/**
	 * Method: getAlertName
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getAlertName() {
		return alertName;
	}
	/**
	 * Method: setAlertName
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param alertName
	 * 
	 * void
	 */
	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}
	/**
	 * Method: getAlertDate
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * Timestamp
	 */
	public Timestamp getAlertDate() {
		return alertDate;
	}
	/**
	 * Method: setAlertDate
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param alertDate
	 * 
	 * void
	 */
	public void setAlertDate(Timestamp alertDate) {
		this.alertDate = alertDate;
	}
	/**
	 * Method: getAlertDescription
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public String getAlertDescription() {
		return alertDescription;
	}
	/**
	 * Method: setAlertDescription
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param alertDescription
	 * 
	 * void
	 */
	public void setAlertDescription(String alertDescription) {
		this.alertDescription = alertDescription;
	}
	/**
	 * Method: addNewAlert
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * 
	 * void
	 */
	public void addNewAlert(){
		Connection conn = DB.getConnection();
		String sql = "INSERT INTO BMH_ALERTS (ALERT_TYPE,ALERT_NAME,ALERT_DESCRIPTION,ALERT_DATE,ALERT_LINK) "
				+ "VALUES (?,?,?,?,?) ";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, this.alertType);
			statement.setString(2, this.alertName);
			statement.setString(3, this.alertDescription);
			statement.setTimestamp(4, this.alertDate);
			statement.setString(5, this.alertLink);
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
			    System.out.println("A new alert was inserted successfully!");
			}
			statement.close();
			
			
		} catch (SQLException e) {
			CustomException.processError(e);
		}
	}
	/**
	 * Method: updateAlert
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * 
	 * void
	 */
	public void updateAlert(){
		Connection conn = DB.getConnection();
		String sql = "UPDATE BMH_ALERTS SET ALERT_TYPE=?,ALERT_NAME=?,ALERT_DESCRIPTION=?,ALERT_DATE=?,ALERT_LINK=? "
				+ "WHERE ALERT_ID=? ";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, this.alertType);
			statement.setString(2, this.alertName);
			statement.setString(3, this.alertDescription);
			statement.setTimestamp(4, this.alertDate);
			statement.setString(5, this.alertLink);
			statement.setInt(6, this.alertId);
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
			    System.out.println("A alert was updated successfully!");
			}
			statement.close();
			
		} catch (SQLException e) {
CustomException.processError(e);
			e.printStackTrace();
		}
	}
	/**
	 * Method: addNewAlertWithEmail
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * 
	 * void
	 */
	public void addNewAlertWithEmail() {
		this.addNewAlert();
		//Send email
		//TODO: need to send directly to holly.
		
		EmailUtility.sendEmail("hollyndale@gmail.com", "Order Alert","An order has been created for you to take action");
		
	}
	/**
	 * Will try to populate object to values that match in the DB
	 * @param sql
	 * @return rs
	 */
	public PreparedStatement toPreparedStatement(String sql){
		Connection conn = DB.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(sql);
	
			statement.setString(1, this.alertType);
			statement.setString(2, this.alertName);
			statement.setString(3, this.alertDescription);
			statement.setTimestamp(4, this.alertDate);
		
		} catch (SQLException e) {
			CustomException.processError(e);
		}
		return statement;
	}
	/**
	 * @return the alertLink
	 */
	public String getAlertLink() {
		return alertLink;
	}
	/**
	 * @param alertLink the alertLink to set
	 */
	public void setAlertLink(String alertLink) {
		this.alertLink = alertLink;
	}
	/**
	 * @return the alertId
	 */
	public int getAlertId() {
		return alertId;
	}
	/**
	 * @param alertId the alertId to set
	 */
	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}
	/**
	 * @param alertId2
	 * @return alerts
	 */
	public static Alert getAlert(String alertId2) {
		Alert alert=new Alert();
		Connection conn = DB.getConnection();
		String sql = "SELECT * FROM BMH_ALERTS "
				+ "WHERE ALERT_ID=? ";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, alertId2);
			ResultSet rs= statement.executeQuery();
			while (rs.next()) {
				alert.setAlertDate(rs.getTimestamp("ALERT_DATE"));
				alert.setAlertDescription(rs.getString("ALERT_DESCRIPTION"));
				alert.setAlertLink(rs.getString("ALERT_LINK"));
				alert.setAlertName(rs.getString("ALERT_NAME"));
				alert.setAlertType(rs.getString("ALERT_TYPE"));
				alert.setAlertId(rs.getInt("ALERT_ID"));
			}
			rs.close();
			statement.close();
			
		} catch (SQLException e) {
			CustomException.processError(e);
		}
		return alert;
	}
}
