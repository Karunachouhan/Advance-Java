package in.com.jdbc.preparedstatement;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.rays.util.JDBCDataSource;

public class BookModel {

	public long nextPK() {
		Connection conn = null;
		long PK = 0;
		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement("select max(book_id) from st_book");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PK = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return PK + 1;
	}

	public void add(BookBean bean) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("insert into st_book values(?,?,?,?,?)");
			ps.setLong(1, nextPK());
			ps.setString(2, bean.getTitle());
			ps.setString(3, bean.getAuthor());
			ps.setDouble(4, bean.getPrice());
			ps.setInt(5, bean.getPublicationYear());
			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Data inserted successfully " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public void update(BookBean bean) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn
					.prepareStatement("update st_book set title=?,author=?,price=?,publication_year=? where book_id=?");
			ps.setString(1, bean.getTitle());
			ps.setString(2, bean.getAuthor());
			ps.setDouble(3, bean.getPrice());
			ps.setInt(4, bean.getPublicationYear());
			ps.setLong(5, bean.getBookId());
			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Data updated succesfully " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(int bookId) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("delete from st_book where book_id = ?");
			ps.setInt(1, bookId);
			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("data deleted successfully " + i + " rows affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public List serach(BookBean bean, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer("select * from st_book where 1=1");
		List list = new ArrayList();
		Connection conn = null;
		try {
			if (bean != null) {
				if (bean.getBookId() > 0) {
					sql.append(" and book_id = " + bean.getBookId());
				}
				if (bean.getTitle() != null && bean.getTitle().length() > 0) {
					sql.append(" and title = '" + bean.getTitle() + "'");
				}
				if (bean.getAuthor() != null && bean.getAuthor().length() > 0) {
					sql.append(" and author = '" + bean.getAuthor() + "'");
				}
				if (bean.getPrice() > 0.0) {
					sql.append(" and price = " + bean.getPrice());
				}
				if (bean.getPublicationYear() > 0) {
					sql.append(" and publication_year = " + bean.getPublicationYear());
				}
			}
			if (pageSize > 0) {
				int index = (pageNo - 1) * pageSize;
				sql.append(" limit " + index + " ," + pageSize);
			}
			System.out.println("sql ----> " + sql.toString());
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new BookBean();
				bean.setBookId(rs.getLong("book_Id"));
				bean.setTitle(rs.getString("title"));
				bean.setAuthor(rs.getString("author"));
				bean.setPrice(rs.getDouble("price"));
				bean.setPublicationYear(rs.getInt("publication_year"));
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

	public BookBean findBYPK(long bookId) {
		Connection conn = null;
		BookBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement("select * from st_book where book_id = ?");
			ps.setLong(1, bookId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new BookBean();
				bean.setBookId(rs.getLong("book_id"));
				bean.setTitle(rs.getString("title"));
				bean.setAuthor(rs.getString("author"));
				bean.setPrice(rs.getDouble("price"));
				bean.setPublicationYear(rs.getInt("publication_year"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return null;

	}
}
