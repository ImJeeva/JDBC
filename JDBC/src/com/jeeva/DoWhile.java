package com.jeeva;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DoWhile {
	static Connection connection;
	static Statement statement;
	static PreparedStatement pstatement; 
	static ResultSet res;
	static String url = "jdbc:mysql://localhost:3306/demodatabases";
	static String uname = "root";
	static String password = "Imjeeva@888";
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			String x = null;
			String query1 = "Select* from employee"; 
			String query = "Delete from employee where e_id=? ";
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, uname, password);
			statement = connection.createStatement();
			System.out.println("------------------------------------------------------");
			method(res, statement, query1);
			System.out.println("------------------------------------------------------");
			System.out.println("------------------------------------------------------");



			pstatement = connection.prepareStatement(query);
			
			
			do {
				System.out.println("enter the id of youn want delete.");
				int input = scan.nextInt();
				pstatement.setInt(1, input);
				int n = pstatement.executeUpdate();
				System.out.println("Delete more (yes/no");
				x = scan.next();
			} while (x.equalsIgnoreCase("yes"));

			
			System.out.println("------------------------------------------------------");
			method(res, statement, query1);
			System.out.println("------------------------------------------------------");
			System.out.println("------------------------------------------------------");



		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void method(ResultSet res, Statement statement, String jeeva) {

		try {
			res = statement.executeQuery(jeeva);
			while (res.next()) {
				System.out.printf("%-2d |%-8s| %-17s| %-10s |%d\n", res.getInt("e_id"), res.getString("e_name"),
						res.getString("email"), res.getString("department"), res.getInt("salary"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	
	
	
	

}
