package edu.ncsu.sarj.dbproj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Deal only with establishing and terminating connection with Oracle10g server
 * This is done to save from wrapping more methods
 * 
 * Singleton design patten
 * 
 * All queries and other operations with the SQL connection to be handled outside
 * this class
 */
public class DBConnFactory {

	private static DBConnFactory connection = null;
	private static Connection sqlConnection = null;
	
	private DBConnFactory() {
		// make it explicit!
		connection = null;
		sqlConnection = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err.println("Oracle JDBC driver not loaded " + e);
		}
	}
	
	public static DBConnFactory getInstance() {
		if(connection == null) {
			connection = new DBConnFactory();
		}
		
		return connection;
	}
	
	public Connection getConnection() {
		if(sqlConnection == null) {
			try {
				// NCSU DB server, user name and password
				String dbUrl = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
				String user = "aravi5"; //unityID
				String pass = "200059828"; //student ID number
				
				sqlConnection = (Connection) DriverManager.getConnection(
						dbUrl, user, pass);
			} catch (SQLException e) {
				System.out.println("DBConnFactory.getConnectio() ~ " + e);
				return null;
			}
		}
		
		return sqlConnection;
		
	}
	
	public boolean closeConnection() {
		if(sqlConnection != null) {
			try {
				sqlConnection.close();
			} catch (SQLException e) {
				System.out.println("DBConnFactory.closeConnection() ~ " + e);
				return false;
			}
		}
		
		return true;
	}
	
}
