package com.jeeva;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Curd {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/demodatabases";
		String user = "root";
		String password = "Imjeeva@888";
		Connection connection = null;
		Statement statement = null;
		ResultSet res = null;
		PreparedStatement pstatement = null;
		String input;
		String jeeva = "select * from employee";
		String query = "insert into employee  (e_id,e_name,email,department,salary)values(?,?,?,?,?)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			

			System.out.println("-----------------------------------------------------");
			process(res, statement, jeeva);
			System.out.println("-----------------------------------------------------");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------------------------------");
			
			

			pstatement = connection.prepareStatement(query);
			do {
				System.out.println("Id:");
				int id = scan.nextInt();
				pstatement.setInt(1, id);
				System.out.println("NAME:");
				String name = scan.next();
				pstatement.setString(2, name);
				System.out.println("EMAIL:");
				String email = scan.next();
				pstatement.setString(3, email);
				System.out.println("DEPARMENT:");
				String dep = scan.next();
				pstatement.setString(4, dep);
				System.out.println("SALARY:");
				int sal = scan.nextInt();
				pstatement.setInt(5, sal);
				pstatement.executeUpdate();
				System.out.println("do you want inset data (yes/no)");
				input = scan.next();
			} while (input.equalsIgnoreCase("yes"));
			

			System.out.println("-----------------------------------------------------");
			process(res, statement, jeeva);
			System.out.println("-----------------------------------------------------");
			
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void process(ResultSet res, Statement statement, String jeeva) {

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
