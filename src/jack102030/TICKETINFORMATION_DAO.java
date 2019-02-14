package jack102030;

import java.util.*;

import java.sql.*;

public class TICKETINFORMATION_DAO implements TICKETINFORMATION_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106";
	String passwd = "123456";
	
	private static final String INSERT_STMT =
			"INSERT INTO TICKETINFORMATION (ti_no,ti_name,ti_price) VALUES (TICKETINFORMATION_seq.NEXTVAL, ?, ?)";
	private static final String GET_ALL_STMT =
			"SELECT ti_no,ti_name,ti_price FROM TICKETINFORMATION order by ti_no";
	private static final String GET_ONE_STMT = 
			"SELECT ti_no,ti_name,ti_price FROM TICKETINFORMATION where ti_no = ?";
	private static final String DELETE =
			"DELETE FROM TICKETINFORMATION where ti_no = ?";
	private static final String UPDATE = 
			"UPDATE TICKETINFORMATION set ti_name=?, ti_price=? where ti_no = ?";
	

	@Override
	public void insert(TICKETINFORMATION_VO ticketinformationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, ticketinformationVO.getTi_name());
			pstmt.setString(2, ticketinformationVO.getTi_price());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occuted. " + se.getMessage());
			
		} finally {
			if (pstmt !=  null) {
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
	public void update(TICKETINFORMATION_VO ticketinformationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, ticketinformationVO.getTi_name());
			pstmt.setString(2, ticketinformationVO.getTi_price());
			pstmt.setString(3, ticketinformationVO.getTi_no());
			
			
			pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			
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
	public void delete(String ti_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, ti_no);
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database drive. " + e.getMessage());
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			
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
	public TICKETINFORMATION_VO findByPrimaryKey(String ti_no) {
		TICKETINFORMATION_VO ticketinformationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, ti_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ticketinformationVO = new TICKETINFORMATION_VO();
				ticketinformationVO.setTi_no(rs.getString("ti_no"));
				ticketinformationVO.setTi_name(rs.getString("ti_name"));
				ticketinformationVO.setTi_price(rs.getString("ti_price"));
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			
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
		return ticketinformationVO;
	}

	@Override
	public List<TICKETINFORMATION_VO> getAll() {
		List<TICKETINFORMATION_VO> list = new ArrayList<TICKETINFORMATION_VO>();
		TICKETINFORMATION_VO ticketinformationVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ticketinformationVO = new TICKETINFORMATION_VO();
				ticketinformationVO.setTi_no(rs.getString("ti_no"));
				ticketinformationVO.setTi_name(rs.getString("ti_name"));
				ticketinformationVO.setTi_price(rs.getString("ti_price"));
				list.add(ticketinformationVO);
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
			if(rs != null) {
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
		
		TICKETINFORMATION_DAO dao = new TICKETINFORMATION_DAO();
		
		// 新增
		TICKETINFORMATION_VO ticketinformationVO1 = new TICKETINFORMATION_VO();
		ticketinformationVO1.setTi_name("新增票");
		ticketinformationVO1.setTi_price("11111");
		dao.insert(ticketinformationVO1);
		
		// 修改
		TICKETINFORMATION_VO ticketinformationVO2 = new TICKETINFORMATION_VO();
		ticketinformationVO2.setTi_no("1");
		ticketinformationVO2.setTi_name("修改票");
		ticketinformationVO2.setTi_price("22");
		dao.update(ticketinformationVO2);
		
		// 刪除
		dao.delete("15");
		
		// 查詢
		TICKETINFORMATION_VO ticketinformationVO3 = dao.findByPrimaryKey("1");
		System.out.print(ticketinformationVO3.getTi_no() + ",");
		System.out.print(ticketinformationVO3.getTi_name() + ",");
		System.out.println(ticketinformationVO3.getTi_price());
		System.out.println("---------------------");
		
		// 查詢
		List<TICKETINFORMATION_VO> list = dao.getAll();
		for (TICKETINFORMATION_VO ticketinformationVO4 : list) {
			System.out.print(ticketinformationVO4.getTi_no() + ",");
			System.out.print(ticketinformationVO4.getTi_name() + ",");
			System.out.print(ticketinformationVO4.getTi_price());
			System.out.println();
		}
		
		
	}

}
