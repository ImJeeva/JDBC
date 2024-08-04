package com.jeeva;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Select {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/demodatabases";
		String username = "root";
		String password = "Imjeeva@888";
		Connection connection = null;
		Statement statement1 = null;
		PreparedStatement statement2 = null;
		ResultSet res = null;
		String query1 = "Select * from employee";
		String query2 = "select count('e_id') from `employee` where `department`=?";
 
		try { 
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
			statement1 = connection.createStatement();
			res = statement1.executeQuery(query1);
			process(res, statement1, query1);

			statement2 = connection.prepareStatement(query2);

			System.out.println("enter the department");
			String de = scan.next();

			statement2.setString(1, de); 
 
			res = statement2.executeQuery(); 

			process(res,statement1,query1);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
		} 

	}

	public static void process(ResultSet res, Statement statement1, String query1) {
		System.out.println("----------------------------------------------------");

		try {
			while (res.next()) {
				try {
					System.out.printf("|%2d |%-7s |%-17s |%-7s| %d\n", res.getInt("e_id"), res.getString("e_name"),
							res.getString("email"), res.getString("department"), res.getInt("salary"));

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("----------------------------------------------------");

	}

}
