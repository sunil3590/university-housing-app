package edu.ncsu.sarj.dbproj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class Services {
	/*
	 * NOTE: Make sure that a ; is not passed at the end of the query
	 * Failure is handled gracefully.
	 * If the query does not work, a simple message is displayed and the control returns
	 */
	public static void printQueryOutput(String query, String columnNames[], Connection conn){
		
		int index = 0;
		String result = null;
		try {
			ResultSet resultSet = queryExecute(query, conn);
			
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int numCols = rsmd.getColumnCount();
			
			if(numCols != columnNames.length) {
				System.out.println("Services.printQueryOutput() ~ Invalid number of column names");
				return;
			}
			
			// go back if there is nothing to print
			resultSet.last();
			if(resultSet.getRow() == 0) {
				System.out.println("Could not retrieve any rows.");
				return;
			}
			resultSet.beforeFirst();
			
			System.out.println("--------------------------------------------------------------------------");
			while(resultSet!=null && resultSet.next())
			{
				for(index=0;index<columnNames.length;index++)
				{
					result = resultSet.getString(index+1);
					if(result==null)
						result = "";
					System.out.println(columnNames[index]+" : "+result);
				}
				System.out.println("--------------------------------------------------------------------------");
			}
		} catch (SQLException e) {
			System.out.println("Error in the query! : " + e.toString());
		}
	}
	
	private static ResultSet queryExecute(String query, Connection conn) throws SQLException {
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
				ResultSet.CONCUR_UPDATABLE);
		return stmt.executeQuery(query);
	}
	
	public static void updateStatement(String query, Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Error in update!" + e.toString());
		}
	}
}
