package com.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestCreateByDoctor {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
		System.out.println("Connection established successfully "+conn.getCatalog());
		Statement stmt = conn.createStatement();
		int i = stmt.executeUpdate("create table Doctor(doctorId long,doctorName VARCHAR(50),specialization VARCHAR(50),experience int,contactNo VARCHAR(50))");
		 System.out.println("Table created "+ i +"row affected" );
		
	}

}
