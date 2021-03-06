package com.mealsorderdetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MealsorderdetailDAO implements MealsorderdetailDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "JOIN";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO MEALSORDERDETAIL (order_no,meals_no,mo_count) VALUES ( ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT order_no,meals_no,mo_count FROM MEALSORDERDETAIL order by order_no";
	private static final String GET_ONE_STMT = 
			"SELECT order_no,meals_no,mo_count FROM MEALSORDERDETAIL where order_no = ?";
	private static final String DELETE = 
			"DELETE FROM MEALSORDERDETAIL where order_no = ?";
	private static final String UPDATE = 
			"UPDATE MEALSORDERDETAIL set meals_no=?, mo_count=? where order_no = ?";


	@Override
	public void insert(MealsorderdetailVO mealsorderdetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			//pstmt.setString(1, ticketorderVO.getOrder_no());
			pstmt.setString(1, mealsorderdetailVO.getOrder_no());
			pstmt.setString(2, mealsorderdetailVO.getMeals_no());
			pstmt.setInt(3, mealsorderdetailVO.getMo_count());
			
			
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void update(MealsorderdetailVO mealsorderdetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, mealsorderdetailVO.getMeals_no());
			pstmt.setInt(2, mealsorderdetailVO.getMo_count());
			pstmt.setString(3, mealsorderdetailVO.getOrder_no());
//			pstmt.setString(2, mealsorderdetailVO.getMeals_no());
			
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void delete(String order_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, order_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public MealsorderdetailVO findByPrimaryKey(String order_no) {
		MealsorderdetailVO mealsorderdetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, order_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				mealsorderdetailVO = new MealsorderdetailVO();
				mealsorderdetailVO.setOrder_no(rs.getString("order_no"));
				mealsorderdetailVO.setMeals_no(rs.getString("meals_no"));
				mealsorderdetailVO.setMo_count(rs.getInt("mo_count"));
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return mealsorderdetailVO;
	}

	@Override
	public List<MealsorderdetailVO> getAll() {
		List<MealsorderdetailVO> list = new ArrayList<MealsorderdetailVO>();
		MealsorderdetailVO mealsorderdetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				mealsorderdetailVO = new MealsorderdetailVO();
				mealsorderdetailVO.setOrder_no(rs.getString("order_no"));
				mealsorderdetailVO.setMeals_no(rs.getString("meals_no"));
				mealsorderdetailVO.setMo_count(rs.getInt("mo_count"));
				
				list.add(mealsorderdetailVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	
	public static void main(String[] args) {

		MealsorderdetailDAO dao = new MealsorderdetailDAO();

		// �s�W
		MealsorderdetailVO mealsorderdetailVO1 = new MealsorderdetailVO();
		mealsorderdetailVO1.setOrder_no("20");
		mealsorderdetailVO1.setMeals_no("MEALS010");
		mealsorderdetailVO1.setMo_count(100);
		dao.insert(mealsorderdetailVO1);

		// �ק�
		MealsorderdetailVO mealsorderdetailVO2 = new MealsorderdetailVO();
		mealsorderdetailVO2.setOrder_no("1");
		mealsorderdetailVO2.setMeals_no("MEALS001");
		mealsorderdetailVO2.setMo_count(46);
		dao.update(mealsorderdetailVO2);

		// �R��
		dao.delete("5");

		// �d��
		MealsorderdetailVO mealsorderdetailVO3 = dao.findByPrimaryKey("6");
		System.out.print(mealsorderdetailVO3.getOrder_no() + ",");
		System.out.print(mealsorderdetailVO3.getMeals_no() + ",");
		System.out.println(mealsorderdetailVO3.getMo_count());
		System.out.println("---------------------");

		// �d��
		List<MealsorderdetailVO> list = dao.getAll();
		for (MealsorderdetailVO mealsorderdetailVO4 : list) {
			System.out.print(mealsorderdetailVO4.getOrder_no() + ",");
			System.out.print(mealsorderdetailVO4.getMeals_no() + ",");
			System.out.print(mealsorderdetailVO4.getMo_count() + ",");
			System.out.println();
		}
	}

}
