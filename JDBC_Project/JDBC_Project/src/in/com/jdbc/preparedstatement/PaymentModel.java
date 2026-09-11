package in.com.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rays.util.JDBCDataSource;

public class PaymentModel {

	public void createTable() throws Exception {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(
					"create table Payment(paymentId int Primary key,amount double,paymentDate date,paymentMethod Varchar(50),transactionId Varchar(50))");
			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Table created successfully: " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public int NextPK() {
		Connection conn = null;
		int PK = 0;
		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement("select max(PaymentId) from payment");
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

	public void add(PaymentBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("insert into payment values(?,?,?,?,?) ");
			ps.setInt(1, NextPK());
			ps.setDouble(2, bean.getAmount());
			ps.setDate(3, new java.sql.Date(bean.getPaymentDate().getTime()));
			ps.setString(4, bean.getPaymentMethod());
			ps.setString(5, bean.getTransactionId());

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

	public void update(PaymentBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(
					"update payment set amount=?,paymentDate=?,paymentMethod=?,transactionId=? where paymentId=?");
			ps.setDouble(1, bean.getAmount());
			ps.setDate(2, new java.sql.Date(bean.getPaymentDate().getTime()));
			ps.setString(3, bean.getPaymentMethod());
			ps.setString(4, bean.getTransactionId());
			ps.setInt(5, bean.getPaymentId());

			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Data updated " + i + " rows affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(int paymentId) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("delete from payment where paymentId = ?");
			ps.setInt(1, paymentId);
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

	public PaymentBean findByPK(int paymentId) {
		Connection conn = null;
		PaymentBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("select * from payment where paymentId = ?");
			ps.setInt(1, paymentId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new PaymentBean();
				bean.setPaymentId(rs.getInt("paymentId"));
				bean.setAmount(rs.getDouble("amount"));
				bean.setPaymentDate(rs.getDate("paymentDate"));
				bean.setPaymentMethod(rs.getString("paymentMethod"));
				bean.setTransactionId(rs.getString("transactionId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public PaymentBean findByTransactionId(String transactionId) {
		Connection conn = null;
		PaymentBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("select * from payment where transactionId = ?");
			ps.setString(1, transactionId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new PaymentBean();
				bean.setPaymentId(rs.getInt("paymentId"));
				bean.setAmount(rs.getDouble("amount"));
				bean.setPaymentDate(rs.getDate("paymentDate"));
				bean.setPaymentMethod(rs.getString("paymentMethod"));
				bean.setTransactionId(rs.getString("transactionId"));
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
