package com.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSelectForDoctor {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
		System.out.println("Connection established successfully : " + conn.getCatalog());
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from Doctor ");
		while (rs.next()) {
			System.out.println();
			System.out.println("Doctor id : " + rs.getLong("doctorId"));
			System.out.println("Doctor name is : " + rs.getString("doctorName"));
			System.out.println("Doctor specialization : " + rs.getString("specialization"));
			System.out.println("Experience : " + rs.getInt("experience"));
			System.out.println("Contact number : " + rs.getString("contactNo"));
			System.out.println("--------------------------------------------------------------");
		}

	}
}
