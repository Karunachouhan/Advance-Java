package in.com.jdbc.preparedstatement;

import java.util.Iterator;
import java.util.List;

public class TestRestaurantModel {
	public static RestaurantModel model = new RestaurantModel();

	public static void main(String[] args) {
		// testAdd();
		// testUpdate();
		// testDelete();
		// testSearch();
		//testFindByPk();
	}

	private static void testFindByPk() {
		RestaurantBean bean = new RestaurantBean();
		bean = model.findByPk(2);
		System.out.println(bean.getItemId());
		System.out.println(bean.getItemName());
		System.out.println(bean.getCategory());
		System.out.println(bean.getPrice());
		System.out.println(bean.isAvailable());
		System.out.println("---------------------------------------------------");

	}

	private static void testSearch() {
		RestaurantBean bean = new RestaurantBean();
		List list = model.search(bean, 1, 5);
		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean = (RestaurantBean) it.next();
			System.out.println(bean.getItemId());
			System.out.println(bean.getItemName());
			System.out.println(bean.getCategory());
			System.out.println(bean.getPrice());
			System.out.println(bean.isAvailable());
			System.out.println("---------------------------------------------------");
		}
	}

	private static void testDelete() {
		model.delete(4);
	}

	private static void testUpdate() {
		RestaurantBean bean = new RestaurantBean();
		bean.setItemName("French fries");
		bean.setCategory("Snacks");
		bean.setPrice(100.00);
		bean.setAvailable(false);
		bean.setItemId(2);

		model.update(bean);
	}

	private static void testAdd() {
		RestaurantBean bean = new RestaurantBean();
		// bean.setItemId(1);
		bean.setItemName("Butter naan");
		bean.setCategory("bread");
		bean.setPrice(50.00);
		bean.setAvailable(true);

		model.add(bean);
	}
}
