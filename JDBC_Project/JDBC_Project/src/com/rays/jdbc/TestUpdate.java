package com.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUpdate {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
		System.out.println("Connection established successfully: " + conn.getCatalog());

		Statement stmt = conn.createStatement();
		int i = stmt.executeUpdate("update marksheet set name = 'Kanishka' where id = 17");
		System.out.println("update marksheet " + i + " row affected");

	}
}
