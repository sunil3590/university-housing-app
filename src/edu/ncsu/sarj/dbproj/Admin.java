package edu.ncsu.sarj.dbproj;

import java.sql.Connection;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin {
	private Connection conn = null;
	private Scanner scanner = null;
	private int loginId = -1;
	private UserType userType = UserType.UNKNOWN;
	
	public Admin(Connection conn, Scanner scanner) {
		// a connection is always needed
		if(conn == null || scanner == null) {
			System.out.println("Admin.Admin() ~ null input");
			return;
		}
		
		this.conn = conn;
		this.scanner = scanner;
	}
	
	public boolean login(String uid, String pass) {	
		// SQL query to check credentials
		try {
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM LOGIN WHERE LOGIN_NAME_V = '" + 
					uid + "'" + " AND LOGIN_PWD_V = '" + pass + "'";
			
			ResultSet result = stmt.executeQuery(query);
			
			result.next();
			
			this.loginId = result.getInt("LOGIN_ID_N");
			String userType = result.getString("LOGIN_TYPE_V");
			
			if(userType.equals("admin")) {
				this.userType = UserType.ADMIN;
			} else {
				//TODO : notify caller of this error
				System.out.println("Admin.login() ~ login failed ");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Admin.login() ~ login failed " + e);
		}
		
		return true;
	}

	public void viewLeaseRequests() {
		
		System.out.println("Admin View LeaseRequests");
		//TODO Enter the query for ADMIN here
		
	}

	public void viewTerminateLeaseRequests() {
		
		System.out.println("Admin View Terminate Lease Requests");
		//TODO Enter the query for ADMIN here
		
	}

	public void viewMaintenanceTickets() {
		
		System.out.println("Admin View Maintenance");
		//TODO Enter the query for ADMIN here
		
	}

	public void viewParkingRequests() {
		
		System.out.println("Admin View Parking Requests");
		//TODO Enter the query for ADMIN here
		
	}

	public void handleProfile() {
		
		System.out.println("Handle ADMIN profile");
		//TODO Enter the query for ADMIN here
		
	}

	public void viewProfile() {
		// TODO Auto-generated method stub
		
	}

	public void updateProfile() {
		// TODO Auto-generated method stub
		
	}
}
