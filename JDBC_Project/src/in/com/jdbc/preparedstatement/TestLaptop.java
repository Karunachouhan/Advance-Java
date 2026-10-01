package in.com.jdbc.preparedstatement;

import java.util.Iterator;
import java.util.List;

public class TestLaptop {

	public static LaptopModel model = new LaptopModel();

	public static void main(String[] args) {

		// testAdd();
		// testUpdate();
		// testDelete();
		// testSearch();
		testFindByPk();
	}

	private static void testFindByPk() {
		LaptopBean bean = new LaptopBean();
		model.findByPk(1);

	}

	private static void testSearch() {
		LaptopBean bean = new LaptopBean();
		bean.setLaptopId(1);
		List list = model.search(bean, 1, 5);

		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean = (LaptopBean) it.next();
			System.out.println(bean.getLaptopId());
			System.out.println(bean.getBrand());
			System.out.println(bean.getProcessor());
			System.out.println(bean.getRam());
			System.out.println(bean.getStorage());
			System.out.println("---------------------------------------------------");
		}

	}

	private static void testDelete() {
		model.delete(4);

	}

	private static void testUpdate() {
		LaptopBean bean = new LaptopBean();
		bean.setBrand("Apple");
		bean.setProcessor("Apple M2");
		bean.setRam(8);
		bean.setStorage(256);
		bean.setLaptopId(3);

		model.update(bean);

	}

	private static void testAdd() {
		LaptopBean bean = new LaptopBean();
		// bean.setLaptopId(1);
		bean.setBrand("MSI");
		bean.setProcessor("Intel core i7");
		bean.setRam(16);
		bean.setStorage(1024);

		model.add(bean);
	}
}
