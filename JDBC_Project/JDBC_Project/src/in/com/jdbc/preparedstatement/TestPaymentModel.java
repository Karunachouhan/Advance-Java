package in.com.jdbc.preparedstatement;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

public class TestPaymentModel {

	public static void main(String[] args) throws Exception {
		// testCreate();
		// testAdd();
		// testUpdate();
		// testdelete();
		// testFindByPK();
		//testFindByTransactionId();
		testSearch();
	}

	private static void testSearch() {
		PaymentModel pm = new PaymentModel();
		PaymentBean bean = new PaymentBean();
		bean.setPaymentId(4);
		List list = pm.search(bean, 1, 5);
		//bean.setPaymentId(4);
		Iterator t = list.iterator();
		while (t.hasNext()) {
			bean = (PaymentBean) t.next();
			System.out.println(bean.getPaymentId());
			System.out.println(bean.getAmount());
			System.out.println(bean.getPaymentDate());
			System.out.println(bean.getPaymentMethod());
			System.out.println(bean.getTransactionId());
			System.out.println("-----------------");
		}
		
	}

	private static void testFindByTransactionId() {
		PaymentModel pm = new PaymentModel();
		PaymentBean bean = new PaymentBean();

		bean = pm.findByTransactionId("TXN2026765445");
		System.out.println(bean.getPaymentId());
		System.out.println(bean.getAmount());
		System.out.println(bean.getPaymentDate());
		System.out.println(bean.getPaymentMethod());
		System.out.println(bean.getTransactionId());
	}

	private static void testFindByPK() {
		PaymentModel pm = new PaymentModel();
		PaymentBean bean = new PaymentBean();

		bean = pm.findByPK(2);

		if (bean != null) {
			System.out.println(bean.getPaymentId());
			System.out.println(bean.getAmount());
			System.out.println(bean.getPaymentDate());
			System.out.println(bean.getPaymentMethod());
			System.out.println(bean.getTransactionId());
		} else {
			System.out.println("User not found");
		}

	}

	private static void testdelete() {
		PaymentModel pm = new PaymentModel();

		pm.delete(6);

	}

	private static void testUpdate() throws Exception {
		PaymentModel pm = new PaymentModel();
		PaymentBean bean = new PaymentBean();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setPaymentId(103);
		bean.setAmount(123000.0);
		bean.setPaymentDate(sdf.parse("2025-12-30"));
		bean.setPaymentMethod("UPI");
		bean.setTransactionId("TXN20264567");
		pm.update(bean);
	}

	private static void testAdd() throws Exception {
		PaymentModel pm = new PaymentModel();
		PaymentBean bean = new PaymentBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setAmount(134000.00);
		bean.setPaymentDate(sdf.parse("2026-07-12"));
		bean.setPaymentMethod("Cash");
		bean.setTransactionId("TXN202676098");
		pm.add(bean);

	}

	private static void testCreate() throws Exception {
		PaymentModel pm = new PaymentModel();
		pm.createTable();

	}

}
