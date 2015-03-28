package edu.ncsu.sarj.dbproj;

import java.sql.Connection;

/*
 * Main class
 */
public class UniversityHousingApp {

	public static void main(String[] args) {
		//glorify ourselves
		System.out.println("*****************************************************");
		System.out.println("CSC540 - Project 1 - University Housing Application");
		System.out.println("{sdavang, aravi5, rravi, jlourde} @ncsu.edu");
		System.out.println("Let's start filling those accomadations!");
		System.out.println("*****************************************************");
		
		//get a connection to the DB before you do anything else
		Connection conn = DBConnFactory.getInstance().getConnection();
		if(conn == null) {
			// nothing to clean up. inform and exit
			System.out.println("UniversityHousingApp.main() ~ SQL connection error");
			System.exit(0);
		}
		
		System.out.println("UniversityHousingApp.main() ~ Connected to database");
		
		//terminate the DB connection before you quit
		DBConnFactory.getInstance().closeConnection();
		
		System.out.println("UniversityHousingApp.main() ~ Exiting");
		
		return;

	}

}
