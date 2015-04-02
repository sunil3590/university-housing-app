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
	
	public Admin(int loginId, Connection conn, Scanner scanner) {
		// a connection is always needed
		if(loginId < 0 || conn == null || scanner == null) {
			System.out.println("Student.Student() ~ null input");
			return;
		}
		
		this.conn = conn;
		this.scanner = scanner;
		this.loginId = loginId;
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
