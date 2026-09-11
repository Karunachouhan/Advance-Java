package in.com.jdbc.preparedstatement;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

public class TestUserModel {
	public static void main(String[] args) throws Exception {

		// testAdd();
		// testUpdate();
		// testDelete();
		// testFindByPK();
		// testFindByLoginId();
		// testFindByAuthenticate();
		testSearch();

	}

	private static void testSearch() {
		UserModel model = new UserModel();
		UserBean bean = new UserBean();

		List list = model.search(bean, 1, 5);

		Iterator t = list.iterator();
		while (t.hasNext()) {
			bean = (UserBean) t.next();
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getPassword());
			System.out.println(bean.getLoginId());
			System.out.println(bean.getDob());
			System.out.println("-----------------");
		}
	}

	private static void testFindByAuthenticate() throws Exception {

		UserModel model = new UserModel();
		UserBean bean = new UserBean();

		bean = model.authenticate("ramsharma@gmail.com", "@ramsharma");
		System.out.println(bean.getId());
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getPassword());
		System.out.println(bean.getLoginId());
		System.out.println(bean.getDob());

	}

	private static void testFindByLoginId() throws Exception {
		UserModel model = new UserModel();
		UserBean bean = new UserBean();

		bean = model.findByLoginId("ramsharma@gmail.com");
		System.out.println(bean.getId());
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getPassword());
		System.out.println(bean.getLoginId());
		System.out.println(bean.getDob());

	}

	private static void testFindByPK() {
		UserModel um = new UserModel();
		UserBean bean = new UserBean();

		bean = um.findByPK(1);

		if (bean != null) {
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getPassword());
			System.out.println(bean.getLoginId());
			System.out.println(bean.getDob());
		} else {
			System.out.println("user not found");
		}

	}

	private static void testDelete() throws Exception {
		UserModel um = new UserModel();
		um.delete(2);

	}

	private static void testUpdate() throws Exception {
		UserModel um = new UserModel();
		UserBean ub = new UserBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		ub.setId(2);
		ub.setFirstName("Shyam");
		ub.setLastName("Verma");
		ub.setLoginId("shyamverma@gmail.com");
		ub.setPassword("@shyamverma");
		ub.setDob(sdf.parse("2000-10-16"));
		um.update(ub);

	}

	private static void testAdd() throws Exception {
		UserModel us = new UserModel();
		UserBean ub = new UserBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		ub.setFirstName("Ram");
		ub.setLastName("Sharma");
		ub.setLoginId("ramsharma@gmail.com");
		ub.setPassword("@ramsharma");
		ub.setDob(sdf.parse("2005-05-17"));

		us.add(ub);

	}

}
