package edu.ncsu.sarj.dbproj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Services {
	/*
	 * NOTE: Make sure that a ; is not passed at the end of the query
	 * Failure is handled gracefully.
	 * If the query does not work, a simple message is displayed and the control returns
	 */
	public static void printQueryOutput(String query,String columnNames[],Connection conn){
		
		Statement stmt;
		int index = 0;
		String result = null;
		try {
			stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(query);
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
			System.out.println("Error in the query!");
		}
		catch (Exception e) {
			System.out.println("An exception has occured!");
		}
	}
}
