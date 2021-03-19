package utils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * 
 */
public class CustomersStatistics extends Constants{
	private int statId;
	private int visitCount;
	private int commentCount;
	private BigDecimal totalSales;
	private int emailCount;
	private LocalDate recordDate;
	
	/**
	 * Constructor
	 */
	public CustomersStatistics(){
		try {
			Connection conn = DB.getConnection();
			String sqlcheck = "select * from BMH_CUSTOMER_STATS ";
			PreparedStatement checkStatement =conn.prepareStatement(sqlcheck);
			ResultSet rs = checkStatement.executeQuery();
			Date dbDate = null;
			int intSales=0;
			
			while (rs.next()) {
				dbDate= rs.getDate("CUR_DATE");
				 statId =rs.getInt("STAT_ID");
				 visitCount = rs.getInt("VISITS");
				 commentCount=rs.getInt("COMMENTS");
				 emailCount=rs.getInt("EMAILS");
				 intSales=rs.getInt("SALES");
			}
			rs.close();
			checkStatement.close();
			totalSales=new BigDecimal(Integer.toString(intSales));
			totalSales = totalSales.setScale(2, BigDecimal.ROUND_CEILING);
			ZonedDateTime zoneTime = Instant.ofEpochMilli(dbDate.getTime()).atZone(ZoneId.systemDefault());
			recordDate = zoneTime.toLocalDate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the statId
	 */
	public int getStatId() {
		return statId;
	}

	/**
	 * @param statId the statId to set
	 */
	public void setStatId(int statId) {
		this.statId = statId;
	}

	/**
	 * @return the visitCount
	 */
	public int getVisitCount() {
		return visitCount;
	}

	/**
	 * @param visitCount the visitCount to set
	 */
	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

	/**
	 * @return the commentCount
	 */
	public int getCommentCount() {
		return commentCount;
	}

	/**
	 * @param commentCount the commentCount to set
	 */
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	/**
	 * @return the totalSales
	 */
	public BigDecimal getTotalSales() {
		return totalSales;
	}

	/**
	 * @param totalSales the totalSales to set
	 */
	public void setTotalSales(BigDecimal totalSales) {
		this.totalSales = totalSales;
	}

	/**
	 * @return the emailCount
	 */
	public int getEmailCount() {
		return emailCount;
	}

	/**
	 * @param emailCount the emailCount to set
	 */
	public void setEmailCount(int emailCount) {
		this.emailCount = emailCount;
	}

	/**
	 * @return the recordDate
	 */
	public LocalDate getRecordDate() {
		return recordDate;
	}

	/**
	 * @param recordDate the recordDate to set
	 */
	public void setRecordDate(LocalDate recordDate) {
		this.recordDate = recordDate;
	}
	
}
