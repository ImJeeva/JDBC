package com.jeeva;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class BatchFile {
	  static String option;

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		Connection connection=null;
		Statement statement=null;
		PreparedStatement pstatement=null;
		ResultSet res=null;
		String url="jdbc:mysql://localhost:3306/demodatabases";
		String uname="root";
		String password="Imjeeva@888";
		String query="Select* from employee";
		String delete="delete from employee where e_id=?";
		
		try {
			
			connection=DriverManager.getConnection(url,uname,password);
			statement=connection.createStatement();
			output(res,statement,query);
			Class.forName("com.mysql.cj.jdbc.Driver");
			pstatement=connection.prepareStatement(delete);
			do {
			System.out.println("endet the crickter id you want delete");
			int id=scan.nextInt();
			pstatement.setInt(1, id);
			pstatement.addBatch();
			System.out.println("do you want any others?(yes/no");
			option=scan.next();
			}while(option.equalsIgnoreCase("yes"));
			int[]aa=pstatement.executeBatch();
			
		
			
			output(res,statement,query);
			
			
			
			
			

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void output(ResultSet res,Statement statement,String query) throws Exception{
		res=statement.executeQuery(query);
		while(res.next()) {
			System.out.printf("%-2d| %-10s |%-17s| %-10s |%d\n",res.getInt("e_id"),res.getString("e_name"),res.getString("email"),res.getString("department"),res.getInt("salary"));
		}
		
	}
	
}	



	


