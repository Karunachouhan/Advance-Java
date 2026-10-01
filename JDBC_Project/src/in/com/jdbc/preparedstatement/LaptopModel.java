package in.com.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rays.util.JDBCDataSource;

public class LaptopModel {

	public int NextPk() {
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select max(laptop_id) from laptop");
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

	public void add(LaptopBean bean) {
		Connection conn = null;
		int pk = 0;
		try {
			pk = NextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into laptop values(?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getBrand());
			pstmt.setString(3, bean.getProcessor());
			pstmt.setInt(4, bean.getRam());
			pstmt.setInt(5, bean.getStorage());
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

	public void update(LaptopBean bean) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update laptop set brand = ?, processor = ?, ram = ?, storage = ? where laptop_id = ?");
			pstmt.setString(1, bean.getBrand());
			pstmt.setString(2, bean.getProcessor());
			pstmt.setInt(3, bean.getRam());
			pstmt.setInt(4, bean.getStorage());
			pstmt.setInt(5, bean.getLaptopId());

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

	public void delete(int laptop_id) {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from laptop where laptop_id = ?");
			pstmt.setInt(1, laptop_id);
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

	public List search(LaptopBean bean, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer("select * from laptop where 1=1");
		List list = new ArrayList();
		Connection conn = null;

		try {
			if (bean != null) {
				if (bean.getLaptopId() > 0) {
					sql.append(" and laptop_id = " + bean.getLaptopId());
				}
				if (bean.getBrand() != null && bean.getBrand().length() > 0) {
					sql.append(" and brand like '" + bean.getBrand() + "'");
				}
				if (bean.getProcessor() != null && bean.getProcessor().length() > 0) {
					sql.append(" and processor like '" + bean.getProcessor() + "'");
				}
				if (bean.getRam() > 0) {
					sql.append(" and ram = " + bean.getRam());
				}
				if (bean.getStorage() > 0) {
					sql.append(" and storage = " + bean.getStorage());
				}
			}
			if (pageNo > 0) {
				int index = (pageNo - 1) * pageSize;
				sql.append(" limit " + index + " , " + pageSize);
			}
			System.out.println("sql-----------> " + sql.toString());
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new LaptopBean();
				bean.setLaptopId(rs.getInt("laptop_id"));
				bean.setBrand(rs.getString("brand"));
				bean.setProcessor(rs.getString("processor"));
				bean.setRam(rs.getInt("ram"));
				bean.setStorage(rs.getInt("storage"));
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

	public LaptopBean findByPk(int laptopId) {
		Connection conn = null;
		LaptopBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from laptop where laptop_id = ?");
			pstmt.setInt(1, bean.getLaptopId());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean.setLaptopId(rs.getInt("laptopId"));
				bean.setBrand(rs.getString("brand"));
				bean.setProcessor(rs.getString("processor"));
				bean.setRam(rs.getInt("ram"));
				bean.setStorage(rs.getInt("storage"));
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
