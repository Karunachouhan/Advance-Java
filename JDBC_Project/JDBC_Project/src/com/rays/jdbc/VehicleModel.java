package com.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleModel {
	// Create table

	public void createTable() throws Exception {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(
					"create table Vehicle(vehicleId int Primary key,vehicleName varchar(60),model varchar(60),color varchar(50),price double)");
			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Table created successfully " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
     
	 public int nextPK() throws Exception {
		 Connection conn = null;
		 int pk = 0;
		 
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
			 
			 PreparedStatement ps = conn.prepareStatement("select max(VehicleId) from vehicle");
			 ResultSet rs = ps.executeQuery();
			 
			 while(rs.next()) {
				 pk = rs.getInt(1);
			 }
		 }catch(Exception e) {
				 e.printStackTrace();
			 }finally {
				 conn.close();
			 }
		 return pk+1;
	 }


	// insert into table
	public void add(VehicleBean vb) throws Exception {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("insert into vehicle values(?,?,?,?,?)");
			ps.setInt(1,nextPK());
			ps.setString(2, vb.getVehicleName());
			ps.setString(3, vb.getModel());
			ps.setString(4, vb.getColor());
			ps.setDouble(5, vb.getPrice());
			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Inserted successfully: " + i);
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	
	public void update(VehicleBean vb)throws Exception{
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement("update vehicle set vehicleName = ? ,model = ?,color = ?,price = ? where vehicleId = ?");
			ps.setString(1, vb.getVehicleName());
			ps.setString(2, vb.getModel());
			ps.setString(3, vb.getColor());
			ps.setDouble(4, vb.getPrice());
			ps.setInt(5, vb.getVehicleId());
			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Data updated successfully: " + i);
		}catch(SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}finally {
			conn.close();
		}
	}
	
	public void delete(VehicleBean vb) throws Exception{
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement("delete from vehicle where vehicleId = ?");
			ps.setInt(1, vb.getVehicleId());
			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Deleted data successfully: " + i);
		}catch(SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}finally {
			conn.close();
		}
	}
	
}
