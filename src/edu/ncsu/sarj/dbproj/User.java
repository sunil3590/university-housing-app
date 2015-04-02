package edu.ncsu.sarj.dbproj;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
	
	private Connection conn = null;
	private Scanner scanner = null;
	private int loginId = -1;
	private UserType userType = UserType.UNKNOWN;
	
	public User(Connection conn, Scanner scanner) {		
		// a connection is always needed
		if(conn == null || scanner == null) {
			System.out.println("User.User() ~ null input");
			return;
		}
		
		this.conn = conn;
		this.scanner = scanner;
	}
	
	public UserType getUserType() {
		return userType;
	}

	public int getLoginId() {
		return loginId;
	}
	
	public int login() {
		Scanner scanner = this.scanner;
		// read any old data
		scanner.nextLine();
		
		//TODO: Handle Guest and Student / Admin separately
		// Changes pertaining to this should be done once suitable changes
		// are made in backend database.
		
		// get credentials
		System.out.println("Enter user ID : ");
		String uid = scanner.nextLine();
		System.out.println("Enter password : ");
		String pass = scanner.nextLine();
		
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
			} else if(userType.equals("admin")) {
				this.userType = UserType.ADMIN;
			} else if(userType.equals("guest")) {
				this.userType = UserType.GUEST;
			} else {
				this.userType = UserType.UNKNOWN;
			}
			
			return this.loginId;
		} catch (SQLException e) {
			System.out.println("User.login() ~ login failed " + e);
			return -1;
		}
	}
}
