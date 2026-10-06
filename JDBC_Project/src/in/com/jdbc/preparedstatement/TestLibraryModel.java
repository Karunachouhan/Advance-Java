package in.com.jdbc.preparedstatement;

import java.util.Iterator;
import java.util.List;

public class TestLibraryModel {
	public static LibraryModel model = new LibraryModel();

	public static void main(String[] args) {

		// testAdd();
		// testUpdate();
		// testDelete();
		//testSearch();
		testFindByPk();
		
	}

	private static void testFindByPk() {
	LibraryBean bean = new LibraryBean();
	bean = model.findByPk(3);
	
	System.out.println(bean.getBookId());
	System.out.println(bean.getTitle());
	System.out.println(bean.getAuthor());
	System.out.println(bean.getPrice());
	System.out.println(bean.isAvailability());
		
	}

	private static void testSearch() {
		LibraryBean bean = new LibraryBean();
		List list = model.search(bean, 1, 4);
		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean = (LibraryBean) it.next();
			System.out.println(bean.getBookId());
			System.out.println(bean.getTitle());
			System.out.println(bean.getAuthor());
			System.out.println(bean.getPrice());
			System.out.println(bean.isAvailability());
			System.out.println("-------------------------------------------");
		}

	}

	private static void testDelete() {
		model.delete(5);

	}

	private static void testUpdate() {
		LibraryBean bean = new LibraryBean();
		bean.setTitle("web development");
		bean.setAuthor("chris minnick");
		bean.setPrice(580.00);
		bean.setAvailability(false);
		bean.setBookId(5);
		model.update(bean);
	}

	private static void testAdd() {
		LibraryBean bean = new LibraryBean();
		// bean.setBookId(1);
		bean.setTitle("HTML and CSS");
		bean.setAuthor("jon duckett");
		bean.setPrice(500.00);
		bean.setAvailability(true);
		model.add(bean);
	}
}
