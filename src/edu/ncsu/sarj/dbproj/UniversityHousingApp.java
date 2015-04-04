package edu.ncsu.sarj.dbproj;


import java.sql.Connection;
import java.util.Scanner;


/*
 * Main class
 */
public class UniversityHousingApp {
	
	private static Connection conn;
	private static Scanner scanner;
	
	//Student is the base class, passing its reference as parameter
	private static void handleUser(Person person) {
		
		
		boolean keepRunning = true;
		while(keepRunning) {
			
			//Display the options available to a Student
			System.out.println("\n\n" + "Hi " + person.loginId);
			System.out.println("What do you want to do next?");
			System.out.println("1 - Housing option");
			System.out.println("2 - Parking option");
			System.out.println("3 - Maintenance");
			System.out.println("4 - Profile");
			System.out.println("5 - Back");
			
			//TODO: Take input as string always and then use Integer.parseInt to convert to integer
			int option = scanner.nextInt();
			switch(option) {
			case 1:
				handleHousing(person);
				break;
			case 2:
				handleParking(person);
				break;
			case 3:
				handleMaintenance(person);
				break;
			case 4:
				handleProfile(person);
				break;
			case 5:
				return;
			default:
				System.out.println("Invalid choice");
			}
		}
		
		
	}
	
	//Helps user navigate through various options provided by Housing
	private static void handleHousing(Person student) {
		
		boolean keepRunning = true;
		while(keepRunning) {
			
			//Display the options available to a Student
			System.out.println("\n\n" + "Housing options");
			System.out.println("What do you want to do next?");
			System.out.println("1 - View Invoices");
			System.out.println("2 - View Leases");
			System.out.println("3 - New request");
			System.out.println("4 - View/Cancel requests");
			System.out.println("5 - View vacancy");
			System.out.println("6 - Back");
			
			
			//TODO: Take input as string always and then use Integer.parseInt to convert to integer
			int option = scanner.nextInt();
			switch(option) {
			case 1:
				viewInvoices(student);
				break;
			case 2:
				viewLeases(student);
				break;
			case 3:
				makeNewRequest(student);
				break;
			case 4:
				viewCancelRequest(student);
				break;
			case 5:
				student.viewVacancy();
				break;
			case 6:
				return;
			default:
				System.out.println("Invalid choice");
			}
		}
		
	}

	//Helps user navigate through various Parking options
	private static void handleParking(Person student) {
		
		boolean keepRunning = true;
		while(keepRunning) {
			
			//Display the options available to a Student
			System.out.println("\n\n" + "Parking options");
			System.out.println("What do you want to do next?");
			System.out.println("1 - Request new parking spot");
			System.out.println("2 - View parking lot information");
			System.out.println("3 - View current parking spot");
			System.out.println("4 - Renew parking spot");
			System.out.println("5 - Return parking spot");
			System.out.println("6 - View request status");
			System.out.println("7 - Back");
			
			
			//TODO: Take input as string always and then use Integer.parseInt to convert to integer
			int option = scanner.nextInt();
			switch(option) {
			case 1:
				student.requestParkingSpot();
				break;
			case 2:
				student.viewParkingInformation();
				break;
			case 3:
				student.viewCurrentParkingSpot();
				break;
			case 4:
				student.renewParkingSpot();
				break;
			case 5:
				student.returnParkingSpot();
				break;
			case 6:
				student.viewRequestStatus();
				break;
			case 7:
				System.out.println("7");
				return;
			default:
				System.out.println("Invalid choice");
			}
		}
		
	}
	
	private static void handleMaintenance(Person student) {
		
		boolean keepRunning = true;
		while(keepRunning) {
			
			//Display the options available to a Student
			System.out.println("\n\n" + "Maintenance options");
			System.out.println("What do you want to do next?");
			System.out.println("1 - New Ticket");
			System.out.println("2 - View Ticket Status");
			System.out.println("3 - Back");
			
			
			//TODO: Take input as string always and then use Integer.parseInt to convert to integer
			int option = scanner.nextInt();
			switch(option) {
			case 1:
				student.raiseNewTicket();
				break;
			case 2:
				student.viewTicketStatus();
				break;
			case 3:
				return;
			default:
				System.out.println("Invalid choice");
			}
		}
		
	}

	private static void handleProfile(Person student) {
		boolean keepRunning = true;
		while(keepRunning) {
			
			//Display the options available to a Student
			System.out.println("\n\n" + "Profile options");
			System.out.println("What do you want to do next?");
			System.out.println("1 - View Profile");
			System.out.println("2 - Update Profile");
			System.out.println("3 - Back");
			
			
			//TODO: Take input as string always and then use Integer.parseInt to convert to integer
			int option = scanner.nextInt();
			switch(option) {
			case 1:
				student.viewProfile();
				break;
			case 2:
				student.updateProfile();
				break;
			case 3:
				return;
			default:
				System.out.println("Invalid choice");
			}
		}
	}

	private static void viewInvoices(Person student) {
		
		boolean keepRunning = true;
		while(keepRunning) {
			
			//Display the options available to a Student
			System.out.println("\n\n" + "View Invoice options");
			System.out.println("What do you want to do next?");
			System.out.println("1 - View current invoice");
			System.out.println("2 - View former invoices");
			System.out.println("3 - Back");
			
			
			//TODO: Take input as string always and then use Integer.parseInt to convert to integer
			int option = scanner.nextInt();
			switch(option) {
			case 1:
				student.viewCurrentInvoice();
				break;
			case 2:
				student.viewFormerInvoices();
				break;
			case 3:
				return;
			default:
				System.out.println("Invalid choice");
			}
		}
		
	}

	private static void viewLeases(Person student) {
		boolean keepRunning = true;
		while(keepRunning) {
			
			//Display the options available to a Student
			System.out.println("\n\n" + "View Lease options");
			System.out.println("What do you want to do next?");
			System.out.println("1 - View current lease");
			System.out.println("2 - View former leases");
			System.out.println("3 - Back");
			
			
			//TODO: Take input as string always and then use Integer.parseInt to convert to integer
			int option = scanner.nextInt();
			switch(option) {
			case 1:
				student.viewCurrentLease();
				break;
			case 2:
				student.viewFormerLeases();
				break;
			case 3:
				System.out.println("3");
				return;
			default:
				System.out.println("Invalid choice");
			}
		}
		
	}

	private static void makeNewRequest(Person student) {
		boolean keepRunning = true;
		while(keepRunning) {
			
			//Display the options available to a Student
			System.out.println("\n\n" + "New Request options");
			System.out.println("What do you want to do next?");
			System.out.println("1 - New lease request");
			System.out.println("2 - Terminate lease request");
			System.out.println("3 - Back");
			
			
			//TODO: Take input as string always and then use Integer.parseInt to convert to integer
			int option = scanner.nextInt();
			switch(option) {
			case 1:
				student.newLeaseRequest();
				break;
			case 2:
				student.terminateLease();
				break;
			case 3:
				return;
			default:
				System.out.println("Invalid choice");
			}
		}
		
	}

	private static void viewCancelRequest(Person student) {
		boolean keepRunning = true;
		while(keepRunning) {
			
			//Display the options available to a Student
			System.out.println("\n\n" + "View/Cancel Request options");
			System.out.println("What do you want to do next?");
			System.out.println("1 - View request");
			System.out.println("2 - Cancel request");
			System.out.println("3 - Back");
			
			
			//TODO: Take input as string always and then use Integer.parseInt to convert to integer
			int option = scanner.nextInt();
			switch(option) {
			case 1:
				student.viewRequest();
				break;
			case 2:
				student.cancelRequest();
				break;
			case 3:
				return;
			default:
				System.out.println("Invalid choice");
			}
		}
		
	}

	private static void handleAdmin(Admin admin) {
		System.out.println("Hi admin");
		
		boolean keepRunning = true;
		while(keepRunning) {
			
			//Display the options available to a Student
			System.out.println("\n\n" + "Admin options");
			System.out.println("What do you want to do next?");
			System.out.println("1 - View new lease requests");
			System.out.println("2 - View terminate lease requests");
			System.out.println("3 - View maintenance tickets");
			System.out.println("4 - View parking requests");
			System.out.println("5 - Profile");
			System.out.println("6 - Back");
			
			
			//TODO: Take input as string always and then use Integer.parseInt to convert to integer
			int option = scanner.nextInt();
			switch(option) {
			case 1:
				admin.viewLeaseRequests();
				break;
			case 2:
				admin.viewTerminateLeaseRequests();
				break;
			case 3:
				admin.viewMaintenanceTickets();
				break;
			case 4:
				admin.viewParkingRequests();
				break;
			case 5:
				handleAdminProfile(admin);
				break;
			case 6:
				System.out.println("Back");
				return;
			default:
				System.out.println("Invalid choice");
			}
		}
		
		
	}

	private static void handleAdminProfile(Admin admin) {
		
		boolean keepRunning = true;
		while(keepRunning) {
			
			//Display the options available to a Student
			System.out.println("\n\n" + "Profile options");
			System.out.println("What do you want to do next?");
			System.out.println("1 - View Profile");
			System.out.println("2 - Update Profile");
			System.out.println("3 - Back");
			
			
			//TODO: Take input as string always and then use Integer.parseInt to convert to integer
			int option = scanner.nextInt();
			switch(option) {
			case 1:
				admin.viewProfile();
				break;
			case 2:
				admin.updateProfile();
				break;
			case 3:
				return;
			default:
				System.out.println("Invalid choice");
			}
		}
		
	}

	private static void handleGuest(Guest guest) {
		System.out.println("Hi guest");
	}
	
	public static void main(String[] args) {
		//Glorify ourselves
		System.out.println("*****************************************************");
		System.out.println("CSC540 - Project 1 - University Housing Application");
		System.out.println("{sdavang, aravi5, rravi, jlourde} @ncsu.edu");
		System.out.println("Let's start filling those accomadations!");
		System.out.println("*****************************************************");
		
		//Get a connection to the DB before you do anything else
		conn = DBConnFactory.getInstance().getConnection();
		if(conn == null) {
			// Nothing to clean up. Inform and exit
			System.out.println("UniversityHousingApp.main() ~ SQL connection error");
			System.exit(0);
		}
		
		System.out.println("Connected to database");
		
		// A scanner is closed only before exiting.
		// All other classes just use the scanner singleton object
		scanner = new Scanner(System.in);
		
		boolean keepRunning = true;
		
		// Take user input to login
		while(keepRunning) {
			System.out.println("\n\n" + "What do you want to do?");
			System.out.println("1 - User login");
			System.out.println("2 - Guest login");
			System.out.println("3 - Exit");	
			
			//TODO: Take input as string always and then use Integer.parseInt to convert to integer
			int login = scanner.nextInt();
			int result = 0;
			switch(login) {
			case 1:
			case 2:
				User user = new User(conn, scanner);
				
				result = user.login();
				if(result == -1) {
					System.out.println("Login failed. Try again.");
					break;
				}
				
				// Handle EVERYTHING else
				if(login == 1) {
					//A Student or an Admin
					if(user.getUserType() == UserType.STUDENT) {
						//User of the application is a Student
						Person student = new Student(user.getLoginId(), conn, scanner);
						handleUser(student);	
					} else if(user.getUserType() == UserType.ADMIN) {
						//User of the application is an Admin
						Admin admin = new Admin(user.getLoginId(), conn, scanner);
						handleAdmin(admin);
					} else {
						System.out.println("Login failed. Try again.");
						break;						
					}
				} else {
					//A Guest
					if(user.getUserType() == UserType.GUEST) {
						//User of the application is a Guest
						Guest guest = new Guest(user.getLoginId(), conn, scanner);
						handleUser(guest);						
					} else {
						System.out.println("Login failed. Try again.");
						break;						
					}
				}

				
				break;
				
			case 3:
				System.out.println("Exiting");
				
				keepRunning = false;
				
				break;
			default:
				System.out.println("Invalid choice");
			}
		}
		
		scanner.close();
		//terminate the DB connection before you quit
		DBConnFactory.getInstance().closeConnection();
	}

}
