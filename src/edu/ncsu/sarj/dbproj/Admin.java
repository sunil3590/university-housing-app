package edu.ncsu.sarj.dbproj;

import java.sql.Connection;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;

public class Admin {
	private Connection conn = null;
	private Scanner scanner = null;
	public int loginId = -1;
	
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
			
			if(!userType.equals("admin")) {
				System.out.println("Admin.login() ~ login failed ");
				return false;
			}
			
		} catch (SQLException e) {
			System.out.println("Admin.login() ~ login failed " + e);
		}
		
		return true;
	}

	public void viewLeaseRequests() {
		//4.A.1
		//TODO : TEST
		//TODO : Are we printing all columns here?
		System.out.println("Admin View LeaseRequests");
		
		// get all pending requests
		String query1 = "SELECT LR.REQUEST_ID_N, P.FIRST_NAME_V, P.LAST_NAME_V "
					+ "FROM LEASE_REQUEST LR, PERSON P "
					+ "WHERE P.PERSON_ID_N  = LR.PERSON_ID_N AND  LR.REQUEST_STATUS_V  = 'Pending' "
					+ "ORDER BY REQUEST_ID_N";
		
		String[] colIds1 = {"REQUEST #", "FIRST NAME", "LAST NAME"};
		
		Services.printQueryOutput(query1, colIds1, conn);
		
		// get request id to be processed
		System.out.println("Enter LEASE REQUEST # for more info, 0 to go back.");
		String lease = scanner.nextLine();
		
		// go back on seeing 0
		if(lease.equals("0")) {
			return;
		}

		//TODO : Check stored procedure
		// get hold of the request 
		String query2 = "SELECT LR.REQUEST_ID_N, P.FIRST_NAME_V, P.LAST_NAME_V, NUMBER_OF_SEM_N, "
							+ "DATE_OFJOIN_DT, DATE_OFLEAVING_DT, PAYMENT_OPTIONS_V "
					+ "FROM LEASE_REQUEST LR, PERSON P "
					+ "WHERE P.PERSON_ID_N  = LR.PERSON_ID_N AND LR.REQUEST_ID_N = " + lease;

		String[] colIds2 = {"REQUEST #", "FIRST NAME", "LAST NAME", "DURATION(SEM)", 
				"JOIN DATE", "LEAVING DATE", "PAYMENT"};

		Services.printQueryOutput(query2, colIds2, conn);
		
		while(true) {
			// check if it needs to be approved or wait listed
			System.out.println("1 to approve, 0 to do nothing.");
			String option = scanner.nextLine();
			
			///TODO : update query
			// go back on seeing 0
			if(option.equals("0")) {
				return;
			} else if(option.equals("1")) {//approve
				String query3 = "UPDATE LEASE_REQUEST SET REQUEST_STATUS_V  = 'Processed' "
						+ "WHERE REQUEST_ID_N = " + lease;
				
				Services.updateStatement(query3, conn);
				return;
			}
		}
	}

	public void viewTerminateLeaseRequests() {
		//4.A.2
		//4.A.2.1
		//TODO : TEST
		//TODO : Are we printing all columns here?
		System.out.println("Admin View Terminate Lease Requests");
		
		// get all pending requests
		String query1 = "SELECT TERMINATION_ID_N, LEASE_NUMBER_N "
						+ "FROM LEASE_TERMINATION_REQUEST LTR "
						+ "WHERE TERMINATION_STATUS_V='Pending' "
						+ "ORDER BY TERMINATION_ID_N";
		
		String[] colIds1 = {"TERMINATION REQUEST #", "LEASE #"};
		
		Services.printQueryOutput(query1, colIds1, conn);
		
		// get request id to be processed
		System.out.println("Enter TERMINATION REQUEST # for more info, 0 to go back.");
		String tid = scanner.nextLine();
		
		// go back on seeing 0
		if(tid.equals("0")) {
			return;
		}

		String query2 = "SELECT TERMINATION_ID_N, P.PERSON_ID_N, LEASE_NUMBER_N, REASON_V,FIRST_NAME_V,LAST_NAME_V "
					+ "FROM LEASE_TERMINATION_REQUEST LTR, PERSON P "
					+ "WHERE P.PERSON_ID_N=LTR.PERSON_ID_N AND TERMINATION_STATUS_V='Pending' AND "
					+ "LTR.TERMINATION_ID_N = " + tid
					+ " ORDER BY TERMINATION_ID_N";

		String[] colIds2 = {"TERMINATION REQUEST #", "PERSON #", "LEASE #", "REASON", "FIRST NAME", "LAST NAME"};

		Services.printQueryOutput(query2, colIds2, conn);
		
		// terminate right now if required
		System.out.println("Do you want to terminate the request now? 1 = YES, anything else = NO");
		String option = scanner.nextLine();
		
		// go back on not seeing 1
		if(!option.equals("1")) {
			return;
		}
		
		//TODO : terminate the request
		// get date
		System.out.println("Enter inspection date(DD-Mon-YYYY) : ");
		String date = scanner.nextLine();
		
		// get damage
		System.out.println("Enter damage amount : ");
		String amount = scanner.nextLine();
		
		//TODO : Stored procedure
		
		// remove from lease, add to history, change to "completed", add line item for damage, remove parking, 
		// input : user id, lease id, termination date, damage
		
		CallableStatement cstmt = null;
		try {
			String storeProc = "{call TERMINATE_LEASE_PRC(?,?,?)}";
			cstmt = conn.prepareCall(storeProc);
			//term req ID, date, amount
			cstmt.setInt(1, Integer.parseInt(tid));
			cstmt.setString(2, date);
			cstmt.setInt(3, Integer.parseInt(amount));
			cstmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("Error in getEmpName stored procedure : " + e);   
		}
	}

	public void viewMaintenanceTickets() {
		//4.A.3
		//TODO : TEST
		System.out.println("Admin View Maintenance");
		
		// get all pending maintenance tickets
		//TODO : write query
		String query1 = "SELECT T_ID, T_DESC, T_DATE, STUDENT_NAME, STATUS "
				+ "FROM MAINTENANCE_VIEW ";
		
		String[] colIds1 = {"TICKET #", "DESC", "DATE", "STUDENT", "STATUS"};
		
		Services.printQueryOutput(query1, colIds1, conn);
		
		// get ticket id to be processed
		System.out.println("Enter MAINTENANCE TICKET # for more info, 0 to go back.");
		String option = scanner.nextLine();
		
		// go back on seeing 0
		if(option.equals("0")) {
			return;
		}

		String query2 = "UPDATE TICKET_LIST SET TICKET_STATUS_V = 'Processing' "
				+ "WHERE TICKET_ID_N = " + option;

		Services.updateStatement(query2, conn);
	}

	public void viewParkingRequests() {
		//4.A.4
		//TODO : TEST
		System.out.println("Admin View Parking request");
		
		// get all parking request
		String query1 = "SELECT PARKING_REQUEST_ID_N, P.FIRST_NAME_V, LAST_NAME_V,VEHICLE_TYPE_V, PR.REQUEST_STATUS_V "
				+ "FROM PARKING_REQUEST PR, PERSON P "
				+ "WHERE P.PERSON_ID_N = PR.PERSON_ID_N "
				+ "ORDER BY PARKING_REQUEST_ID_N";
		
		String[] colIds1 = {"REQUEST #", "FNAME", "LNAME", "VEHICLE TYPE", "REQUEST STATUS"};
		
		Services.printQueryOutput(query1, colIds1, conn);
		
		// get parking request ID
		System.out.println("Enter PARKING REQUEST # for more info, 0 to go back.");
		String rid = scanner.nextLine();
		
		// go back on seeing 0
		if(rid.equals("0")) {
			return;
		}

		//get info on request
		String query2 = "SELECT P.FIRST_NAME_V, LAST_NAME_V,VEHICLE_TYPE_V, "
				+ "HANDICAP_STATUS_V,NEARBY_OPTION_V "
				+ "FROM PARKING_REQUEST PR, PERSON P "
				+ "WHERE P.PERSON_ID_N = PR.PERSON_ID_N AND PARKING_REQUEST_ID_N = " + rid
				+ "ORDER BY PARKING_REQUEST_ID_N";

		String[] colIds2 = {"FNAME", "LNAME", "VEHICLE TYPE" , "HANDICAP", "NEARBY"};

		Services.printQueryOutput(query2, colIds2, conn);
		
		// do you want to approve this
		System.out.println("Do you want to approve this?(1 = yes, anything else = no) : ");
		String option = scanner.nextLine();
		
		if(option.equals("1")) {
			// check if the user already has a parking spot
			String query4 = "SELECT * "
					+ "FROM PARKING_REQUEST R, ASSIGNED_PARKING P, LEASE L "
					+ "WHERE L.LEASE_NUMBER_N = P.LEASE_NUMBER_N AND L.PERSON_ID_N = R.PERSON_ID_N";
			
			try {
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_UPDATABLE);
				ResultSet resultSet = stmt.executeQuery(query4);
				
				resultSet.last();
				if(resultSet.getRow() > 0) {
					System.out.println("Cannot approve. Already has one.");
					return;
				}
				resultSet.beforeFirst();
								
			} catch (SQLException e) {
				
			}

			//update query
			String query3= "UPDATE PARKING_REQUEST SET REQUEST_STATUS_V='Processed' "
					+ "WHERE PARKING_REQUEST_ID_N = " + rid;
			Services.updateStatement(query3, conn);
		}
		
		while(true) {
			System.out.println("Enter 1 to go back");	
			option = scanner.nextLine();
			if(option.equals("1")) {
				break;
			}
		}
	}

	public void viewProfile() {
		// TODO Auto-generated method stub
		
	}

	public void updateProfile() {
		// TODO Auto-generated method stub
		
	}
}
