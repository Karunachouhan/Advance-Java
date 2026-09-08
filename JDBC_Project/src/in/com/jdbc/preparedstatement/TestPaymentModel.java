package in.com.jdbc.preparedstatement;

public class TestPaymentModel {

	public static void main(String[] args) throws Exception {
		testCreate();

	}

	private static void testCreate() throws Exception {
		PaymentModel pm = new PaymentModel();
		pm.createTable();

	}

}
