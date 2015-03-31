package edu.ncsu.sarj.dbproj;

import java.sql.Connection;
import java.util.Scanner;

public class Student {
	private Connection conn = null;
	private Scanner scanner = null;
	private int loginId = -1;
	
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
}
