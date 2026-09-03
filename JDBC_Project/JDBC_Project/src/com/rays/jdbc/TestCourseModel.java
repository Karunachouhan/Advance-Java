package com.rays.jdbc;

public class TestCourseModel {
	public static void main(String[] args) throws Exception {
		// add();
		// update();
		delete();
	}

	private static void delete() throws Exception {
		CourseModel cm = new CourseModel();
		cm.delete(102);
	}

	private static void update() throws Exception {
		CourseModel cm = new CourseModel();
		cm.update("Mtech", "2 years", 101);

	}

	private static void add() throws Exception {
		CourseModel cm = new CourseModel();
		cm.add(105, "MBA", "2 years", 15000.00, "Kanak");
	}
}
