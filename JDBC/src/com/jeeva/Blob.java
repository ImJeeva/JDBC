package com.jeeva;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.cj.jdbc.CallableStatement;



public class Blob {

	static Scanner scan = new Scanner(System.in);
	static Connection connection = null;
	static Statement staement = null;
	static CallableStatement cal = null;
	static ResultSet res = null;
	static PreparedStatement pstatement = null;
	static String url = "jdbc:mysql://localhost:3306/tap_food";
	static String uname = "root";
	static String password = "Imjeeva@888";
	static String query = "select * from employee";
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, uname, password);
			staement=connection.createStatement();
			System.out.println(res=staement.executeQuery("select img from image where id=1 "));;
			
			
//			pstatement=connection.prepareStatement("update image set img= ' C:\\Users\\User\\Pictures\\pizza.jpeg'    where id=1");
//			int x=pstatement.executeUpdate();
//			System.out.println(x);
			



		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


}
