package edu.ncsu.sarj.dbproj;

import java.sql.Connection;
import java.util.Scanner;

/*
 * Main class
 */
public class UniversityHousingApp {
	private static void handleStudent(Student student) {
		System.out.println("Hi student");
	}
	
	private static void handleAdmin(Admin admin) {
		System.out.println("Hi admin");
	}

	private static void handleGuest(Guest guest) {
		System.out.println("Hi guest");
	}
	
	public static void main(String[] args) {
		//glorify ourselves
		System.out.println("*****************************************************");
		System.out.println("CSC540 - Project 1 - University Housing Application");
		System.out.println("{sdavang, aravi5, rravi, jlourde} @ncsu.edu");
		System.out.println("Let's start filling those accomadations!");
		System.out.println("*****************************************************");
		
		//get a connection to the DB before you do anything else
		Connection conn = DBConnFactory.getInstance().getConnection();
		if(conn == null) {
			// nothing to clean up. inform and exit
			System.out.println("UniversityHousingApp.main() ~ SQL connection error");
			System.exit(0);
		}
		
		System.out.println("Connected to database");
		
		// a scanner is closed only before exiting. all other classes just use
		// the scanner singleton object
		Scanner scanner = new Scanner(System.in);
		
		boolean keepRunning = true;
		// take user input to login
		while(keepRunning) {
			System.out.println("What do you want to do?");
			System.out.println("1 - User login");
			System.out.println("2 - Guest login");
			System.out.println("3 - Exit");	
			
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
				
				// handle EVERYTHING else
				if(login == 1) {
					if(user.getUserType() == UserType.STUDENT) {
						Student student = new Student(user.getLoginId(), conn, scanner);
						handleStudent(student);	
					} else if(user.getUserType() == UserType.ADMIN) {
						Admin admin = new Admin(user.getLoginId(), conn, scanner);
						handleAdmin(admin);
					} else {
						System.out.println("Login failed. Try again.");
						break;						
					}
				} else {
					if(user.getUserType() == UserType.GUEST) {
						Guest guest = new Guest(user.getLoginId(), conn, scanner);
						handleGuest(guest);						
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
