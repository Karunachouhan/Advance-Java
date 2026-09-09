package in.com.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

	public void add(PaymentBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("insert into payment values(?,?,?,?,?) ");
			ps.setInt(1, bean.getPaymentId());
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
			
			PreparedStatement ps = conn.prepareStatement("update payment set amount=?,paymentDate=?,paymentMethod=?,transactionId=? where paymentId=?");
			ps.setDouble(1, bean.getAmount());
			ps.setDate(2, new java.sql.Date(bean.getPaymentDate().getTime()));
			ps.setString(3, bean.getPaymentMethod());
			ps.setString(4, bean.getTransactionId());
			ps.setInt(5, bean.getPaymentId());
			
			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Data updated " + i + " rows affected");
		}catch(SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
	}
}  
