package in.com.jdbc.preparedstatement;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestOrderModel {

	public static void main(String[] args) throws Exception {

		// testAdd();
		// testUpdate();
		testDelete();
	}

	private static void testDelete() {
		OrderModel o = new OrderModel();
		OrderBean bean = new OrderBean();

		// bean.setOrderId(3);

		o.delete(3);

	}

	private static void testUpdate() throws ParseException {
		OrderModel o = new OrderModel();
		OrderBean bean = new OrderBean();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setOrderDate(sdf.parse("2025-08-15"));
		bean.setAmount(21000);
		bean.setStatus("complete");
		bean.setCustomerId("CID502");
		bean.setOrderId(2);
		o.update(bean);

	}

	private static void testAdd() throws ParseException {
		OrderModel o = new OrderModel();
		OrderBean bean = new OrderBean();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// bean.setOrderId(1);
		bean.setOrderDate(sdf.parse("2026-12-17"));
		bean.setAmount(20000.00);
		bean.setStatus("completed");
		bean.setCustomerId("CID505");
		o.add(bean);

	}
}
