package in.com.jdbc.preparedstatement;

import java.util.Iterator;
import java.util.List;

public class TestVendorModel {

	public static void main(String[] args) {

		// testAdd();
		// testUpdate();
		// testDelete();
		// testSearch();
		testFindByPK();
	}

	private static void testFindByPK() {
		VendorModel vm = new VendorModel();
		VendorBean bean = new VendorBean();

		bean = vm.FindByPK(1);
        if(bean != null) {
        	System.out.println(bean.getVendorId());
			System.out.println(bean.getVendorName());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getAddress());
			System.out.println(bean.getServiceType());
        }else {
        	System.out.println("User not found");
        }
	}

	private static void testSearch() {
		VendorModel vm = new VendorModel();
		VendorBean bean = new VendorBean();

		List list = vm.search(bean, 1, 5);

		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean = (VendorBean) it.next();
			System.out.println(bean.getVendorId());
			System.out.println(bean.getVendorName());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getAddress());
			System.out.println(bean.getServiceType());
			System.out.println("------------------------------------------");
		}

	}

	private static void testDelete() {
		VendorModel vm = new VendorModel();
		VendorBean bean = new VendorBean();

		vm.delete(5);
	}

	private static void testUpdate() {
		VendorModel vm = new VendorModel();
		VendorBean bean = new VendorBean();

		bean.setVendorName("Rahul");
		bean.setMobileNo("7067201602");
		bean.setAddress("Indore");
		bean.setServiceType("Stationary items");
		bean.setVendorId(6);

		vm.update(bean);

	}

	private static void testAdd() {
		VendorModel vm = new VendorModel();
		VendorBean bean = new VendorBean();

		bean.setVendorName("Raman");
		bean.setMobileNo("9770756457");
		bean.setAddress("Ujjain");
		bean.setServiceType("Electronic appliences");

		vm.add(bean);

	}
}
