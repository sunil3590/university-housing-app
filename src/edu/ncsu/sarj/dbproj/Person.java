package edu.ncsu.sarj.dbproj;

import java.sql.Connection;
import java.util.Scanner;

public abstract class Person {
	
	protected Connection conn = null;
	protected Scanner scanner = null;
	protected int loginId = -1;
	protected UserType userType = UserType.UNKNOWN;
	
	public abstract boolean login(String uid, String pass);
	
	public abstract void viewVacancy();

	public abstract void viewCurrentInvoice();

	public abstract void viewFormerInvoices();

	public abstract void viewCurrentLease();

	public abstract void viewFormerLeases();

	public abstract void newLeaseRequest();

	public abstract void terminateLease();

	public abstract void viewRequest();

	public abstract void cancelRequest();

	public abstract void requestParkingSpot();

	public abstract void viewParkingInformation();

	public abstract void viewCurrentParkingSpot();

	public abstract void renewParkingSpot();

	public abstract void returnParkingSpot();

	public abstract void viewRequestStatus();

	public abstract void raiseNewTicket();
	
	public abstract void viewTicketStatus();

	public abstract void viewProfile();

	public abstract void updateProfile();

}
