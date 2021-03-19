package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.pool.OracleDataSource;
import utils.CustomException;

public class DBManager {

	private String dbURL;
	private String user;
	private String password;
	private Connection conn;
	private OracleDataSource ods;

	public DBManager(String url, String u, String p) {
		this.dbURL = url;
		this.user = u;
		this.password = p;
		try {
			this.ods = new OracleDataSource();
			// System.out.println("DB Called");
			ods.setURL(this.dbURL);
			ods.setUser(this.user);
			ods.setPassword(this.password);
			this.conn = ods.getConnection();
		} catch (SQLException e) {
			CustomException.processError(e);
		}

	}

	public Connection getConnection() {
		return this.conn;
	}

	public void closeConnection() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			CustomException.processError(e);
		}
	}
	/**
	 *  Runs given sql string and returns resultset
	 * @param sql
	 * @return
	 */
	public ResultSet runSql(String sql) {
		Statement sta=null;
		ResultSet rs=null;
		try {
			sta = this.conn.createStatement();
			rs=sta.executeQuery(sql);
			sta.closeOnCompletion();
		} catch(SQLException e){
			CustomException.processError(e);
		}
		return rs;
	}
	public ResultSet runPreparedStatement(String sql) {
		try {
			PreparedStatement ps=this.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ps.closeOnCompletion();
			return rs;
		}catch (SQLException e) {
			CustomException.processError(e);
		}
		return null;
	}
	/**
	 * Takes a sql string and array of objects for possible parm types to append to sql.
	 * @param sql
	 * @param parm
	 * @return
	 */
	public ResultSet runPreparedStatement(String sql, Object parm) {
		try {
			PreparedStatement ps=this.conn.prepareStatement(sql);
			
				String parmType=parm.getClass().getName();
				if(parmType.contains("Integer")){
						ps.setInt(1, (int) parm);	
				}
				else if(parmType.contains("String")){
					ps.setString(1, (String) parm);
				}
				ResultSet rs = ps.executeQuery();
				ps.closeOnCompletion();
			return rs;
		} catch (SQLException e) {
			CustomException.processError(e);
		}
		return null;
	}
	public int runUpdateStatement(PreparedStatement statement){
		try {
			int temp = statement.executeUpdate();
			statement.closeOnCompletion();
			return temp;
		} catch (SQLException e) {
			CustomException.processError(e);
		}
		return 0;
	}

	public PreparedStatement prepareStatement(String sql) {
		PreparedStatement ps = null;
		try {
			ps = this.conn.prepareStatement(sql);
		} catch (SQLException e) {
			CustomException.processError(e);
		}
		return ps;
	}
}
