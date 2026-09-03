package com.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VendorModel {

	public void createTable() throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(
					"create table Vendor(vendorId int Primary Key,vendorName varchar(60),mobileNo varchar(60),address varchar(60),serviceType varchar(60))");
			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Table created successfully: " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public int nextPk() throws Exception {
		Connection conn = null;
		int pk = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");

			PreparedStatement ps = conn.prepareStatement("select max(VendorId) from Vendor");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return pk + 1;
	}

	public void add(VendorBean vb) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("insert into Vendor values(?,?,?,?,?)");
			ps.setInt(1, nextPk());
			ps.setString(2, vb.getVendorName());
			ps.setString(3, vb.getMobileNo());
			ps.setString(4, vb.getAddress());
			ps.setString(5, vb.getServiceType());
			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Data inserted successfully " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void update(VendorBean vb) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(
					"update Vendor set vendorName=?,mobileNo=?,address=?,serviceType=? where vendorId=?");
			ps.setString(1, vb.getVendorName());
			ps.setString(2, vb.getMobileNo());
			ps.setString(3, vb.getAddress());
			ps.setString(4, vb.getServiceType());
			ps.setInt(5, vb.getVendorId());
			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Data updated successfully: " + i + " rows affected");
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void delete(VendorBean vb) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("delete from Vendor where vendorId = ?");
			ps.setInt(1, vb.getVendorId());
			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Record deleted successfully: " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void select(VendorBean vb) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("select * from Vendor");
			ResultSet rs = ps.executeQuery();
			conn.commit();
			while (rs.next()) {
				System.out.println("Vendor ID: " + rs.getInt("vendorId"));
				System.out.println("Vendor Name: " + rs.getString("vendorName"));
				System.out.println("Vendor mobileNo: " + rs.getString("mobileNo"));
				System.out.println("Vendor address: " + rs.getString("address"));
				System.out.println("Service type: " + rs.getString("serviceType"));
				System.out.println("---------------------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
}
