package in.com.jdbc.preparedstatement;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TestBusModel {

	public static BusModel model = new BusModel();

	public static void main(String[] args) {

		// testAdd();
		// testUpdate();
		// testDelete();
		testSearch();

	}

	private static void testSearch() {
		BusBean bean = new BusBean();

		List list = model.search(bean, 1, 4);
		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean = (BusBean) it.next();
			System.out.println(bean.getBusId());
			System.out.println(bean.getBusNumber());
			System.out.println(bean.getRoute());
			System.out.println(bean.getCapacity());
			System.out.println(bean.getDepartureTime());
			System.out.println("-------------------------------------");
		}
	}

	private static void testDelete() {
		model.delete(5);

	}

	private static void testUpdate() {
		BusBean bean = new BusBean();
		bean.setBusId(2);
		bean.setBusNumber("BUS102");
		bean.setRoute("Bhoapl - Dewas");
		bean.setCapacity(30);
		bean.setDepartureTime(new Timestamp(new Date().getTime()));

		model.update(bean);
	}

	private static void testAdd() {
		BusBean bean = new BusBean();
		// bean.setBusId(2);
		bean.setBusNumber("BUS105");
		bean.setRoute("Indore - Dewas");
		bean.setCapacity(40);
		bean.setDepartureTime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}
}
