package com.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestInsert {
	public static void main(String[] args) throws SQLException, ClassNotFoundException{

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest","root","root");
		System.out.println("Connection established successfully: " + conn.getCatalog());
		
		Statement stmt = conn.createStatement();
		int i = stmt.executeUpdate("insert into marksheet values(17,117,'Saharsh',67,75,46,188,62,'pass')");
		System.out.println("insert into marksheet "+ i +" row affected");
	}
}
