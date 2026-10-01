package in.com.jdbc.preparedstatement;

import java.util.Iterator;
import java.util.List;

public class TestBookModel {
	public static void main(String[] args) {

		// testAdd();
		// testUpdate();
		// testDelete();
		// testSearch();
		testFindByPk();
	}

	private static void testFindByPk() {
		BookModel model = new BookModel();
		BookBean bean = new BookBean();

		bean = model.findBYPK(1);
		if (bean != null) {
			System.out.println(bean.getBookId());
			System.out.println(bean.getTitle());
			System.out.println(bean.getAuthor());
			System.out.println(bean.getPrice());
			System.out.println(bean.getPublicationYear());
		} else {
			System.out.println("User not found");
		}

	}

	private static void testSearch() {
		BookModel model = new BookModel();
		BookBean bean = new BookBean();

		List list = model.serach(bean, 1, 5);
		Iterator i = list.iterator();
		while (i.hasNext()) {
			bean = (BookBean) i.next();
			System.out.println(bean.getBookId());
			System.out.println(bean.getTitle());
			System.out.println(bean.getAuthor());
			System.out.println(bean.getPrice());
			System.out.println(bean.getPublicationYear());
		}

	}

	private static void testDelete() {
		BookModel model = new BookModel();
		model.delete(2);

	}

	private static void testUpdate() {
		BookModel model = new BookModel();
		BookBean bean = new BookBean();
		bean.setBookId(2);
		bean.setTitle("Python basics");
		bean.setAuthor("Mark Lutz");
		bean.setPrice(480.0);
		bean.setPublicationYear(2022);

		model.update(bean);

	}

	private static void testAdd() {
		BookModel model = new BookModel();
		BookBean bean = new BookBean();
		// bean.setBookId(1);
		bean.setTitle("Java programming");
		bean.setAuthor("James Gosling");
		bean.setPrice(550.0);
		bean.setPublicationYear(2020);

		model.add(bean);

	}
}
