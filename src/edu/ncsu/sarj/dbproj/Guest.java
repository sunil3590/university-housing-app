package edu.ncsu.sarj.dbproj;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
	public void viewCurrentInvoice() {
		//5.A.1.1
		//TODO : TEST
		System.out.println("GUEST View Current Invoice");
		
		String query = "SELECT L.LINEITEMS_TYPE_V , L.LINEITEM_PRICE_N , TO_CHAR(I.DUE_DATE_DT,'DD-MON-YYYY') "
					+ "FROM STUDENT_INVOICE I, INVOICE_LINEITEMS L "
					+ "WHERE L.INVOICE_ID_N = I.INVOICE_ID_N  AND I.STUDENT_ID_N  = "+ Integer.toString(loginId)
							+ " AND I.PERIOD_START_DT <= SYSDATE AND SYSDATE <= I.PERIOD_END_DT";
		
		String[] colIds = {"INVOICE LINE ITEM", "PRICE", "DUE DATE"};
		
		Services.printQueryOutput(query, colIds, conn);
		
		while(true) {
			System.out.println("Enter 1 to go back");	
			String option = scanner.nextLine();
			if(option.equals("1")) {
				break;
			}
		}
	}
	
	//@Override
	public void viewFormerInvoices() {
		//3.A.1.2
		//TODO : TEST
		String query1 = "SELECT I.INVOICE_ID_N, I.PERIOD_START_DT, I.PERIOD_END_DT "
					+ "FROM STUDENT_INVOICE I "
					+ "WHERE I.PERIOD_END_DT  < SYSDATE AND I.STUDENT_ID_N = " + this.loginId;

		String[] colIds1 = {"INVOICE #", "START", "END"};
		
		Services.printQueryOutput(query1, colIds1, conn);
		
		System.out.println("Enter INVOICE #(0 to go back) ");
		String option = scanner.nextLine();
		if(option.equals("0")) {
			return;
		}
		
		String query2 = "SELECT I.INVOICE_ID_N, I.PERIOD_START_DT, I.PERIOD_END_DT ,  I.DUE_DATE_DT , I.AMOUNT_DUE_N  "
					+ "FROM STUDENT_INVOICE I, INVOICE_LINEITEMS  L "
					+ "WHERE L.INVOICE_ID_N  = I.INVOICE_ID_N  AND I.STUDENT_ID_N  = "+ Integer.toString(loginId) +
					"AND I.PERIOD_END_DT  < SYSDATE AND I.INVOICE_ID_N = " + option;
		
		String[] colIds2 = {"INVOICE #", "START", "END", "DUE", "AMOUNT"};
		
		Services.printQueryOutput(query2, colIds2, conn);
		
		while(true) {
			System.out.println("Enter 1 to go back");	
			option = scanner.nextLine();
			if(option.equals("1")) {
				break;
			}
		}
	}

	//@Override
	public void viewCurrentLease() {
		//5.A.2.1
		//TODO : TEST
		
		System.out.println("GUEST View Current Lease");
	
		String query = "SELECT LEASE_NUMBER_N, NUMBER_OF_SEM_N, PLACE_NUMBER_N , DATE_OFJOIN_DT , "
							+ "DATE_OFLEAVING_DT , PAYMENT_OPTIONS_V , SECURITY_DEPOSIT_N , LEASE_PENALTY_N "
					+ "FROM LEASE "
					+ "WHERE PERSON_ID_N = " + Integer.toString(loginId);
		
		String[] colIds = {"LEASE #", "DURATION(SEM)", "PLACE #", 
				"JOIN DATE", "LEAVING DATE", "PAYMENT", "DEPOSIT", "LEASE PENALTY"};
		
		Services.printQueryOutput(query, colIds, conn);
		
		while(true) {
			System.out.println("Enter 1 to go back");	
			String option = scanner.nextLine();
			if(option.equals("1")) {
				break;
			}
		}
	}

	//@Override
	public void viewFormerLeases() {
		//5.A.2.2
		//TODO : TEST
		String query = "SELECT LEASE_NUMBER_N, DATE_OFJOIN_DT, DATE_OFTERMINATION_DT "
					+ "FROM LEASE_HISTORY "
					+ "WHERE PERSON_ID_N = " + Integer.toString(loginId);
		
		String[] colIds = {"LEASE #", "JOIN DATE", "TERMINATION DATE"};
		
		Services.printQueryOutput(query, colIds, conn);
		
		System.out.print("Enter lease ID(0 to go back) : ");
		
		String option = scanner.nextLine();
		if(option.equals("0")) {
			return;
		}
		
		String query2 = "SELECT LEASE_NUMBER_N, NUMBER_OF_SEM_N, PLACE_NUMBER_N , "
				+ "DATE_OFJOIN_DT , DATE_OFTERMINATION_DT , PAYMENT_OPTIONS_V , "
				+ "SECURITY_DEPOSIT_N , LEASE_PENALTY_N "
		+ "FROM LEASE_HISTORY "
		+ "WHERE PERSON_ID_N = " + Integer.toString(loginId);

		String[] colIds2 = {"LEASE #", "DURATION(SEM)", "PLACE #", "JOIN DATE", 
				"TERMINATION DATE", "PAYMENT", "DEPOSIT", "LEASE PENALTY"};

		Services.printQueryOutput(query2, colIds2, conn);		
	}

	//@Override
	public void newLeaseRequest() {
		//5.A.3.1
		//TODO : TEST
		System.out.println("Enter the following details");
		
		//period
		System.out.println("Duration of leasing (number of sems): ");
		String duration = scanner.nextLine();
		
		//preference
		String query = "SELECT BUILDING_ID_N, BUILDING_TYPE_V, BUILDING_NAME_V FROM HOUSING_MASTER";
		String[] colIds = {"BUILDING #", "TYPE", "BUILDING NAME"};
		Services.printQueryOutput(query, colIds, conn);
		
		System.out.println("Select 3 preferences: ");
		int i = 0;
		String[] pref = new String[3];
		while(i < 3){
			System.out.println("Select building number: ");
			pref[i++] = scanner.nextLine();
		}
		
		//join
		System.out.println("Enter join date (DD-MON-YYYY): ");
		String join = scanner.nextLine();
		
		//leave
		System.out.println("Enter leave date (DD-MON-YYYY): ");
		String leave = scanner.nextLine();
		
		//payment
		System.out.println("Monthly / yearly: (month / year)");
		String payment = scanner.nextLine();
		
		query = "INSERT INTO LEASE_REQUEST(REQUEST_ID_N, PERSON_ID_N, NUMBER_OF_SEM_N, "
				+ "HOUSING_FIRST_N, HOUSING_SECOND_N, HOUSING_THIRD_N, DATE_OFJOIN_DT, "
				+ "DATE_OFLEAVING_DT, PAYMENT_OPTIONS_V, REQUEST_STATUS_V) "
				+ "VALUES(SEQ_LEASE_REQ_ID.nextval," + this.loginId + "," + duration + "," + 
				pref[0] + "," + pref[1] + "," + pref[2] + ",'" + join + "','" + leave + "','" + payment + "'," + "'Pending')";
		
		Services.updateStatement(query, conn);
	}

	//@Override
	public void terminateLease() {
		//5.A.3.2
		//TODO : TEST
		System.out.println("GUEST Terminate Lease");
		
		//show current lease
		this.viewCurrentLease();
		
		//lease id
		System.out.println("Confirm by entering lease # (0 to go back) : ");
		String lease = scanner.nextLine();
		if(lease.equals("0")) {
			System.out.println("Going back.");
			return;
		}
		
		//date
		System.out.println("When do you want to terminate(DD-MON-YYYY) : ");
		String date = scanner.nextLine();
		
		//reason
		System.out.println("Reason to terminate : ");
		String reason = scanner.nextLine();
		
		String query = "INSERT INTO LEASE_TERMINATION_REQUEST "
				+ "VALUES(SEQ_LEASE_TERM_ID.nextval, " + this.loginId + "," + 
				lease + ",'" + reason + "','" + date + "','Pending')";
		
		Services.updateStatement(query, conn);
	}

	private void viewLeaseRequest() {
		String query = "SELECT REQUEST_ID_N, PERSON_ID_N, NUMBER_OF_SEM_N, DATE_OFJOIN_DT,"
							+ "DATE_OFLEAVING_DT, PAYMENT_OPTIONS_V, REQUEST_STATUS_V "
					+ "FROM LEASE_REQUEST "
					+ "WHERE PERSON_ID_N=" + this.loginId;
		
		String[] colIds = {"REQUEST #", "PERSON #", "DURATION(SEM)", 
				"JOIN DATE", "LEAVING DATE", "PAYMENT", "STATUS"};
		
		Services.printQueryOutput(query, colIds, conn);
	}
	
	private void viewTerminateRequest() {
		String query = "SELECT TERMINATION_ID_N , LEASE_NUMBER_N , REASON_V  , TERMINATION_STATUS_V  "
				+ "FROM LEASE_TERMINATION_REQUEST LT "
				+ "WHERE PERSON_ID_N=" + this.loginId
				+ " ORDER BY TERMINATION_ID_N";

		String[] colIds = {"REQUEST #", "LEASE #", "REASON", "STATUS"};

		Services.printQueryOutput(query, colIds, conn);
	}
	
	//@Override
	public void viewRequest() {
		//5.A.4.1
		//TODO : TEST
		System.out.println("GUEST View Request by Student");
		
		System.out.println("STUDENT View LEASE Requests");
		this.viewLeaseRequest();

		System.out.println("STUDENT View TERMINATE Requests");
		this.viewTerminateRequest();

		while(true) {
			System.out.println("Enter 1 to go back");	
			String option = scanner.nextLine();
			if(option.equals("1")) {
				break;
			}
		}
	}

	//@Override
	public void cancelRequest() {
		//5.A.4.2
		//TODO : TEST
		System.out.println("GUEST View Request");
		
		// show current requests
		this.viewRequest();
		
		// ask which type of request to cancel
		System.out.println("Enter type of request to cancel(1 = lease, 2 = termination) : ");
		String type = scanner.nextLine();
		
		// ask which request to cancel
		System.out.println("Enter REQUEST # to cancel : ");
		String rid = scanner.nextLine();
		
		if(type.equals("1")) {
			String query = "UPDATE LEASE_REQUEST SET REQUEST_STATUS_V='Cancelled' WHERE REQUEST_ID_N = " + rid;
			Services.updateStatement(query, conn);
		} else if(type.equals("2")) {
			String query = "UPDATE LEASE_TERMINATION_REQUEST  "
					+ "SET  TERMINATION_STATUS_V = 'Cancelled' "
					+ "WHERE TERMINATION_ID_N =" + rid;
			Services.updateStatement(query, conn);
		}
		
		while(true) {
			System.out.println("Enter 1 to go back");	
			String option = scanner.nextLine();
			if(option.equals("1")) {
				break;
			}
		}
	}

	//@Override
	public void viewVacancy() {
		//5.A.5
		//TODO : TEST

		System.out.println("GUEST Vacant Residence Halls");
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
			String option = scanner.nextLine();
			if(option.equals("1")) {
				break;
			}
		}
	}
	
	//@Override
	public void requestParkingSpot() {
		//5.B.1
		//TODO - TEST
		System.out.println("GUEST Request Parking");
		
		String query;
		
		//vehicle type
		System.out.println("What is the parking spot type? # (Handicapped , Bike, Small Car, Large Car) : ");
		String vehicleType = scanner.nextLine();
		
		String handicapped = "No";
		if(vehicleType.toLowerCase().contains("handicap"))
			handicapped = "Yes";
		
		System.out.println("Do you wish to have a spot near your housing? (Yes, No) : ");
		String nearByOption = scanner.nextLine();
		
		query = "INSERT INTO PARKING_REQUEST (PARKING_REQUEST_ID_N,PERSON_ID_N, VEHICLE_TYPE_V, "
				+ " HANDICAP_STATUS_V, NEARBY_OPTION_V,REQUEST_STATUS_V) VALUES "
				+ " (SEQ_PARK_REQ_ID.NEXTVAL,"+this.loginId+", '"+vehicleType+"', '"+handicapped+"', "
				+  " '"+nearByOption+"' ,'Pending') ";
		
		Services.updateStatement(query, conn);
	}

	//@Override
	public void viewParkingInformation() {
		//5.B.2
		//TODO : TEST
		
		System.out.println("GUEST View Parking information");
		
		System.out.println("Parking lots");
		String query = "SELECT LOT.PARKING_TYPE_V, LOT.ADDRESS_V "
				+ "FROM PARKING_LOT LOT "
				+ "WHERE LOT.PARKING_TYPE_V='General'";

		String[] colIds1 = {"LOT TYPE", "ADDRESS"};
		
		Services.printQueryOutput(query, colIds1, conn);
		
		System.out.println("Vacant SLOTS");
		query = "SELECT LOT.PARKING_TYPE_V, LOT.ADDRESS_V, SPOT.PARKING_SPOT_ID_N ,PTYPE.SPOT_TYPE_V  "
				+ "FROM PARKING_LOT LOT , PARKING_SPOT SPOT , PARKING_SPOT_TYPE PTYPE "
				+ "WHERE SPOT.PARKING_LOT_ID_N = LOT.PARKING_LOT_ID_N AND "
				+ "SPOT.SPOT_TYPE_N = PTYPE.SPOT_TYPE_ID_N AND "
				+ "SPOT.PARKING_SPOT_ID_N NOT IN (SELECT ASS.PARKING_SPOT_ID_N FROM ASSIGNED_PARKING ASS) "
				+ "AND LOT.PARKING_TYPE_V='General'";
		
		String[] colIds2 = {"LOT TYPE", "ADDRESS"};
		
		Services.printQueryOutput(query, colIds2, conn);
		
		while(true) {
			System.out.println("Enter 1 to go back");	
			String option = scanner.nextLine();
			if(option.equals("1")) {
				break;
			}
		}
	}

	//@Override
	public void viewCurrentParkingSpot() {
		//5.B.3
		//TODO : TEST
		
		System.out.println("GUEST View current Parking information");
		
		String query = "SELECT ASSIGNED.PARKING_SPOT_ID_N , LOT.PARKING_TYPE_V, LOT.ADDRESS_V, ASSIGNED.PERMIT_ID_N "
					+ "FROM PARKING_LOT LOT , PARKING_SPOT SPOT, ASSIGNED_PARKING ASSIGNED, PARKING_REQUEST REQUEST  "
					+ "WHERE SPOT.PARKING_LOT_ID_N = LOT.PARKING_LOT_ID_N AND "
						+ "ASSIGNED.PARKING_SPOT_ID_N = SPOT.PARKING_SPOT_ID_N AND "
						+ "REQUEST.PERSON_ID_N = " + Integer.toString(loginId);
		
		String[] colIds = {"SPOT #", "LOT TYPE", "LOT ADDR", "PERMIT #"};
		
		Services.printQueryOutput(query, colIds, conn);
		
		while(true) {
			System.out.println("Enter 1 to go back");	
			String option = scanner.nextLine();
			if(option.equals("1")) {
				break;	
			}
		}	
	}

	//@Override
	public void renewParkingSpot() {
		//5.B.4
		//TODO - TEST
		System.out.println("GUEST Renew Parking Spot");
		
		String query;
		viewCurrentParkingSpot();
		
		System.out.println("Please enter the Spot # : ");
		scanner.nextLine();
		
		query = " INSERT INTO PARKING_REQUEST(PARKING_REQUEST_ID_N,PERSON_ID_N,VEHICLE_TYPE_V,HANDICAP_STATUS_V,NEARBY_OPTION_V,REQUEST_STATUS_V) VALUES (SEQ_PARK_REQ_ID.NEXTVAL,"+this.loginId+", " +
				" (SELECT P1.VEHICLE_TYPE_V FROM PARKING_REQUEST P1 WHERE P1.PARKING_REQUEST_ID_N =( SELECT MAX(P2.PARKING_REQUEST_ID_N) FROM PARKING_REQUEST  P2 WHERE P2.REQUEST_STATUS_V='Processed' AND P2.PERSON_ID_N = "+this.loginId+")), " +
				" (SELECT P1.HANDICAP_STATUS_V FROM PARKING_REQUEST P1 WHERE P1.PARKING_REQUEST_ID_N =( SELECT MAX(P2.PARKING_REQUEST_ID_N) FROM PARKING_REQUEST  P2 WHERE P2.REQUEST_STATUS_V='Processed' AND P2.PERSON_ID_N = "+this.loginId+")), "+
				" (SELECT P1.NEARBY_OPTION_V FROM PARKING_REQUEST P1 WHERE P1.PARKING_REQUEST_ID_N =( SELECT MAX(P2.PARKING_REQUEST_ID_N) FROM PARKING_REQUEST  P2 WHERE P2.REQUEST_STATUS_V='Processed' AND P2.PERSON_ID_N = "+this.loginId+")), 'Pending') ";
		
		Services.updateStatement(query, conn);
	}

	//@Override
	public void returnParkingSpot() {
		//5.B.5
		//TODO - TEST
		System.out.println("GUEST Return Parking Spot");
		
		String query;
		
		viewCurrentParkingSpot();
		
		System.out.println("Please enter the Spot # : ");
		scanner.nextLine();
		
		query = " UPDATE PARKING_REQUEST SET REQUEST_STATUS_V = 'Completed' "
				+ " WHERE PARKING_REQUEST_ID_N = (SELECT max(P1.PARKING_REQUEST_ID_N) "
							+ "FROM PARKING_REQUEST P1 "
							+ "WHERE  P1.REQUEST_STATUS_V ='Processed' "
							+ "	AND P1.PERSON_ID_N="+this.loginId+") ";	
		Services.updateStatement(query, conn);
		
		query = " DELETE FROM ASSIGNED_PARKING WHERE LEASE_NUMBER_N = "
				+ "(SELECT L.LEASE_NUMBER_N "
				+ "FROM LEASE L "
				+ "WHERE L.PERSON_ID_N = "+this.loginId+"  ) ";
		Services.updateStatement(query, conn);
	}

	//@Override
	public void viewRequestStatus() {		
		//5.B.6
		//TODO : TEST
		
		System.out.println("GUEST View Request Status");
		
		String query = "SELECT REQUEST.PARKING_REQUEST_ID_N, REQUEST.VEHICLE_TYPE_V, "
							+ "REQUEST.HANDICAP_STATUS_V, REQUEST.NEARBY_OPTION_V, "
							+ "REQUEST.REQUEST_STATUS_V "
					+ "FROM PARKING_REQUEST REQUEST "
					+ "WHERE REQUEST.PARKING_REQUEST_ID_N = (SELECT MAX(REQ.PARKING_REQUEST_ID_N) "
					+ "FROM PARKING_REQUEST REQ "
					+ "WHERE REQ.PERSON_ID_N = " + Integer.toString(loginId) + ")";
		
		String[] colIds = {"REQUEST #", "VEHICLE TYPE", "HANDICAP STATUS", "NEARBY OPTION", "STATUS"};
		
		Services.printQueryOutput(query, colIds, conn);
		
		while(true) {
			System.out.println("Enter 1 to go back");	
			String option = scanner.nextLine();
			if(option.equals("1")) {
				break;
			}
		}
	}

	//@Override
	public void raiseNewTicket() {
		//5.C.1
		//TODO : TEST
		System.out.println("GUEST Request Parking");
		
		String query = "SELECT TICKET_ID_N, TICKET_DESC_V  FROM TICKETING_MASTER";
	
		String[] colIds = {"TICKET #", "DESC"};
		
		Services.printQueryOutput(query, colIds, conn);
		
		System.out.println("Please enter the Ticket # for the type of the problem : ");
		String issueType = scanner.nextLine();
		
		
		query = "INSERT INTO TICKET_LIST(TICKET_LIST_ID_N ,TICKET_ID_N ,"
				+ "TICKETED_DATE_DT , STUDENT_ID_N , PROBLEM_LOCATION_N , TICKET_STATUS_V ) "
				+ "VALUES (SEQ_TICKET_LIST_ID.NEXTVAL, "+ issueType+ ", SYSDATE, "+ Integer.toString(loginId) + ", "
				+ "(SELECT L.PLACE_NUMBER_N FROM LEASE L "
				+ "WHERE L.PERSON_ID_N = " + this.loginId + "),'Pending')";
		
		Services.updateStatement(query, conn);
		
		while(true) {
			System.out.println("Enter 1 to go back");	
			String option = scanner.nextLine();
			if(option.equals("1")) {
				break;
			}
		}
	}

	//@Override
	public void viewTicketStatus() {
		//5.C.2
		//TODO : TEST
		System.out.println("GUEST View Ticket Status");
		
		String query1 = "SELECT T_ID, T_DESC FROM MAINTENANCE_VIEW "
				+ "WHERE STUDENT_ID = " + Integer.toString(loginId)
				+ "ORDER BY T_DATE";

		String[] colIds1 = {"TICKET #", "DESCRIPTION"};
		
		Services.printQueryOutput(query1, colIds1, conn);
		
		System.out.println("Enter TICKET # for more info");
		String option = scanner.nextLine();
		
		// go back on seeing 0
		if(option.equals("0")) {
			return;
		}
		
		String query2 = "SELECT T_ID, T_DESC, T_DATE, STUDENT_NAME "
					+ "FROM MAINTENANCE_VIEW "
					+ "WHERE T_ID = " + option + " AND STUDENT_ID = " + Integer.toString(loginId)
					+ "ORDER BY T_DATE";
		
		String[] colIds2 = {"TICKET #", "DESCRIPTION", "DATE", "STUDENT"};
		
		Services.printQueryOutput(query2, colIds2, conn);
	}
	
	//@Override
	public void viewProfile() {
		//5.D.1
		//TODO : TEST
		System.out.println("--> STUDENT View Profile");
		
		String query = "SELECT PERSON_ID_N , FIRST_NAME_V, LAST_NAME_V, PHONE_NUM_N, "
								+ "ALT_PHONE_NUM_N, TO_CHAR(PERSON_DOB_DT,'DD-MON-YYYY'), "
								+ "PERSON_SEX_V, PERSON_CATEGORY_V, PERSON_NATIONALITY_V, "
								+ "CURRENT_STATUS_V, PERSON_COURSE_V, FAMILY_STUDENT_V "
					+ "FROM PERSON "
					+ "WHERE PERSON_ID_N = " + Integer.toString(loginId);
		
		String[] colIds = {"PERSON #", "FIRST NAME", "LAST NAME", "PHONE", "ALT PHONE", 
				"DOB", "SEX", "CATEGORY", "NATIONALITY", "STATUS", "COURSE", "TYPE"};
		
		Services.printQueryOutput(query, colIds, conn);
		
		while(true) {
			System.out.println("Enter 1 to go back");	
			String option = scanner.nextLine();
			if(option.equals("1")) {
				break;
			}
		}
		
	}

	//@Override
	public void updateProfile() {
		
		System.out.println("--> STUDENT Update Profile");
		
		String pid = Integer.toString(loginId);
		String query = null;
		String update = null;
		
		System.out.print("Enter First name : ");
		update = scanner.nextLine();
		query = "UPDATE PERSON SET FIRST_NAME_V = '" + update + "' WHERE PERSON_ID_N = " + pid;
		Services.updateStatement(query, conn);

		System.out.print("Enter Last name : ");		
		update = scanner.nextLine();
		query = "UPDATE PERSON SET LAST_NAME_V = '" + update + "' WHERE PERSON_ID_N = " + pid;
		Services.updateStatement(query, conn);
		
		System.out.print("Enter phone number : ");		
		update = scanner.nextLine();
		query = "UPDATE PERSON SET PHONE_NUM_N = " + update + " WHERE PERSON_ID_N = " + pid;
		Services.updateStatement(query, conn);
		
		System.out.print("Enter alternate phone number : ");		
		update = scanner.nextLine();
		query = "UPDATE PERSON SET ALT_PHONE_NUM_N = " + update  + "WHERE PERSON_ID_N = " + pid;
		Services.updateStatement(query, conn);
		
		try{
			query = "UPDATE PERSON SET PERSON_DOB_DT = ? WHERE PERSON_ID_N = " + pid;
			
			System.out.print("Enter DOB year : ");
			int year = Integer.parseInt(scanner.nextLine()) - 1990;
			
			System.out.print("Enter DOB month : ");
			int month = Integer.parseInt(scanner.nextLine()) - 1;
			
			System.out.print("Enter DOB day : ");
			int day = Integer.parseInt(scanner.nextLine());
			
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
		query = "UPDATE PERSON SET PERSON_SEX_V = '" + update  + "' WHERE PERSON_ID_N = " + pid;
		Services.updateStatement(query, conn);
		
		System.out.print("Enter category : ");		
		update = scanner.nextLine();
		query = "UPDATE PERSON SET PERSON_CATEGORY_V = '" + update  + "' WHERE PERSON_ID_N = " + pid;
		Services.updateStatement(query, conn);
		
		System.out.print("Enter nationality : ");		
		update = scanner.nextLine();
		query = "UPDATE PERSON SET PERSON_NATIONALITY_V = '" + update  + "' WHERE PERSON_ID_N = " + pid;
		Services.updateStatement(query, conn);
		
		System.out.print("Enter course : ");		
		update = scanner.nextLine();
		query = "UPDATE PERSON SET PERSON_COURSE_V = '" + update  + "' WHERE PERSON_ID_N = " + pid;
		Services.updateStatement(query, conn);
	}
}
