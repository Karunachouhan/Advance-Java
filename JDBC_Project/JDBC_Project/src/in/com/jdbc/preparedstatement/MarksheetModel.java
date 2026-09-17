package in.com.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rays.util.JDBCDataSource;

public class MarksheetModel {

	public int nextPK() {
		Connection conn = null;
		int PK = 0;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement("select max(id) from marksheet");
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

	public void add(MarksheetBean bean) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("insert into marksheet values(?,?,?,?,?,?)");
			ps.setInt(1, nextPK());
			ps.setInt(2, bean.getRollNo());
			ps.setString(3, bean.getName());
			ps.setInt(4, bean.getPhy());
			ps.setInt(5, bean.getChm());
			ps.setInt(6, bean.getMaths());
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

	public void update(MarksheetBean bean) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn
					.prepareStatement("update marksheet set rollNo=?,name= ?,phy=?,chm=?,maths=? where id= ?");
			ps.setInt(1, bean.getRollNo());
			ps.setString(2, bean.getName());
			ps.setInt(3, bean.getPhy());
			ps.setInt(4, bean.getChm());
			ps.setInt(5, bean.getMaths());
			ps.setInt(6, bean.getId());

			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Data updated successfully " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(int id) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("delete from marksheet where id = ?");
			ps.setInt(1, id);
			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Data deleted successfully " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public List search(MarksheetBean bean, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer("select * from marksheet where 1=1");
		List list = new ArrayList();
		Connection conn = null;

		try {
			if (bean != null) {
				if (bean.getId() > 0) {
					sql.append(" and id = " + bean.getId());
				}
				if (bean.getRollNo() > 0) {
					sql.append(" and rollNo = " + bean.getRollNo());
				}
				if (bean.getName() != null && bean.getName().length() > 0) {
					sql.append(" and name = '" + bean.getName() + "'");
				}
				if (bean.getPhy() > 0) {
					sql.append(" and phy = " + bean.getPhy());
				}
				if (bean.getChm() > 0) {
					sql.append(" and chm = " + bean.getChm());
				}
				if (bean.getMaths() > 0) {
					sql.append(" and maths = " + bean.getMaths());
				}
			}
			if (pageSize > 0) {
				int index = (pageNo - 1) * pageSize;
				sql.append(" limit " + index + "," + pageSize);
			}
			System.out.println("sql--> " + sql.toString());
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new MarksheetBean();
				bean.setId(rs.getInt("id"));
				bean.setRollNo(rs.getInt("rollNo"));
				bean.setName(rs.getString("name"));
				bean.setPhy(rs.getInt("phy"));
				bean.setChm(rs.getInt("chm"));
				bean.setMaths(rs.getInt("maths"));
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

	public MarksheetBean findByRollNo(int rollNo) {
		Connection conn = null;
		MarksheetBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("select * from marksheet where rollNo = ?");
			ps.setInt(1, rollNo);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new MarksheetBean();
				bean.setId(rs.getInt("id"));
				bean.setRollNo(rs.getInt("rollNo"));
				bean.setName(rs.getString("name"));
				bean.setPhy(rs.getInt("phy"));
				bean.setChm(rs.getInt("chm"));
				bean.setMaths(rs.getInt("maths"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public MarksheetBean findByPK(int id) {
		Connection conn = null;
		MarksheetBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("select * from marksheet where id = ?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new MarksheetBean();
				bean.setId(rs.getInt("id"));
				bean.setRollNo(rs.getInt("rollNo"));
				bean.setName(rs.getString("name"));
				bean.setPhy(rs.getInt("phy"));
				bean.setChm(rs.getInt("chm"));
				bean.setMaths(rs.getInt("maths"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}
}
