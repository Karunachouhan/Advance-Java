package in.com.jdbc.preparedstatement;

import java.text.SimpleDateFormat;

public class TestPaymentModel {

	public static void main(String[] args) throws Exception {
		// testCreate();
		// testAdd();
		testUpdate();
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

		bean.setPaymentId(105);
		bean.setAmount(1000.00);
		bean.setPaymentDate(sdf.parse("2026-05-26"));
		bean.setPaymentMethod("Banking");
		bean.setTransactionId("TXN20267655");
		pm.add(bean);

	}

	private static void testCreate() throws Exception {
		PaymentModel pm = new PaymentModel();
		pm.createTable();

	}

}
