package com.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSelectByCourse {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
		System.out.println("Connection established successfully: " + conn.getCatalog());
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from course");
		while (rs.next()) {
			System.out.println(" Course id: " + rs.getLong("courseId"));
			System.out.println(" Course name: " + rs.getString("courseName"));
			System.out.println(" Course duration: " + rs.getString("duration"));
			System.out.println(" Fees: " + rs.getDouble("fees"));
			System.out.println(" Trainer name: " + rs.getString("trainerName"));
			System.out.println("---------------------------------------------------------------------------------");
		}

	}
}
