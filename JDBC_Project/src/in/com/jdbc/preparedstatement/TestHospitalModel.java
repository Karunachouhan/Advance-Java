package in.com.jdbc.preparedstatement;

import java.util.Iterator;
import java.util.List;

public class TestHospitalModel {

	public static HospitalModel model = new HospitalModel();

	public static void main(String[] args) {

		// testAdd();
		// testUpdate();
		// testDelete();
		// testSearch();
		//testFindByPk();
	}

	private static void testFindByPk() {
		HospitalBean bean = new HospitalBean();

		bean = model.findByPk(1);
		if (bean != null) {
			System.out.println(bean.getPatientId());
			System.out.println(bean.getName());
			System.out.println(bean.getAge());
			System.out.println(bean.getBloodGroup());
			System.out.println(bean.getDisease());
		} else {
			System.out.println("User not found");
		}

	}

	private static void testSearch() {
		HospitalBean bean = new HospitalBean();
		bean.setDisease("Fever");
		List list = model.search(bean, 1, 5);
		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean = (HospitalBean) it.next();
			System.out.println(bean.getPatientId());
			System.out.println(bean.getName());
			System.out.println(bean.getAge());
			System.out.println(bean.getBloodGroup());
			System.out.println(bean.getDisease());
			System.out.println("-----------------------------------------------");
		}

	}

	private static void testDelete() {
		model.delete(2);
	}

	private static void testUpdate() {
		HospitalBean bean = new HospitalBean();

		bean.setName("Niya Verma");
		bean.setAge(39);
		bean.setBloodGroup("o negative");
		bean.setDisease("Fever");
		bean.setPatientId(1);

		model.update(bean);

	}

	private static void testAdd() {
		HospitalBean bean = new HospitalBean();

		// bean.setPatientId(1);
		bean.setName("Megha Sharma");
		bean.setAge(21);
		bean.setBloodGroup("o+");
		bean.setDisease("Cancer");

		model.add(bean);
	}

}
