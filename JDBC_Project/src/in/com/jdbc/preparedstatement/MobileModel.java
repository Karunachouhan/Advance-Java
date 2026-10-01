package in.com.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rays.util.JDBCDataSource;

public class MobileModel {

	public int nextPk() {
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select max(mobile_id) from mobile");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	public void add(MobileBean bean) {
		Connection conn = null;
		int pk = 0;

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into mobile values(?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getBrand());
			pstmt.setString(3, bean.getModel());
			pstmt.setInt(4, bean.getBattery());
			pstmt.setString(5, bean.getOperatingSystem());

			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Data inserted successfully " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);

		}
	}

	public void update(MobileBean bean) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update mobile set brand=?, model=?, battery=?, operating_system=? where mobile_id = ?");
			pstmt.setString(1, bean.getBrand());
			pstmt.setString(2, bean.getModel());
			pstmt.setInt(3, bean.getBattery());
			pstmt.setString(4, bean.getOperatingSystem());
			pstmt.setInt(5, bean.getMobileId());

			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Data updated successfully " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(int mobileId) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from mobile where mobile_id = ?");
			pstmt.setInt(1, mobileId);
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Data deleted successfully " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public List search(MobileBean bean, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer("select * from mobile where 1=1");
		List list = new ArrayList();
		Connection conn = null;

		try {
			if (bean != null) {
				if (bean.getMobileId() > 0) {
					sql.append(" and mobile_id = " + bean.getMobileId());
				}
				if (bean.getBrand() != null && bean.getBrand().length() > 0) {
					sql.append(" and brand '" + bean.getBrand() + "'");
				}
				if (bean.getModel() != null && bean.getModel().length() > 0) {
					sql.append(" and model = '" + bean.getModel() + "'");
				}
				if (bean.getBattery() > 0) {
					sql.append(" and battery = " + bean.getBattery());
				}
				if (bean.getOperatingSystem() != null && bean.getOperatingSystem().length() > 0) {
					sql.append(" and operating_system = '" + bean.getOperatingSystem() + "'");
				}
			}
			if (pageNo > 0) {
				int index = (pageNo - 1) * pageSize;
				sql.append(" limit " + index + " , " + pageSize);
			}
			System.out.println("sql -------> " + sql.toString());
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new MobileBean();
				bean.setMobileId(rs.getInt("mobile_id"));
				bean.setBrand(rs.getString("brand"));
				bean.setModel(rs.getString("model"));
				bean.setBattery(rs.getInt("battery"));
				bean.setOperatingSystem(rs.getString("operating_system"));
				list.add(bean);
			}
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;

	}
}
