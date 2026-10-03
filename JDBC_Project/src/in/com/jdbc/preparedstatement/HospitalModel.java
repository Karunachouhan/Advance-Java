package in.com.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rays.util.JDBCDataSource;

public class HospitalModel {

	public int nextPk() {
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(patient_id) from hospital");
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

	public void add(HospitalBean bean) {
		Connection conn = null;
		int pk = 0;

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into hospital values(?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setInt(3, bean.getAge());
			pstmt.setString(4, bean.getBloodGroup());
			pstmt.setString(5, bean.getDisease());
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

	public void update(HospitalBean bean) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn
					.prepareStatement("update hospital set name=?, age=?, blood_group=?, disease=? where patient_id=?");
			pstmt.setString(1, bean.getName());
			pstmt.setInt(2, bean.getAge());
			pstmt.setString(3, bean.getBloodGroup());
			pstmt.setString(4, bean.getDisease());
			pstmt.setInt(5, bean.getPatientId());
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

	public void delete(int patient_id) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from hospital where patient_id = ?");
			pstmt.setInt(1, patient_id);
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

	public List search(HospitalBean bean, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer("select * from hospital where 1=1");
		List list = new ArrayList();
		Connection conn = null;

		try {
			if (bean != null) {
				if (bean.getPatientId() > 0) {
					sql.append(" and patient_id = " + bean.getPatientId());
				}
				if (bean.getName() != null && bean.getName().length() > 0) {
					sql.append(" and name like '" + bean.getName() + "'");
				}
				if (bean.getAge() > 0) {
					sql.append(" and age = " + bean.getAge());
				}
				if (bean.getBloodGroup() != null && bean.getBloodGroup().length() > 0) {
					sql.append(" and blood_group like '" + bean.getBloodGroup() + "'");
				}
				if (bean.getDisease() != null && bean.getDisease().length() > 0) {
					sql.append(" and disease like '" + bean.getDisease() + "'");
				}
			}
			if (pageNo > 0) {
				int index = (pageNo - 1) * pageSize;
				sql.append(" limit " + index + "," + pageSize);
			}
			System.out.println("sql -------------------> " + sql);
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean.setPatientId(rs.getInt("patient_id"));
				bean.setName(rs.getString("name"));
				bean.setAge(rs.getInt("age"));
				bean.setBloodGroup(rs.getString("blood_group"));
				bean.setDisease(rs.getString("disease"));
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

	public HospitalBean findByPk(int patient_id) {
		Connection conn = null;
		HospitalBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from hospital where patient_id = ?");
			pstmt.setInt(1, patient_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new HospitalBean();
				bean.setPatientId(rs.getInt("patient_id"));
				bean.setName(rs.getString("name"));
				bean.setAge(rs.getInt("age"));
				bean.setBloodGroup(rs.getString("blood_group"));
				bean.setDisease(rs.getString("disease"));
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
