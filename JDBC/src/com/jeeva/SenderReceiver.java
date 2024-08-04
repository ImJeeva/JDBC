package com.jeeva;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SenderReceiver {
	static Scanner scan=new Scanner(System.in);
	static Connection connection;
	static Statement statement;
	static PreparedStatement pstatement1;
	static PreparedStatement pstatement2;

	static ResultSet res;
	static String url="jdbc:mysql://localhost:3306/demodatabases";
	static String uname="root";
	static String password="Imjeeva@888";
	static String query="select* from employee";
	static String ing="Update employee set salary=salary+? where e_name=?";
	static String deg="Update employee set salary=salary-? where e_name=?"; 

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url,uname,password);
			statement=connection.createStatement();
			output(res,statement,query);
			
			pstatement1=connection.prepareCall(ing);
			System.out.println("enter how much amout you want send");
			int amount=scan.nextInt();
			pstatement1.setInt(1, amount);
			System.out.println("enter the name");
			String reciver=scan.next();
			pstatement1.setString(2, reciver);
			
			
			pstatement2=connection.prepareStatement(deg);
			pstatement2.setInt(1, amount);
			System.out.println("sender name");
			String sender=scan.next();
			pstatement2.setString(2, sender);
			pstatement1.addBatch();
			pstatement2.addBatch();
			
			int [] a=pstatement1.executeBatch()	;
			int [] b=pstatement2.executeBatch()	;

			
			
			
//			int b=pstatement1.executeUpdate();
//			int a=pstatement2.executeUpdate();
			output(res,statement,query);
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void output(ResultSet res ,Statement statement,String query) throws Exception {
		res=statement.executeQuery(query);
		
	 while(res.next()) {
		 System.out.printf("%-2d |%-10s |%-17s |%-15s |%d\n",res.getInt("e_id"),res.getString("e_name"),res.getString("email"),res.getString("department"),
				           res.getInt("salary"));
	 }
		
		
	}
	

}
