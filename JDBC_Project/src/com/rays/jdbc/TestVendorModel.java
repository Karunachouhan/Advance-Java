package com.rays.jdbc;

public class TestVendorModel {
	public static void main(String[] args) throws Exception {

		// testCreate();
		// testadd();
		// testUpdate();
		// testDelete();
		testSearch();
	}

	private static void testSearch() throws Exception {
		VendorModel vm = new VendorModel();
		VendorBean vb = new VendorBean();

		vm.select(vb);

	}

	private static void testDelete() throws Exception {
		VendorModel vm = new VendorModel();
		VendorBean vb = new VendorBean();

		vb.setVendorId(3);
		vm.delete(vb);

	}

	private static void testUpdate() throws Exception {
		VendorModel vm = new VendorModel();
		VendorBean vb = new VendorBean();

		vb.setVendorId(2);
		vb.setVendorName("Gupta Traders");
		vb.setAddress("Mumbai");
		vb.setMobileNo("7067546798");
		vb.setServiceType("Hardware");

		vm.update(vb);

	}

	private static void testadd() throws Exception {
		VendorModel vm = new VendorModel();
		VendorBean vb = new VendorBean();

		vb.setVendorName("Krishna Suppliers");
		vb.setMobileNo("9756987634");
		vb.setAddress("Indore");
		vb.setServiceType("Stationery");

		vm.add(vb);
	}

	private static void testCreate() throws Exception {
		VendorModel vm = new VendorModel();
		vm.createTable();

	}
}
