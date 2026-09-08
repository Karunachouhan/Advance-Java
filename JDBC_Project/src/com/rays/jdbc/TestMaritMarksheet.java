package com.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestMaritMarksheet {

	public static void main(String[] args) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
		System.out.println("Connection established successfully: " + conn.getCatalog());
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(
				"select *,(phy+chm+maths)as total from marksheet where phy>=33 and chm>=33 and maths>=33 order by total desc limit 0,3");
		while (rs.next()) {
			System.out.println("Top three students records:- ");
			System.out.println("Id : " + rs.getInt("id"));
			System.out.println("RollNo : " + rs.getInt("rollNo"));
			System.out.println("Name : " + rs.getString("name"));
			System.out.println("Physics marks : " + rs.getInt("phy"));
			System.out.println("Chemistry : " + rs.getInt("chm"));
			System.out.println("Maths : " + rs.getInt("maths"));
			int total = rs.getInt("phy") + rs.getInt("chm") + rs.getInt("maths");
			System.out.println("total : " + total);
			float percentage = (total / 3);
			System.out.println("percentage : " + percentage);
			System.out.println("------------------------------");
		}

	}

}
