package com.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSelectByBankAccount {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
		System.out.println("Connection established successfully: " + conn.getCatalog());
		System.out.println();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("Select * from bankaccount");
		while (rs.next()) {
			System.out.println("Account number: " + rs.getLong("accountNumber"));
			System.out.println("Account holder name: " + rs.getString("accountHolderName"));
			System.out.println("Account type: " + rs.getString("accountType"));
			System.out.println("Balance: " + rs.getDouble("balance"));
			System.out.println("Branch name: " + rs.getString("branchName"));
			System.out.println("------------------------------------------------------------------");
		}
	}
}
