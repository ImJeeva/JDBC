package com.jeeva;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

public class StoreProcedure {
	static Connection connection;
	static String url="jdbc:mysql://localhost:3306/demodatabases";
	static String uname="root";
	static String password="Imjeeva@888";
	static Scanner scan =new Scanner(System.in);
	static String option;

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url,uname,password);
			CallableStatement prepareCall=connection.prepareCall("{call emp_count(?,?)}");
			
			do {	
				System.out.println("Emter the department");
				String dname=scan.next();
				prepareCall.setString(1, dname);		
				prepareCall.registerOutParameter(2,Types.INTEGER);
				
				
				prepareCall.execute();
				int a=prepareCall.getInt(2);
				System.out.println(a);
				System.out.println("do you know any more(Yes/No)");
				option=scan.next();
				

			}while(option.equalsIgnoreCase("yes"));
			System.out.println("Connection terminate...");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		

	}

}
