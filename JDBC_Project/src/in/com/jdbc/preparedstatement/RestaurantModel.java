package in.com.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rays.util.JDBCDataSource;

public class RestaurantModel {

	public int nextPk() {
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(item_id) from restaurant");
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

	public void add(RestaurantBean bean) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into restaurant values(?,?,?,?,?)");
			pstmt.setInt(1, nextPk());
			pstmt.setString(2, bean.getItemName());
			pstmt.setString(3, bean.getCategory());
			pstmt.setDouble(4, bean.getPrice());
			pstmt.setBoolean(5, bean.isAvailable());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("record inserted successfully " + i + " row affected");
		} catch (SQLException e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void update(RestaurantBean bean) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update restaurant set item_name = ?, category = ?, price = ?, is_available = ? where item_id = ?");
			pstmt.setString(1, bean.getItemName());
			pstmt.setString(2, bean.getCategory());
			pstmt.setDouble(3, bean.getPrice());
			pstmt.setBoolean(4, bean.isAvailable());
			pstmt.setInt(5, bean.getItemId());

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

	public void delete(int item_id) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from restaurant where item_id = ? ");
			pstmt.setInt(1, item_id);
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

	public List search(RestaurantBean bean, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer("select * from restaurant where 1=1");
		List list = new ArrayList();
		Connection conn = null;
		try {
			if (bean != null) {
				if (bean.getItemId() > 0) {
					sql.append(" and item_id = " + bean.getItemId());
				}
				if (bean.getItemName() != null && bean.getItemName().length() > 0) {
					sql.append(" and item_name like '" + bean.getItemName() + "%'");
				}
				if (bean.getCategory() != null && bean.getCategory().length() > 0) {
					sql.append(" and category like '" + bean.getCategory() + "%'");
				}
				if (bean.getPrice() > 0) {
					sql.append(" and price = " + bean.getPrice());
				}
				if (bean.isAvailable()) {
					sql.append(" and is_avalaible = " + bean.isAvailable());
				}
			}
			if (pageNo > 0) {
				int index = (pageNo - 1) * pageSize;
				sql.append(" limit " + index + "," + pageSize);
			}

			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new RestaurantBean();
				bean.setItemId(rs.getInt("item_id"));
				bean.setItemName(rs.getString("item_name"));
				bean.setCategory(rs.getString("category"));
				bean.setPrice(rs.getDouble("price"));
				bean.setAvailable(rs.getBoolean("is_available"));
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

	public RestaurantBean findByPk(int item_id) {
		Connection conn = null;
		RestaurantBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from restaurant where item_id = ?");
			pstmt.setInt(1, item_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new RestaurantBean();
				bean.setItemId(rs.getInt("item_id"));
				bean.setItemName(rs.getString("item_name"));
				bean.setCategory(rs.getString("category"));
				bean.setPrice(rs.getDouble("price"));
				bean.setAvailable(rs.getBoolean("is_available"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}
}
