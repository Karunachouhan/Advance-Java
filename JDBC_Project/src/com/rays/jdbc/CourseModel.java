package com.rays.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourseModel {

	public void add(long courseId, String courseName, String duration, double fees, String trainerName)
			throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("insert into Course values(?,?,?,?,?)");
			ps.setLong(1, courseId);
			ps.setString(2, courseName);
			ps.setString(3, duration);
			ps.setDouble(4, fees);
			ps.setString(5, trainerName);

			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Record inserted successfully: " + i);
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void update(String courseName, String duration, long courseId) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("update Course set courseName=?,duration=? where courseId =?");
			ps.setString(1, courseName);
			ps.setString(2, duration);
			ps.setLong(3, courseId);

			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Record updated: " + i);
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void delete(long courseId) throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("delete from Course where courseId = ?");
			ps.setLong(1, courseId);

			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Recorded deleted: " + i);
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
}