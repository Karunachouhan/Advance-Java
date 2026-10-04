package in.com.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rays.util.JDBCDataSource;

public class BusModel {

	public int nextPk() {
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(bus_id) from bus");
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

	public void add(BusBean bean) {
		Connection conn = null;
		int pk = 0;

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into bus values(?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getBusNumber());
			pstmt.setString(3, bean.getRoute());
			pstmt.setInt(4, bean.getCapacity());
			pstmt.setTimestamp(5, bean.getDepartureTime());

			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record inserted successfully " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void update(BusBean bean) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update bus set bus_number = ?, route = ?, capacity = ?, departure_time = ? where bus_id = ?");
			pstmt.setString(1, bean.getBusNumber());
			pstmt.setString(2, bean.getRoute());
			pstmt.setInt(3, bean.getCapacity());
			pstmt.setTimestamp(4, bean.getDepartureTime());
			pstmt.setInt(5, bean.getBusId());

			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record updated successfully " + i + " row affected");

		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(int bus_id) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from bus where bus_id = ?");
			pstmt.setInt(1, bus_id);
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Record deleted successfully " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public List search(BusBean bean, int pageNo, int pageSize) {
		List list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from bus where 1=1");
		Connection conn = null;

		try {
			if (bean != null) {
				if (bean.getBusId() > 0) {
					sql.append(" and bus_id = " + bean.getBusId());
				}
				if (bean.getBusNumber() != null && bean.getBusNumber().length() > 0) {
					sql.append(" and bus_number like '" + bean.getBusNumber() + "'");
				}
				if (bean.getCapacity() > 0) {
					sql.append(" and capacity = " + bean.getCapacity());
				}
				if (bean.getRoute() != null && bean.getRoute().length() > 0) {
					sql.append(" and route like '" + bean.getRoute() + "'");
				}
				if (bean.getDepartureTime() != null) {
					sql.append(" and departure_time = " + bean.getDepartureTime());
				}
			}
			if (pageNo > 0) {
				int index = (pageNo - 1) * pageSize;
				sql.append(" limit " + index + "," + pageSize);
			}
			System.out.println("sql -----------------> " + sql);
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean.setBusId(rs.getInt("bus_id"));
				bean.setBusNumber(rs.getString("bus_number"));
				bean.setRoute(rs.getString("route"));
				bean.setCapacity(rs.getInt("capacity"));
				bean.setDepartureTime(rs.getTimestamp("departure_time"));
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

	public BusBean findByPk(int bus_id) {
		Connection conn = null;
		BusBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from bus where bus_id = ?");
			pstmt.setInt(1, bus_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new BusBean();
				bean.setBusId(rs.getInt("bus_id"));
				bean.setBusNumber(rs.getString("bus_number"));
				bean.setRoute(rs.getString("route"));
				bean.setCapacity(rs.getInt("capacity"));
				bean.setDepartureTime(rs.getTimestamp("departure_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public BusBean findByUniqueColumn(String bus_number) {
		Connection conn = null;
		BusBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from bus where bus_number = ?");
			pstmt.setString(1, bus_number);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new BusBean();
				bean.setBusId(rs.getInt("bus_id"));
				bean.setBusNumber(rs.getString("bus_number"));
				bean.setRoute(rs.getString("route"));
				bean.setCapacity(rs.getInt("capacity"));
				bean.setDepartureTime(rs.getTimestamp("departure_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}
}
