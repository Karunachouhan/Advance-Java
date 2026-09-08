package in.com.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentModel {

	public void createTable() throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advtest", "root", "root");
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(
					"create table Payment(paymmentId int Primary key,amount double,paymentDate date,paymentMethod Varchar(50),transactionId Varchar(50))");
			int i = ps.executeUpdate();
			conn.commit();
			System.out.println("Table created successfully: " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
     
	
}
