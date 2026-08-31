package com.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConditionalSearch {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
		System.out.println("Connection established successfully: " + conn.getCatalog());
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from marksheet where name = 'Rohit'");
		while (rs.next()) {
			System.out.println("Id : " + rs.getInt("id"));
			System.out.println("RollNo : " + rs.getInt("rollNo"));
			System.out.println("Name : " + rs.getString("name"));
			System.out.println("Physics marks : " + rs.getInt("phy"));
			System.out.println("Chemistry : " + rs.getInt("chm"));
			System.out.println("Maths : " + rs.getInt("maths"));
			System.out.println("Total : " + rs.getInt("total"));
			System.out.println("Percentage : " + rs.getFloat("percentage"));
			System.out.println("Result : " + rs.getString("result"));
		}
	}
}
