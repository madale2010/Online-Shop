/**
 * Project: bmh
 * Package: utils
 * File: Document.java
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
public class Document extends Constants {
	private String name;
	private String data;
	private Date date;
	private String url;
	/**
	 * Constructor
	 */
	public Document(){}
	
	public Document(ResultSet resultSet){
		try {
			this.name=resultSet.getString("NAME");
			this.data=resultSet.getString("DATA");
			this.date=resultSet.getDate("LAST_UPDATED");
			this.url=resultSet.getString("URL");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
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
	public void insertToDatabase() {
		Connection conn = DB.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement("insert INTO BMH_DOCUMENTS (NAME, DATA, URL) VALUES(?,?,?) ");
			st.setString(1,	this.name);
			st.setString(2,	this.data);
			st.setString(3, this.url);
			int rowsInserted = st.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Document " + this.name + " was inserted successfully!");
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



public void updateToDatabase() {
	Connection conn = DB.getConnection();
	try {
		PreparedStatement st = conn.prepareStatement("update BMH_DOCUMENTS SET DATA=?, URL=? WHERE NAME= ? ");
		st.setString(1,	this.data);
		st.setString(2,	this.url);
		st.setString(3, this.name);

		int rowsInserted = st.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Document " + this.name + " was inserted successfully!");
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
		PreparedStatement st = conn.prepareStatement("DELETE FROM BMH_DOCUMENTS WHERE NAME= ? ");
		st.setString(1,	this.name);

		int rowsInserted = st.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("Document " + this.name + " was deleted successfully!");
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
 * @param documentName
 * @return
 * 
 * Document
 */
public static Document load(String documentName) {
	Connection conn = DB.getConnection();
	String sql = "SELECT * FROM BMH_DOCUMENTS WHERE NAME= ? ";
	
	Document currentDocument=null;
	try {
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, documentName);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			currentDocument = new Document(resultSet);
		}
		statement.close();
		resultSet.close();
	} catch(SQLException e) {
		e.printStackTrace();
	}
	return currentDocument;
}
/**
 * Method: load
 * Author: madal
 * 
 * Description: 
 *
 * @param documentName
 * @return
 * 
 * Document
 */
public static Document view(String url) {
	Connection conn = DB.getConnection();
	String sql = "SELECT * FROM BMH_DOCUMENTS WHERE URL LIKE ? ";
	
	Document currentDocument=null;
	try {
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, "%"+url+"%");
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			currentDocument = new Document(resultSet);
		}
		statement.close();
		resultSet.close();
	} catch(SQLException e) {
		e.printStackTrace();
	}
	return currentDocument;
}

/**
 * @return the url
 */
public String getUrl() {
	return url;
}

/**
 * @param url the url to set
 */
public void setUrl(String url) {
	this.url = url;
}

}
