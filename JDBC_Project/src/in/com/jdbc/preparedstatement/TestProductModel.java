package in.com.jdbc.preparedstatement;

import java.util.Iterator;
import java.util.List;

public class TestProductModel {
	public static ProductModel model = new ProductModel();

	public static void main(String[] args) {

		// testAdd();
		// testUpdate();
		// testDelete();
		// testSearch();
		// testFindByPk();
		testFindByUniqueColumn();

	}

	private static void testFindByUniqueColumn() {
		ProductBean bean = model.findByUniqueColumn("laptop");
		System.out.println(bean.getProductId());
		System.out.println(bean.getName());
		System.out.println(bean.getBrand());
		System.out.println(bean.getPrice());
		System.out.println(bean.getWarranty());
	}

	private static void testFindByPk() {
		ProductBean bean = new ProductBean();
		bean = model.findByPk(2);
		System.out.println(bean.getProductId());
		System.out.println(bean.getName());
		System.out.println(bean.getBrand());
		System.out.println(bean.getPrice());
		System.out.println(bean.getWarranty());

	}

	private static void testSearch() {
		ProductBean bean = new ProductBean();
		List<ProductBean> list = model.search(bean, 1, 5);
		Iterator<ProductBean> it = list.iterator();

		while (it.hasNext()) {
			bean = (ProductBean) it.next();
			System.out.println(bean.getProductId());
			System.out.println(bean.getName());
			System.out.println(bean.getBrand());
			System.out.println(bean.getPrice());
			System.out.println(bean.getWarranty());
			System.out.println("-----------------------------------------------");
		}

	}

	private static void testDelete() {
		model.delete(4);

	}

	private static void testUpdate() {
		ProductBean bean = new ProductBean();

		bean.setName("Printer");
		bean.setBrand("canon");
		bean.setPrice(12000.00);
		bean.setWarranty("1 year");
		bean.setProductId(4);

		model.update(bean);

	}

	private static void testAdd() {
		ProductBean bean = new ProductBean();
		// bean.setProductId(1);
		bean.setName("mouse");
		bean.setBrand("logitech");
		bean.setPrice(800.00);
		bean.setWarranty("6 months");
		model.add(bean);
	}
}
