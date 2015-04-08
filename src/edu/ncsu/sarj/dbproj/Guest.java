package edu.ncsu.sarj.dbproj;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Guest extends Person {
	
	public Guest(Connection conn, Scanner scanner) {
		
		// a connection is always needed
		if(conn == null || scanner == null) {
			System.out.println("Guest.Guest() ~ null input");
			return;
		}
		
		this.conn = conn;
		this.scanner = scanner;
	}
	
	//@Override
	public boolean login(String approvalId, String pass) {		
		// SQL query to check credentials
		try {
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM LOGIN WHERE LOGIN_NAME_V = '" + 
					approvalId + "'";
			
			ResultSet result = stmt.executeQuery(query);
			
			result.next();
			
			this.loginId = result.getInt("LOGIN_ID_N");
			String userType = result.getString("LOGIN_TYPE_V");
			
			if(userType.equals("guest")) {
				this.userType = UserType.GUEST;
			} else {
				System.out.println("Guest.login() ~ login failed ");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Guest.login() ~ login failed " + e);
		}
		
		return true;
	}
	
	//@Override
	public void viewVacancy() {
		
		System.out.println("View vacancy in Guest");
		//TODO: Enter the query for GUEST here
		
	}
	
	//@Override
	public void viewCurrentInvoice() {
		
		System.out.println("View Current Invoice for Guest");
		
		System.out.println("Enter approval ID : ");
		
		//TODO: Enter the query for GUEST here
		
	}
	
	//@Override
	public void viewFormerInvoices() {
		
		System.out.println("Which former Invoice you want to see? " +
				"Enter 1 for first lease, 2 for second lease and so on...");
		
		int option = scanner.nextInt();
		
		System.out.println("You have entered " + option);
		
	}
	
	//@Override
	public void viewCurrentLease() {
		
		System.out.println("View Current Lease for Guest");
		//TODO Enter the query for GUEST here
	}
	
	//@Override
	public void viewFormerLeases() {
		
		System.out.println("Which former Lease you want to see? " +
				"Enter 1 for first lease, 2 for second lease and so on...");
		
		int option = scanner.nextInt();
		
		System.out.println("You have entered " + option);
		//TODO Enter the query for GUEST here
		
	}
	
	//@Override
	public void newLeaseRequest() {
		
		System.out.println("Enter the following details");
		
		System.out.println("Period of leasing: ");
		
		//TODO Enter the query for GUEST here
		
	}
	
	//@Override
	public void terminateLease() {
		
		System.out.println("GUEST Terminate Lease");
		//TODO Enter the query for GUEST here
		
	}
	
	//@Override
	public void viewRequest() {
		
		System.out.println("GUEST View Request");
		
		//TODO Enter the query for GUEST here
	}
	
	//@Override
	public void cancelRequest() {
		
		System.out.println("GUEST View Request");
		
		//TODO Enter the query for GUEST here
		
	}
	
	//@Override
	public void requestParkingSpot() {
		
		System.out.println("GUEST Request Parking");
		
		//TODO Enter the query for GUEST here
		
	}
	
	//@Override
	public void viewParkingInformation() {
		
		System.out.println("GUEST View Parking");
		
		//TODO Enter the query for STUDENT here
		
		
	}
	
	//@Override
	public void viewCurrentParkingSpot() {

		System.out.println("GUEST View Parking Spot");
		
		//TODO Enter the query for GUEST here
		
	}
	
	//@Override
	public void renewParkingSpot() {
		
		System.out.println("GUEST Renew Parking Spot");
		
		//TODO Enter the query for GUEST here
		
	}
	
	//@Override
	public void returnParkingSpot() {
		
		System.out.println("GUEST Return Parking Spot");
		
		//TODO Enter the query for GUEST here
		
	}
	
	//@Override
	public void viewRequestStatus() {
		
		System.out.println("GUEST View Request Status");
		
		//TODO Enter the query for GUEST here
		
	}
	
	//@Override
	public void raiseNewTicket() {
		
		System.out.println("GUEST Raise New Ticket");
		
		//TODO Enter the query for GUEST here
		
	}
	
	//@Override
	public void viewTicketStatus() {
		
		System.out.println("GUEST View Ticket Status");
		
		//TODO Enter the query for GUEST here
		
	}
	
	//@Override
	public void viewProfile() {
		
		System.out.println("GUEST View Profile");
		
		//TODO Enter the query for GUEST here
		
	}
	
	//@Override
	public void updateProfile() {
		
		System.out.println("GUEST Update Profile");
		
		//TODO Enter the query for GUEST here
		
	}
}
