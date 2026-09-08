package com.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestInsertByBankAccount {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
		System.out.println("Connection established successfully: " + conn.getCatalog());
		Statement stmt = conn.createStatement();
		int i = stmt.executeUpdate(
				"insert into bankaccount values(1001,'Krishna','Saving',156000.00,'Inodre'),(1002,'Mahesh','Current',1200.00,'Bhopal'),(1003,'Ram','Saving',20000.00,'Dewas'),(1004,'Rahul','Current',2300.00,'Palasia'),(1005,'Amit','Saving',250000.00,'Rau')");
		System.out.println("Inserted into bankaccount " + i + " rows affected");

	}
}
