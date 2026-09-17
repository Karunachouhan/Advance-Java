package in.com.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rays.util.JDBCDataSource;

public class VendorModel {

	public int nextPk() {
		Connection conn = null;
		int PK = 0;

		try {
			conn = JDBCDataSource.getConnection(); // factory design pattern

			PreparedStatement ps = conn.prepareStatement("select max(vendorId) from vendor");
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

	public void add(VendorBean bean) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement("insert into vendor values(?,?,?,?,?)");
			ps.setInt(1, nextPk());
			ps.setString(2, bean.getVendorName());
			ps.setString(3, bean.getMobileNo());
			ps.setString(4, bean.getAddress());
			ps.setString(5, bean.getServiceType());
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

	public void update(VendorBean bean) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(
					"update vendor set vendorName=?,mobileNo=?,address=?,serviceType=? where vendorID=?");
			ps.setString(1, bean.getVendorName());
			ps.setString(2, bean.getMobileNo());
			ps.setString(3, bean.getAddress());
			ps.setString(4, bean.getServiceType());
			ps.setInt(5, bean.getVendorId());
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

			PreparedStatement ps = conn.prepareStatement("delete from vendor where vendorId = ? ");
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

	public List search(VendorBean bean, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer("select * from vendor where 1=1"); // where 1=1 is a sql injection
		List list = new ArrayList();
		Connection conn = null;

		try {
			if (bean != null) {
				if (bean.getVendorId() > 0) {
					sql.append(" and vendorId = " + bean.getVendorId());
				}
				if (bean.getVendorName() != null && bean.getVendorName().length() > 0) {
					sql.append(" and vendorName like '" + bean.getVendorName() + "%'");
				}
				if (bean.getAddress() != null && bean.getAddress().length() > 0) {
					sql.append(" and address = '" + bean.getAddress() + "'");
				}
				if (bean.getMobileNo() != null && bean.getMobileNo().length() == 10) {
					sql.append(" and mobileNo = '" + bean.getMobileNo() + "'");
				}
				if (bean.getServiceType() != null && bean.getServiceType().length() > 0) {
					sql.append(" and serviceType = '" + bean.getServiceType() + "'");
				}
			}
			if (pageSize > 0) {
				int index = (pageNo - 1) * pageSize;
				sql.append(" limit " + index + "," + pageSize);
			}

			System.out.println("sql --> " + sql.toString());
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean = new VendorBean();
				bean.setVendorId(rs.getInt("vendorId"));
				bean.setVendorName(rs.getString("vendorName"));
				bean.setMobileNo(rs.getString("mobileNo"));
				bean.setAddress(rs.getString("address"));
				bean.setServiceType(rs.getString("serviceType"));
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

	public VendorBean FindByPK(int id) {
		Connection conn = null;
		VendorBean bean = null;
		
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement("select * from vendor where vendorId = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				bean = new VendorBean();
				bean.setVendorId(rs.getInt("vendorId"));
				bean.setVendorName(rs.getString("vendorName"));
				bean.setMobileNo(rs.getString("mobileNo"));
				bean.setAddress(rs.getString("address"));
				bean.setServiceType(rs.getString("serviceType"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
		
	}
}
