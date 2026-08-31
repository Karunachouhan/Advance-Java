package com.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestInsertByCourse {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
		System.out.println("Connection established successfully: " + conn.getCatalog());
		Statement stmt = conn.createStatement();
		int i = stmt.executeUpdate(
				"insert into Course values(101,'BCA','3 years',50000.00,'Sachin Tripathi'),(102,'MCA','2 years',150000.00,'Praveen Mahajan'),(103,'BBA','3 years',55000.00,'Nikita Patel'),(104,'BA','4 years',35000.00,'Rachna Nayak')");
		System.out.println("Data inserted " + i + " rows affected");

	}

}
