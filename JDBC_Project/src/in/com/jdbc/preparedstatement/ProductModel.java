package in.com.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rays.util.JDBCDataSource;

public class ProductModel {

	public int nextPk() {
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(product_id) from product");
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

	public void add(ProductBean bean) {
		Connection conn = null;
		int pk = 0;
		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into product values(?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getBrand());
			pstmt.setDouble(4, bean.getPrice());
			pstmt.setString(5, bean.getWarranty());

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

	public void update(ProductBean bean) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update product set name = ?, brand = ?, price = ?, warranty = ? where  product_id = ?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getBrand());
			pstmt.setDouble(3, bean.getPrice());
			pstmt.setString(4, bean.getWarranty());
			pstmt.setInt(5, bean.getProductId());

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

	public void delete(int product_id) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from product where product_id = ?");
			pstmt.setInt(1, product_id);
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

	public List search(ProductBean bean, int pageNo, int pageSize) {
		List<ProductBean> list = new ArrayList<ProductBean>();
		StringBuffer sql = new StringBuffer("select * from product where 1=1");
		Connection conn = null;

		try {
			if (bean != null) {
				if (bean.getProductId() > 0) {
					sql.append(" and product_id = " + bean.getProductId());
				}
				if (bean.getName() != null && bean.getName().length() > 0) {
					sql.append(" and name like '" + bean.getName() + "%'");
				}
				if (bean.getBrand() != null && bean.getBrand().length() > 0) {
					sql.append(" and brand like '" + bean.getBrand() + "%'");
				}
				if (bean.getPrice() > 0) {
					sql.append(" and price = " + bean.getPrice());
				}
				if (bean.getWarranty() != null && bean.getWarranty().length() > 0) {
					sql.append(" and warranty like '" + bean.getWarranty() + "%'");
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
				bean = new ProductBean();
				bean.setProductId(rs.getInt("product_id"));
				bean.setName(rs.getString("name"));
				bean.setBrand(rs.getString("brand"));
				bean.setPrice(rs.getDouble("price"));
				bean.setWarranty(rs.getString("warranty"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

	public ProductBean findByPk(int product_id) {
		Connection conn = null;
		ProductBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from product where product_id = ?");
			pstmt.setInt(1, product_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ProductBean();
				bean.setProductId(rs.getInt("product_id"));
				bean.setName(rs.getString("name"));
				bean.setBrand(rs.getString("brand"));
				bean.setPrice(rs.getDouble("price"));
				bean.setWarranty(rs.getString("warranty"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public ProductBean findByUniqueColumn(String name) {
		Connection conn = null;
		ProductBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from product where name = ?");
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ProductBean();
				bean.setProductId(rs.getInt("product_id"));
				bean.setName(rs.getString("name"));
				bean.setBrand(rs.getString("brand"));
				bean.setPrice(rs.getDouble("price"));
				bean.setWarranty(rs.getString("warranty"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}
}
