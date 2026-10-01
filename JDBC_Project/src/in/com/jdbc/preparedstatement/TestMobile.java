package in.com.jdbc.preparedstatement;

import java.util.Iterator;
import java.util.List;

public class TestMobile {

	public static void main(String[] args) {

		// testAdd();
		// testUpdate();
		// testDelete();
		//testsearch();

	}

	private static void testsearch() {
		MobileModel model = new MobileModel();
		MobileBean bean = new MobileBean();

		List list = model.search(bean, 1, 5);
		Iterator it = list.iterator();
		while (it.hasNext()) {
		bean = (MobileBean) it.next();
		System.out.println("Id - " + bean.getMobileId());
		System.out.println("Brand - " + bean.getBrand());
		System.out.println("Model - " + bean.getModel());
		System.out.println("Battery - " + bean.getBattery());
		System.out.println("Operating system - " + bean.getOperatingSystem());
		System.out.println("-------------------------------------------------------");
		}
	}

	private static void testDelete() {
		MobileModel model = new MobileModel();
		model.delete(2);

	}

	private static void testUpdate() {
		MobileModel model = new MobileModel();
		MobileBean bean = new MobileBean();

		bean.setBrand("Oppo");
		bean.setModel("Reno 11");
		bean.setBattery(5000);
		bean.setOperatingSystem("Android");
		bean.setMobileId(3);

		model.update(bean);

	}

	private static void testAdd() {
		MobileModel model = new MobileModel();
		MobileBean bean = new MobileBean();

		// bean.setMobileId(1);
		bean.setBrand("Vivo");
		bean.setModel("V30");
		bean.setBattery(5000);
		bean.setOperatingSystem("Android");

		model.add(bean);
	}

}
