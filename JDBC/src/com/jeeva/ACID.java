package com.jeeva;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ACID {

	static Scanner scan = new Scanner(System.in);
	static Connection connection = null;
	static Statement statement = null;
	static CallableStatement cal = null;
	static ResultSet res = null;
	static PreparedStatement pstatement = null;
	static String url = "jdbc:mysql://localhost:3306/demodatabases";
	static String uname = "root";
	static String password = "Imjeeva@888";
	static String query = "select * from employee";

	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, uname, password);
			connection.setAutoCommit(false);
			statement=connection.createStatement();
			System.out.println("------------------------------------------------------");
			output(res,statement,query);
			System.out.println("------------------------------------------------------");


			update();
			System.out.println("------------------------------------------------------");
			output(res,statement,query);
			System.out.println("------------------------------------------------------");




		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void update() throws Exception {
		String option=null;
		do {
			System.out.println("enter the sender name");
			String sender = scan.next();
			System.out.println("enter the receiver name");
			String receive = scan.next();
			System.out.println("enter the amount");
			int amount = scan.nextInt();
			int n=value(- amount, sender);
			int m=value(amount, receive);
			if(n==m) {
				connection.commit();
				System.out.println("Success");
			}
			else {
				connection.rollback();
				System.out.println("faliure");
			}
			System.out.println("do you want send again(yes/no)");
		    option =scan.next();
		}while(option.equalsIgnoreCase("yes"));

	}

	public static int value(int amount, String name) throws Exception {
		String receiver = "update employee set salary=salary+? where e_name=?";

		pstatement = connection.prepareStatement(receiver);
		pstatement.setInt(1, amount);
		pstatement.setString(2, name);
		return pstatement.executeUpdate();
	}

	public static void output(ResultSet res, Statement statement, String query) throws Exception {
		res = statement.executeQuery(query);

		while (res.next()) {
			System.out.printf("%-2d |%-12s| %-17s| %-10s |%d\n", res.getInt("e_id"), res.getString("e_name"),
					res.getString("email"), res.getString("department"), res.getInt("salary"));

		}

	}
	

}
