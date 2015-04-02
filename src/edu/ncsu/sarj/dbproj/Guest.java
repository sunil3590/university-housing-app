package edu.ncsu.sarj.dbproj;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//TODO: Make Person class and let Student and Guest inherit from that
public class Guest extends Student {
	
	//private Connection conn = null;
	//private Scanner scanner = null;
	//private int loginId = -1;
	
	public Guest(int loginId, Connection conn, Scanner scanner) {
		
		super(loginId, conn, scanner);
		// a connection is always needed
		/* if(conn == null || scanner == null || loginId < 0) {
			System.out.println("Guest.Guest() ~ null input");
			return;
		}
		
		this.conn = conn;
		this.scanner = scanner;
		this.loginId = loginId; */
	}
	
	public void viewVacancy() {
		
		System.out.println("View vacancy in Guest");
		//TODO: Enter the query for GUEST here
		
	}
	
	public void viewCurrentInvoice() {
		
		System.out.println("View Current Invoice for Guest");
		//TODO Enter the query for GUEST here
		
		
	}
	
	public void viewFormerInvoices() {
		
		System.out.println("Which former Invoice you want to see? " +
				"Enter 1 for first lease, 2 for second lease and so on...");
		
		int option = scanner.nextInt();
		
		System.out.println("You have entered " + option);
		
	}
	
	public void viewCurrentLease() {
		
		System.out.println("View Current Lease for Guest");
		//TODO Enter the query for GUEST here
	}
	
	public void viewFormerLeases() {
		
		System.out.println("Which former Lease you want to see? " +
				"Enter 1 for first lease, 2 for second lease and so on...");
		
		int option = scanner.nextInt();
		
		System.out.println("You have entered " + option);
		//TODO Enter the query for GUEST here
		
	}
	
	public void newLeaseRequest() {
		
		System.out.println("Enter the following details");
		
		System.out.println("Period of leasing: ");
		
		//TODO Enter the query for GUEST here
		
	}
	
	public void terminateLease() {
		
		System.out.println("GUEST Terminate Lease");
		//TODO Enter the query for GUEST here
		
	}
	
	public void viewRequest() {
		
		System.out.println("GUEST View Request");
		
		//TODO Enter the query for GUEST here
	}
	
	public void cancelRequest() {
		
		System.out.println("GUEST View Request");
		
		//TODO Enter the query for GUEST here
		
	}
	
	public void requestParkingSpot() {
		
		System.out.println("GUEST Request Parking");
		
		//TODO Enter the query for GUEST here
		
	}
	
	public void viewParkingInformation() {
		
		System.out.println("GUEST View Parking");
		
		//TODO Enter the query for STUDENT here
		
		
	}
	
	public void viewCurrentParkingSpot() {

		System.out.println("GUEST View Parking Spot");
		
		//TODO Enter the query for GUEST here
		
	}
	
	public void renewParkingSpot() {
		
		System.out.println("GUEST Renew Parking Spot");
		
		//TODO Enter the query for GUEST here
		
	}
	
	public void returnParkingSpot() {
		
		System.out.println("GUEST Return Parking Spot");
		
		//TODO Enter the query for GUEST here
		
	}
	
	public void viewRequestStatus() {
		
		System.out.println("GUEST View Request Status");
		
		//TODO Enter the query for GUEST here
		
	}
	
	public void raiseNewTicket() {
		
		System.out.println("GUEST Raise New Ticket");
		
		//TODO Enter the query for GUEST here
		
	}
	
	public void viewTicketStatus() {
		
		System.out.println("GUEST View Ticket Status");
		
		//TODO Enter the query for GUEST here
		
	}
	
	public void viewProfile() {
		
		System.out.println("GUEST View Profile");
		
		//TODO Enter the query for GUEST here
		
	}
	
	public void updateProfile() {
		
		System.out.println("GUEST Update Profile");
		
		//TODO Enter the query for GUEST here
		
	}
}
