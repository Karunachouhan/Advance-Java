package in.com.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rays.util.JDBCDataSource;

public class OrderModel {

	public int nextPK() {
		Connection conn = null;
		int PK = 0;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement("select max(orderId) from st_order");
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

	public void add(OrderBean bean) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("insert into st_order values(?,?,?,?,?)");
			ps.setInt(1, nextPK());
			ps.setDate(2, new java.sql.Date(bean.getOrderDate().getTime()));
			ps.setDouble(3, bean.getAmount());
			ps.setString(4, bean.getStatus());
			ps.setString(5, bean.getCustomerId());
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

	public void update(OrderBean bean) {
		Connection conn = null;
		
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement("update st_order set orderDate=?,amount=?,status=?,customerId=? where orderId=?");
			ps.setDate(1, new java.sql.Date(bean.getOrderDate().getTime()));
			ps.setDouble(2, bean.getAmount());
			ps.setString(3,bean.getStatus());
			ps.setString(4, bean.getCustomerId());
			ps.setInt(5, bean.getOrderId());
			
			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Data updated " + i + " row affected");
		}catch(SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
	}
	
	public void delete(int orderId) {
		Connection conn = null;
		
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement("delete from st_order where orderId = ?");
			ps.setInt(1, orderId);
			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Data deleted successfully " + i + " row affected");
		}catch(SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
	}
}
