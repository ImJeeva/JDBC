package com.jeeva;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class jd {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/demodatabases";
		String username = "root";
		String password = "Imjeeva@888";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = "select * from employee where `department`= ? ";
		Scanner scan = new Scanner(System.in);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.prepareStatement(query);
			System.out.println("emter department you want");
			String dep = scan.next();
			statement.setString(1, dep);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				System.out.println(resultSet.getInt("e_id") + " " + resultSet.getString("e_name") + " "
						+ resultSet.getInt("salary") + "  " + resultSet.getString("department"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
		}

	}

}
