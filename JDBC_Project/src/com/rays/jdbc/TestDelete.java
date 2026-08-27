package com.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDelete {
	
	public static void main(String[] args) throws Exception {
		
		Class .forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
		System.out.println("Connection established successfully : "+ conn.getCatalog());
		Statement stmt = conn.createStatement();
		int i = stmt.executeUpdate("Delete from marksheet where id = 10");
		System.out.println("Deleted "+ i + " row affected");
		
	}

}
