/**
 * Project: bmh
 * Package: utils
 * File: Coupon.java
 * 
 * Author: madal
 * Date: Jul 20, 2016
 * 
 * Description: 
 * 
 */
package utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 */
public class Coupon extends Constants {
	private String id;
	private String name;
	private String couponDesc;
	private Date activeDate;
	private Date expiresDate;
	private String parmData;
	private String active;
	private Date lastUpdated;
	/**
	 * Constructor
	 */
	public Coupon(){}

	public Coupon(ResultSet resultSet){
		try {
			this.id=resultSet.getString("ID");
			this.name=resultSet.getString("NAME");
			this.couponDesc=resultSet.getString("COUPON_DESC");
			this.activeDate=resultSet.getDate("ACTIVE_DATE");
			this.expiresDate=resultSet.getDate("EXPIRES_DATE");
			this.parmData=resultSet.getString("PARM_DATA");
			this.active=resultSet.getString("ACTIVE");
			this.lastUpdated=resultSet.getDate("LAST_UPDATED");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the couponDesc
	 */
	public String getCouponDesc() {
		return couponDesc;
	}
	/**
	 * @param couponDesc the couponDesc to set
	 */
	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
	}
	/**
	 * @return the activeDate
	 */
	public Date getActiveDate() {
		return activeDate;
	}
	/**
	 * @param activeDate the activeDate to set
	 */
	public void setActiveDate(Date activeDate) {
		this.activeDate = activeDate;
	}
	/**
	 * @return the expiresDate
	 */
	public Date getExpiresDate() {
		return expiresDate;
	}
	/**
	 * @param expiresDate the expiresDate to set
	 */
	public void setExpiresDate(Date expiresDate) {
		this.expiresDate = expiresDate;
	}
	/**
	 * @return the parmData
	 */
	public String getParmData() {
		return parmData;
	}
	/**
	 * @param parmData the parmData to set
	 */
	public void setParmData(String parmData) {
		this.parmData = parmData;
	}
	/**
	 * @return the active
	 */
	public String getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(String active) {
		this.active = active;
	}
	/**
	 * @return the lastUpdated
	 */
	public Date getLastUpdated() {
		return lastUpdated;
	}
	/**
	 * @param lastUpdated the lastUpdated to set
	 */
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	/**
	 * Method: updateToDatabase
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * 
	 * void
	 */
	public void updateToDatabase() {
		Connection conn = DB.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement("update BMH_COUPONSS SET NAME=?,COUPON_DESC=?,ACTIVE_DATE=?,EXPIRES_DATE=?,PARM_DATA=?,ACTIVE=? WHERE ID= ? ");
			st.setString(1, this.name);
			st.setString(2, this.couponDesc);
			st.setDate(3, this.activeDate);
			st.setDate(4, this.expiresDate);
			st.setString(5, this.parmData);
			st.setString(6, this.active);
			st.setInt(7, Integer.valueOf(this.id));

			int rowsInserted = st.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Document " + this.name + " was updated successfully!");
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method: insertToDatabase
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * 
	 * void
	 */
	public void insertToDatabase() {
		Connection conn = DB.getConnection();
		try {
			
			PreparedStatement st = conn.prepareStatement("insert INTO BMH_COUPONS (NAME,COUPON_DESC,ACTIVE_DATE,EXPIRES_DATE,PARM_DATA,ACTIVE) VALUES(?,?,?,?,?,?) ");
			st.setString(1, this.name);
			st.setString(2, this.couponDesc);
			st.setDate(3, this.activeDate);
			st.setDate(4, this.expiresDate);
			st.setString(5, this.parmData);
			st.setString(6, this.active);

			int rowsInserted = st.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Coupon " + this.name + " was inserted successfully!");
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Method: deleteFromDatabase
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * 
	 * void
	 */
	public void deleteFromDatabase() {
		Connection conn = DB.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement("DELETE FROM BMH_COUPONS WHERE ID= ? ");
			st.setString(1,	this.id);

			int rowsInserted = st.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Coupon " + this.name + " was deleted successfully!");
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Method: load
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @param couponId
	 * @return
	 * 
	 * Coupon
	 */
	public static Coupon load(String couponId) {
		Connection conn = DB.getConnection();
		String sql = "SELECT * FROM BMH_COUPONS WHERE ID= ? ";
		
		Coupon currentCoupon=null;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, couponId);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				currentCoupon = new Coupon(resultSet);
			}
			statement.close();
			resultSet.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return currentCoupon;
	}
}

