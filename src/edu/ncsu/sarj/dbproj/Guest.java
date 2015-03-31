package edu.ncsu.sarj.dbproj;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Guest {
	
	private Connection conn = null;
	private Scanner scanner = null;
	private int loginId = -1;
	
	public Guest(int loginId, Connection conn, Scanner scanner) {		
		// a connection is always needed
		if(conn == null || scanner == null || loginId < 0) {
			System.out.println("Guest.Guest() ~ null input");
			return;
		}
		
		this.conn = conn;
		this.scanner = scanner;
		this.loginId = loginId;
	}
}
