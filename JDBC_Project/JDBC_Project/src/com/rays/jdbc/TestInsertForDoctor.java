package com.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TestInsertForDoctor {
	
	public static void main(String[] args) throws Exception
	{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
		System.out.println("Connection established successfully: "+conn.getCatalog());
		Statement stmt = conn.createStatement();
		int i = stmt.executeUpdate("insert into Doctor values(101,'Dr.Aman','Cardiologist',5,'7685643677'),(102,'Dr.Sharma','Neurologiest',4,'2356267372'),(103,'Dr.Karan','Orthopedics',3,'7843567649'),(104,'Dr.Mishra','Pediatrics',8,'8975645667'),(105,'Dr.Aman','Dermatologist',7,'6578654676'),"
				+ "(106,'Dr.Mishti','Psychiatriest',5,'8767546788')");
		System.out.println("Data inserted "+ i + " row affected");
		
	}

}
