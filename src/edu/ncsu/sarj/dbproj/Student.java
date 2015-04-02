package edu.ncsu.sarj.dbproj;

import java.sql.Connection;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Student {
	protected Connection conn = null;
	protected Scanner scanner = null;
	protected int loginId = -1;
	
	//public Student() {
		
	//}
	
	public Student(int loginId, Connection conn, Scanner scanner) {
		// a connection is always needed
		if(loginId < 0 || conn == null || scanner == null) {
			System.out.println("Student.Student() ~ null input");
			return;
		}
		
		this.conn = conn;
		this.scanner = scanner;
		this.loginId = loginId;
	}

	public void viewVacancy() {
		
		System.out.println("View vacancy in student");
		//TODO Enter the query for STUDENT here
		
	}

	public void viewCurrentInvoice() {
		
		System.out.println("View Current Invoice for student");
		//TODO Enter the query for STUDENT here
		
		
	}

	public void viewFormerInvoices() {
		
		System.out.println("Which former Invoice you want to see? " +
				"Enter 1 for first lease, 2 for second lease and so on...");
		
		int option = scanner.nextInt();
		
		System.out.println("You have entered " + option);
		
	}

	public void viewCurrentLease() {
		
		System.out.println("View Current Lease for student");
		//TODO Enter the query for STUDENT here
	}

	public void viewFormerLeases() {
		
		System.out.println("Which former Lease you want to see? " +
				"Enter 1 for first lease, 2 for second lease and so on...");
		
		int option = scanner.nextInt();
		
		System.out.println("You have entered " + option);
		
		//TODO Enter the query for STUDENT here
		
	}

	public void newLeaseRequest() {
		
		System.out.println("Enter the following details");
		
		System.out.println("Period of leasing: ");
		
		//TODO Enter the query for STUDENT here
	}

	public void terminateLease() {
		
		System.out.println("STUDENT Terminate Lease");
		
		//TODO Enter the query for STUDENT here
		
	}

	public void viewRequest() {
		
		System.out.println("STUDENT View Request");
		
		//TODO Enter the query for STUDENT here
	}

	public void cancelRequest() {
		
		System.out.println("STUDENT View Request");
		
		//TODO Enter the query for STUDENT here
		
	}

	public void requestParkingSpot() {
		
		System.out.println("STUDENT Request Parking");
		
		//TODO Enter the query for STUDENT here
		
	}

	public void viewParkingInformation() {
	
		System.out.println("STUDENT View Parking");
		
		//TODO Enter the query for STUDENT here
		
		
	}

	public void viewCurrentParkingSpot() {

		System.out.println("STUDENT View Parking Spot");
		
		//TODO Enter the query for STUDENT here
		
	}

	public void renewParkingSpot() {
		
		System.out.println("STUDENT Renew Parking Spot");
		
		//TODO Enter the query for STUDENT here
		
	}

	public void returnParkingSpot() {
		
		System.out.println("STUDENT Return Parking Spot");
		
		//TODO Enter the query for STUDENT here
		
	}

	public void viewRequestStatus() {
		
		System.out.println("STUDENT View Request Status");
		
		//TODO Enter the query for STUDENT here
		
	}

	public void raiseNewTicket() {
		
		System.out.println("STUDENT Raise New Ticket");
		
		//TODO Enter the query for STUDENT here
		
	}

	public void viewTicketStatus() {
		
		System.out.println("STUDENT View Ticket Status");
		
		//TODO Enter the query for STUDENT here
		
	}

	public void viewProfile() {
		
		System.out.println("STUDENT View Profile");
		
		//TODO Enter the query for STUDENT here
		
	}

	public void updateProfile() {
		
		System.out.println("STUDENT Update Profile");
		
		//TODO Enter the query for STUDENT here
		
	}
}
