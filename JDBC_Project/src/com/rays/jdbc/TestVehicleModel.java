package com.rays.jdbc;

public class TestVehicleModel {
	
	public static void main(String[] args) throws Exception {
		//testCreateTable();
		//testAdd();
		//testUpdate();
		testDelete();
	}

	private static void testDelete() throws Exception{
		VehicleModel vm = new VehicleModel();
		VehicleBean vb =  new VehicleBean();
	    vb.setVehicleId(2);
		vm.delete(vb);
	}

	private static void testUpdate() throws Exception {
		VehicleModel vm = new VehicleModel();
		VehicleBean vb = new VehicleBean();
		
		vb.setVehicleId(2);
		vb.setVehicleName("Verna");
		vb.setModel("2024");
		vb.setColor("Grey");
		vb.setPrice(1500000.00);
		
		vm.update(vb);
		
	}

	private static void testAdd() throws Exception {
		VehicleModel vm = new VehicleModel();
		VehicleBean vb = new VehicleBean();
		
	  
		vb.setVehicleName("Fortuner");
		vb.setModel("2025");
		vb.setColor("Black");
		vb.setPrice(4800000.00);
		
		vm.add(vb);
	}

	private static void testCreateTable() throws Exception {
		VehicleModel vm = new VehicleModel();
		vm.createTable();
		
	}

	

}
