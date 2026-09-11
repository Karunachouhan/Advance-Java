package in.com.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rays.util.JDBCDataSource;

public class UserModel {

	public int nextPk() throws Exception {
		Connection conn = null;
		int PK = 0;
		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement("select max(id) from user");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PK = rs.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return PK + 1;
	}

	public void add(UserBean ub) throws Exception {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("insert into user values(?,?,?,?,?,?)");

			ps.setInt(1, nextPk());
			ps.setString(2, ub.getFirstName());
			ps.setString(3, ub.getLastName());
			ps.setString(4, ub.getLoginId());
			ps.setString(5, ub.getPassword());
			ps.setDate(6, new java.sql.Date(ub.getDob().getTime()));

			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Data inserted " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void update(UserBean ub) throws Exception {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(
					"update user set firstName = ?, lastName=?, loginId=?, password=?, dob=? where id=?");
			ps.setString(1, ub.getFirstName());
			ps.setString(2, ub.getLastName());
			ps.setString(3, ub.getLoginId());
			ps.setString(4, ub.getPassword());
			ps.setDate(5, new java.sql.Date(ub.getDob().getTime()));
			ps.setInt(6, ub.getId());
			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Updated successfully: " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(int id) throws Exception {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("delete from user where id = ?");
			ps.setInt(1, id);

			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Data deleted " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public UserBean findByPK(int id) {
		Connection conn = null;
		UserBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("select * from user where id = ?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getInt("id"));
				bean.setFirstName(rs.getString("firstName"));
				bean.setLastName(rs.getString("lastName"));
				bean.setLoginId(rs.getString("loginId"));
				bean.setPassword(rs.getString("password"));
				bean.setDob(rs.getDate("dob"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public UserBean findByLoginId(String loginId) throws Exception {

		Connection conn = null;
		UserBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("select * from user where loginId = ?");
			ps.setString(1, loginId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getInt("id"));
				bean.setFirstName(rs.getString("firstName"));
				bean.setLastName(rs.getString("lastName"));
				bean.setLoginId(rs.getString("loginId"));
				bean.setPassword(rs.getString("password"));
				bean.setDob(rs.getDate("dob"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;

	}

	public UserBean authenticate(String loginId, String password) throws Exception {
		UserBean bean = new UserBean();

		bean = findByLoginId(loginId);

		if (bean != null && bean.getPassword().equals(password)) {
			return bean;
		}
		System.out.println("Wrong loginId and password");
		return null;
	}

	public List search(UserBean bean, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer("select * from user where 1=1"); // where 1=1 is sql injection
		List list = new ArrayList();
		Connection conn = null;

		try {
			if (bean != null) {
				if (bean.getId() > 0) {
					sql.append(" and id = " + bean.getId());
				}
				if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
					sql.append(" and firstName like '" + bean.getFirstName() + "%'");
				}
				if (bean.getLastName() != null && bean.getLastName().length() > 0) {
					sql.append(" and lastName like '" + bean.getLastName() + "%'");
				}
				if (bean.getLoginId() != null && bean.getLoginId().length() > 0) {
					sql.append(" and loginId = '" + bean.getLoginId() + "'");
				}
				if (bean.getPassword() != null && bean.getPassword().length() > 0) {
					sql.append(" and password = '" + bean.getPassword() + "'");
				}
				if (bean.getDob() != null && bean.getDob().getTime() > 0) {
					sql.append(" and dob = '" + new java.sql.Date(bean.getDob().getTime()) + "'");
				}
			}
			if (pageSize > 0) {
				int index = (pageNo - 1) * pageSize;
				sql.append(" limit " + index + " ," + pageSize);
			}
			System.out.println("sql ======> " + sql.toString());
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getInt("id"));
				bean.setFirstName(rs.getString("firstName"));
				bean.setLastName(rs.getString("lastName"));
				bean.setLoginId(rs.getString("loginId"));
				bean.setPassword(rs.getString("password"));
				bean.setDob(rs.getDate("dob"));
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
}
