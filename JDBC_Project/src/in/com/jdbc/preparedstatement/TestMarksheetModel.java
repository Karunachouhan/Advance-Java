package in.com.jdbc.preparedstatement;

import java.util.Iterator;
import java.util.List;

public class TestMarksheetModel {

	public static void main(String[] args) {

		// testAdd();
		// testUpdate();
		// testDelete();
		// testSearch();
		// testFindByRollNo();
		testFindByPK();
	}

	private static void testFindByPK() {
		MarksheetModel m = new MarksheetModel();
		MarksheetBean bean = new MarksheetBean();

		bean = m.findByPK(11);
		System.out.println("Id = " + bean.getId());
		System.out.println("RollNo = " + bean.getRollNo());
		System.out.println("Name = " + bean.getName());
		System.out.println("Physics = " + bean.getPhy());
		System.out.println("Chmestry = " + bean.getChm());
		System.out.println("Maths = " + bean.getMaths());

	}

	private static void testFindByRollNo() {
		MarksheetModel m = new MarksheetModel();
		MarksheetBean bean = new MarksheetBean();

		bean = m.findByRollNo(109);
		System.out.println(bean.getId());
		System.out.println(bean.getRollNo());
		System.out.println(bean.getName());
		System.out.println(bean.getPhy());
		System.out.println(bean.getChm());
		System.out.println(bean.getMaths());

	}

	private static void testSearch() {
		MarksheetModel m = new MarksheetModel();
		MarksheetBean bean = new MarksheetBean();
		bean.setName("Rohit");
		List list = m.search(bean, 1, 5);

		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean = (MarksheetBean) it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getRollNo());
			System.out.println(bean.getName());
			System.out.println(bean.getPhy());
			System.out.println(bean.getChm());
			System.out.println(bean.getMaths());
		}

	}

	private static void testDelete() {
		MarksheetModel m = new MarksheetModel();
		// MarksheetBean bean = new MarksheetBean();

		m.delete(12);
	}

	private static void testUpdate() {
		MarksheetModel m = new MarksheetModel();
		MarksheetBean bean = new MarksheetBean();

		bean.setId(18);
		bean.setRollNo(118);
		bean.setName("Rahul");
		bean.setPhy(56);
		bean.setChm(67);
		bean.setMaths(89);

		m.update(bean);

	}

	private static void testAdd() {
		MarksheetModel m = new MarksheetModel();
		MarksheetBean bean = new MarksheetBean();

		bean.setRollNo(118);
		bean.setName("Rahul");
		bean.setPhy(56);
		bean.setChm(78);
		bean.setMaths(67);

		m.add(bean);

	}

}
