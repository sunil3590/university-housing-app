package edu.ncsu.sarj.dbproj;

import java.sql.Connection;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Date;

public class Student extends Person {
	
	public Student(Connection conn, Scanner scanner) {
		// a connection is always needed
		if(conn == null || scanner == null) {
			System.out.println("Student.Student() ~ null input");
			return;
		}
		
		this.conn = conn;
		this.scanner = scanner;
	}

	//@Override
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
			
			if(userType.equals("student")) {
				this.userType = UserType.STUDENT;
			} else {
				System.out.println("Student.login() ~ login failed ");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Student.login() ~ login failed " + e);
		}
		
		return true;
	}

	//@Override
	public void viewCurrentInvoice() {
		//3.A.1.1
		//TODO : TEST
		System.out.println("STUDENT View Current Invoice");
		
		String query = "SELECT L.LINEITEMS_TYPE_V , L.LINEITEM_PRICE_N , TO_CHAR(I.DUE_DATE_DT,'DD-MON-YYYY') "
					+ "FROM STUDENT_INVOICE I, INVOICE_LINEITEMS L "
					+ "WHERE L.INVOICE_ID_N = I.INVOICE_ID_N  AND I.STUDENT_ID_N  = "+ Integer.toString(loginId)
							+ " AND I.PERIOD_START_DT <= SYSDATE AND SYSDATE<=I.PERIOD_END_DT";
		
		String[] colIds = {"INVOICE LINE ITEM", "PRICE", "DUE DATE"};
		
		Services.printQueryOutput(query, colIds, conn);
		
		
	}
	
	//@Override
	public void viewFormerInvoices() {
		//3.A.1.2
		//TODO : I guess the query is wrong. TEST well
		
		System.out.println("Which former Invoice you want to see? " +
				"Enter 1 for first lease, 2 for second lease and so on...");
		
		int option = scanner.nextInt();
		if(option == 0) {
			return;
		}
		
		System.out.println("You have entered " + option);
		
		String[] colIds = {"INVOICE #", "START", "END", "DUE", "AMOUNT"};
				
		String query = "SELECT ROWNUM, I.PERIOD_START_DT, I.PERIOD_END_DT ,  I.DUE_DATE_DT , I.AMOUNT_DUE_N  "
					+ "FROM STUDENT_INVOICE I, INVOICE_LINEITEMS  L "
					+ "WHERE L.INVOICE_ID_N  = I.INVOICE_ID_N  AND I.STUDENT_ID_N  = "+ Integer.toString(loginId) +
					"AND I.PERIOD_START_DT  < SYSDATE AND SYSDATE>I.PERIOD_END_DT";
		
		Services.printQueryOutput(query, colIds, conn);
	}

	//@Override
	public void viewCurrentLease() {
		//3.A.2.1
		//TODO : TEST
		
		System.out.println("STUDENT View Current Lease for student");
	
		String query = "SELECT LEASE_NUMBER_N, NUMBER_OF_SEM_N, PLACE_NUMBER_N , DATE_OFJOIN_DT , "
							+ "DATE_OFLEAVING_DT , PAYMENT_OPTIONS_V , SECURITY_DEPOSIT_N , LEASE_PENALTY_N "
					+ "FROM LEASE "
					+ "WHERE PERSON_ID_N = " + Integer.toString(loginId);
		
		String[] colIds = {"LEASE #", "DURATION(SEM)", "PLACE #", 
				"JOIN DATE", "LEAVING DATE", "PAYMENT", "DEPOSIT", "LEASE PENALTY"};
		
		Services.printQueryOutput(query, colIds, conn);
		
		while(true) {
			System.out.println("Enter 1 to go back");	
			int option = scanner.nextInt();
			if(option == 1) {
				break;	
			}
		}
	}

	//@Override
	public void viewFormerLeases() {
		//3.A.2.2
		//TODO : TEST
		
		System.out.println("Which former Lease you want to see? " +
				"Enter 1 for first lease, 2 for second lease and so on...");
		
		int option = scanner.nextInt();
		if(option == 0) {
			return;
		}
		
		System.out.println("You have entered " + option);

		String query = "SELECT LEASE_NUMBER_N, NUMBER_OF_SEM_N, PLACE_NUMBER_N , "
							+ "DATE_OFJOIN_DT , DATE_OFTERMINATION_DT , PAYMENT_OPTIONS_V , "
							+ "SECURITY_DEPOSIT_N , LEASE_PENALTY_N "
					+ "FROM LEASE_HISTORY "
					+ "WHERE PERSON_ID_N = " + Integer.toString(loginId);
		
		Services.printQueryOutput(query, new String[]{"LEASE #", "DURATION(SEM)", "PLACE #", 
				"JOIN DATE", "TERMINATION DATE", "PAYMENT", "DEPOSIT", "LEASE PENALTY"}, conn);
		
	}

	//@Override
	public void newLeaseRequest() {
		
		System.out.println("Enter the following details");
		
		System.out.println("Period of leasing: ");
		
		//TODO Enter the query for STUDENT here
	}

	//@Override
	public void terminateLease() {
		
		System.out.println("STUDENT Terminate Lease");
		
		//TODO Enter the query for STUDENT here
		
	}

	//@Override
	public void viewRequest() {
		//3.A.4.1
		//TODO : TEST
		
		System.out.println("STUDENT View Request by Student");
		
		String query = "SELECT REQUEST_ID_N, PERSON_ID_N, NUMBER_OF_SEM_N, DATE_OFJOIN_DT,"
							+ "DATE_OFLEAVING_DT, PAYMENT_OPTIONS_V, REQUEST_STATUS_V "
					+ "FROM LEASE_REQUEST "
					+ "WHERE PERSON_ID_N=1";
		
		String[] colIds = {"REQUEST #", "PERSON #", "DURATION(SEM)", 
				"JOIN DATE", "LEAVING DATE", "PAYMENT", "STATUS"};
		
		Services.printQueryOutput(query, colIds, conn);
		
		while(true) {
			System.out.println("Enter 1 to go back");	
			int option = scanner.nextInt();
			if(option == 1) {
				break;	
			}
		}
	}

	//@Override
	public void cancelRequest() {
		
		System.out.println("STUDENT View Request");
		
		//TODO Enter the query for STUDENT here
		
	}

	//@Override
	public void viewVacancy() {
		//3.A.5
		//TODO : TEST

		System.out.println("STUDENT Vacant Residence Halls");
		String query1 = "SELECT LE.PLACE_NUMBER_N, LE.ROOM_NUMBER_N, LE.BUILDING_ID_N, LE.RENT_RATE_N, "
							+ "OCCUPANCY_COUNT_N AS MAX_OCCUPANCY, RH.HALL_NAME_V, RH.HALL_ADDR_V, "
							+ "RH.HALL_PHONE_N, RH.MANAGER_FIRST_NAME_V, RH.MANAGER_LAST_NAME_V "
					+ "FROM  RESIDENCE_HALLS RH, LEASABLE_ENTITY LE "
					+ "WHERE LE.BUILDING_ID_N = RH.BUILDING_ID_N AND "
							+ "LE.PLACE_NUMBER_N NOT IN (SELECT PLACE_NUMBER_N FROM LEASE)";
			
		String[] colIds1 = {"PLACE #", "ROOM #", "BUILDING #", "RENT", "MAX OCCUPENCY", "HALL NAME", 
				"ADDR", "PHONE", "MANAGER FNAME", "MANAGER LNAME"};
		
		Services.printQueryOutput(query1, colIds1, conn);
		
		//TODO : are we taking care of both family and individual apartment?
		System.out.println("Vacant Appartments");
		String query2 = 
				"SELECT  LE.PLACE_NUMBER_N, LE.ROOM_NUMBER_N, LE.BUILDING_ID_N, LE.RENT_RATE_N, "
						+ "OCCUPANCY_COUNT_N AS MAX_OCCUPANCY, APT.APT_ADDRESS_V, "
						+ "APT.NUM_BEDROOMS_N, APT.NUM_BATHS_N "
				+ "FROM  APARTMENT_HOUSING APT, LEASABLE_ENTITY LE "
				+ "WHERE LE.BUILDING_ID_N = APT.BUILDING_ID_N AND "
						+ "LE.PLACE_NUMBER_N NOT IN (SELECT PLACE_NUMBER_N FROM LEASE )";
			
		String[] colIds2 = {"PLACE #", "ROOM #", "BUILDING #", "RENT", "MAX OCCUPENCY", 
				"ADDR", "# BEDS", "# BATHS"};
		
		Services.printQueryOutput(query2, colIds2, conn);
		
		while(true) {
			System.out.println("Enter 1 to go back");	
			int option = scanner.nextInt();
			if(option == 1) {
				break;	
			}
		}
	}
	
	//@Override
	public void requestParkingSpot() {
		
		System.out.println("STUDENT Request Parking");
		
		//TODO Enter the query for STUDENT here
		
	}

	//@Override
	public void viewParkingInformation() {
		//3.B.2
		//TODO : TEST
		
		System.out.println("STUDENT View Parking information");
		
		String query = "SELECT LOT.PARKING_TYPE_V, LOT.ADDRESS_V "
					+ "FROM PARKING_LOT LOT , PARKING_SPOT SPOT "
					+ "WHERE SPOT.PARKING_LOT_ID_N = LOT.PARKING_LOT_ID_N";
		
		String[] colIds = {"LOT TYPE", "ADDRESS"};
		
		Services.printQueryOutput(query, colIds, conn);
		
		while(true) {
			System.out.println("Enter 1 to go back");	
			int option = scanner.nextInt();
			if(option == 1) {
				break;	
			}
		}
	}

	//@Override
	public void viewCurrentParkingSpot() {
		//3.B.3
		//TODO : TEST
		
		System.out.println("STUDENT View current Parking information");
		
		String query = "SELECT ASSIGNED.PARKING_SPOT_ID_N , LOT.PARKING_TYPE_V, LOT.ADDRESS_V, ASSIGNED.PERMIT_ID_N "
					+ "FROM PARKING_LOT LOT , PARKING_SPOT SPOT, ASSIGNED_PARKING ASSIGNED, PARKING_REQUEST REQUEST  "
					+ "WHERE SPOT.PARKING_LOT_ID_N = LOT.PARKING_LOT_ID_N AND "
						+ "ASSIGNED.PARKING_SPOT_ID_N = SPOT.PARKING_SPOT_ID_N AND "
						+ "REQUEST.PERSON_ID_N = " + Integer.toBinaryString(loginId);
		
		String[] colIds = {"SPOT #", "LOT TYPE", "LOT ADDR", "PERMIT #"};
		
		Services.printQueryOutput(query, colIds, conn);
		
		while(true) {
			System.out.println("Enter 1 to go back");	
			int option = scanner.nextInt();
			if(option == 1) {
				break;	
			}
		}	
	}

	//@Override
	public void renewParkingSpot() {
		
		System.out.println("STUDENT Renew Parking Spot");
		
		//TODO Enter the query for STUDENT here
		
	}

	//@Override
	public void returnParkingSpot() {
		
		System.out.println("STUDENT Return Parking Spot");
		
		//TODO Enter the query for STUDENT here
		
	}

	//@Override
	public void viewRequestStatus() {		
		//3.B.6
		//TODO : TEST
		
		System.out.println("STUDENT View Request Status");
		
		String query = "SELECT REQUEST.PARKING_REQUEST_ID_N, REQUEST.VEHICLE_TYPE_V, "
							+ "REQUEST.HANDICAP_STATUS_V, REQUEST.NEARBY_OPTION_V, "
							+ "REQUEST.REQUEST_STATUS_V "
					+ "FROM PARKING_REQUEST REQUEST "
					+ "WHERE REQUEST.PARKING_REQUEST_ID_N = (SELECT MAX(REQ.PARKING_REQUEST_ID_N) "
					+ "FROM PARKING_REQUEST REQ "
					+ "WHERE REQ.PARKING_REQUEST_ID_N = " + Integer.toString(loginId) + ")";
		
		String[] colIds = {"REQUEST #", "VEHICLE TYPE", "HANDICAP STATUS", "NEARBY OPTION", "STATUS"};
		
		Services.printQueryOutput(query, colIds, conn);
		
		while(true) {
			System.out.println("Enter 1 to go back");	
			int option = scanner.nextInt();
			if(option == 1) {
				break;	
			}
		}
	}

	//@Override
	public void raiseNewTicket() {
		
		System.out.println("STUDENT Raise New Ticket");
		
		//TODO Enter the query for STUDENT here
		
	}

	//@Override
	public void viewTicketStatus() {
		//3.C.2
		//TODO : TEST
		System.out.println("STUDENT View Ticket Status");
		
		String query1 = "SELECT T_ID"
				+ "WHERE STUDENT_ID = " + Integer.toString(loginId)
				+ "ORDER BY T_DATE";

		String[] colIds1 = {"TICKET #"};
		
		Services.printQueryOutput(query1, colIds1, conn);
		
		System.out.println("Enter TICKET # for more info");
		String option = scanner.nextLine();
		
		// go back on seeing 0
		if(option.equals("0")) {
			return;
		}
		
		String query2 = "SELECT T_ID, T_DESC, T_DATE, STUDENT_NAME "
					+ "FROM MAINTENANCE_VIEW "
					+ "WHERE T_ID = " + option + "STUDENT_ID = " + Integer.toString(loginId)
					+ "ORDER BY T_DATE";
		
		String[] colIds2 = {"TICKET #", "DESCRIPTION", "DATE", "STUDENT"};
		
		Services.printQueryOutput(query2, colIds2, conn);
	}
	
	//@Override
	public void viewProfile() {
		//3.D.1
		//TODO : TEST
		System.out.println("STUDENT View Profile");
		
		String query = "SELECT PERSON_ID_N , FIRST_NAME_V, LAST_NAME_V, PHONE_NUM_N, "
								+ "ALT_PHONE_NUM_N, TO_CHAR(PERSON_DOB_DT,'DD-MON-YYYY'), "
								+ "PERSON_SEX_V, PERSON_CATEGORY_V, PERSON_NATIONALITY_V, "
								+ "CURRENT_STATUS_V, PERSON_COURSE_V, PERSON_TYPE_V "
					+ "FROM PERSON "
					+ "WHERE PERSON_ID_N = " + Integer.toString(loginId);
		
		String[] colIds = {"PERSON #", "FIRST NAME", "LAST NAME", "PHONE", "ALT PHONE", 
				"DOB", "SEX", "CATEGORY", "NATIONALITY", "STATUS", "COURSE", "TYPE"};
		
		Services.printQueryOutput(query, colIds, conn);
		
		while(true) {
			System.out.println("Enter 1 to go back");	
			int option = scanner.nextInt();
			if(option == 1) {
				break;
			}
		}
		
	}

	//@Override
	public void updateProfile() {
		
		System.out.println("STUDENT Update Profile");
		
		String pid = Integer.toString(loginId);
		String query = null;
		String update = null;
		
		System.out.print("Enter First name : ");		
		update = scanner.nextLine();
		query = "UPDATE PERSON SET FIRST_NAME_V = " + update  + "WHERE PERSON_ID_N = " + pid;
		Services.updateStatement(query, conn);

		System.out.print("Enter Last name : ");		
		update = scanner.nextLine();
		query = "UPDATE PERSON SET LAST_NAME_V = " + update  + "WHERE PERSON_ID_N = " + pid;
		Services.updateStatement(query, conn);
		
		System.out.print("Enter phone number : ");		
		update = scanner.nextLine();
		query = "UPDATE PERSON SET PHONE_NUM_N = " + update  + "WHERE PERSON_ID_N = " + pid;
		Services.updateStatement(query, conn);
		
		System.out.print("Enter alternate phone number : ");		
		update = scanner.nextLine();
		query = "UPDATE PERSON SET ALT_PHONE_NUM_N = " + update  + "WHERE PERSON_ID_N = " + pid;
		Services.updateStatement(query, conn);
		
		try{
			query = "UPDATE PERSON SET PERSON_DOB_DT = ? WHERE PERSON_ID_N = " + pid;
			
			System.out.print("Enter DOB year : ");
			int year = scanner.nextInt();
			
			System.out.print("Enter DOB month : ");
			int month = scanner.nextInt();
			
			System.out.print("Enter DOB day : ");
			int day = scanner.nextInt();
			
			@SuppressWarnings("deprecation")
			Date date = new Date(year, month, day);
			
			PreparedStatement prepStmt = conn.prepareStatement(query);
			prepStmt.setDate(1, date);
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.print("DOB update failed. " + e);
		}
		
		System.out.print("Enter sex : ");		
		update = scanner.nextLine();
		query = "UPDATE PERSON SET PERSON_SEX_V = " + update  + "WHERE PERSON_ID_N = " + pid;
		Services.updateStatement(query, conn);
		
		System.out.print("Enter category : ");		
		update = scanner.nextLine();
		query = "UPDATE PERSON SET PERSON_CATEGORY_V = " + update  + "WHERE PERSON_ID_N = " + pid;
		Services.updateStatement(query, conn);
		
		System.out.print("Enter nationality : ");		
		update = scanner.nextLine();
		query = "UPDATE PERSON SET PERSON_NATIONALITY_V = " + update  + "WHERE PERSON_ID_N = " + pid;
		Services.updateStatement(query, conn);
		
		System.out.print("Enter course : ");		
		update = scanner.nextLine();
		query = "UPDATE PERSON SET PERSON_COURSE_V = " + update  + "WHERE PERSON_ID_N = " + pid;
		Services.updateStatement(query, conn);
	}
}
