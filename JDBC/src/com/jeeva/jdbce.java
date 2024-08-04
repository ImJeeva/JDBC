package com.jeeva;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class jdbce {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/demodatabases";
		String username = "root";
		String password = "Imjeeva@888";
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String query = "Select * from `employee`";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("work");
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

		} catch (Exception e) {
			e.printStackTrace();
		}
		process(resultSet);
		injection(resultSet, statement, connection);

	

	}
	public static void process(ResultSet resultSet) {
		try {
			while (resultSet.next()) {
				System.out.println(resultSet.getInt("e_id") + " " + resultSet.getString("e_name") + " "
						+ resultSet.getInt("salary"));

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void injection(ResultSet resultSet, Statement statement, Connection connection) {

		try {
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
