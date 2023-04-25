package com.shopuser.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shopuser.dto.ShopUserVO;

import util.DBManager;

public class ShopUserDAO {
	private ShopUserDAO() {

	}

	private static ShopUserDAO instance = new ShopUserDAO();

	public static ShopUserDAO getInstance() {
		return instance;
	}

	public int shopUserCheck(String userid, String pass) {
		int result = -1;
		String sql = "select pass from shopuser where userid =?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("pass") != null && rs.getString("pass").equals(pass)) {
					result = 1; // 아이디 패스워드 일치
				} else {
					result = 0; // 아이디는 있지만 패스워드 불일치
				}
			} else {
				result = -1; // 검색한 아이디가 없을때
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}

	public ShopUserVO getShopUser(String userid) {
		String sql = "select * from shopuser where userid = ?";
		ShopUserVO suVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				suVo = new ShopUserVO();

				suVo.setUserid(rs.getString("userid"));
				suVo.setPass(rs.getString("pass"));
				suVo.setName(rs.getString("name"));
				suVo.setEmail(rs.getString("email"));
				suVo.setZipcode((rs.getString("zipcode")));
				suVo.setAddress1(rs.getString("address1"));
				suVo.setAddress2(rs.getString("address2"));
				suVo.setPhone(rs.getString("phone"));
				suVo.setGender(rs.getInt("gender"));
				suVo.setGrade(rs.getInt("grade"));
				suVo.setPoint(rs.getInt("point"));
				suVo.setEnter(rs.getTimestamp("enter"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);

		}
		return suVo;
	}

	public int confirmID(String userid) {
		int result = -1;
		String sql = "select * from shopuser where userid=?";

		Connection connn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connn = DBManager.getConnection();
			pstmt = connn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = 1;
			} else {
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(connn, pstmt, rs);
		}
		return result;
	}

	public int insertUser(ShopUserVO uVo) {
		int result = 0;
		String sql = "insert into shopuser(userid, pass, name, email, zipcode,address1,address2, phone, gender) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uVo.getUserid());
			pstmt.setString(2, uVo.getPass());
			pstmt.setString(3, uVo.getName());
			pstmt.setString(4, uVo.getEmail());
			pstmt.setString(5, uVo.getZipcode());
			pstmt.setString(6, uVo.getAddress1());
			pstmt.setString(7, uVo.getAddress2());
			pstmt.setString(8, uVo.getPhone());
			pstmt.setInt(9, uVo.getGender());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}

	public List<ShopUserVO> getAllMember() { // 관리자 회원 관리
		String sql = "select*from shopuser order by enter desc";
		ShopUserVO sVo = null;
		List<ShopUserVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sVo = new ShopUserVO();
				sVo.setUserid(rs.getString("userid"));
				sVo.setPass(rs.getString("pass"));
				sVo.setName(rs.getString("name"));
				sVo.setAddress1(rs.getString("address1"));
				sVo.setAddress2(rs.getString("address2"));
				sVo.setEmail(rs.getString("email"));
				sVo.setGrade(rs.getInt("grade"));
				sVo.setPhone(rs.getString("phone"));
				sVo.setZipcode(rs.getString("zipcode"));
				sVo.setPoint(rs.getInt("point"));
				sVo.setGender(rs.getInt("gender"));
				sVo.setEnter(rs.getTimestamp("enter"));
				list.add(sVo);
			}

		} catch (Exception e) {
			System.out.println("전체멤버 로딩 오류: " + e);
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	public void updateUser(ShopUserVO sVo) {
		String sql = "update shopuser set pass=?, name=?, email=?, zipcode=?, address1=?, address2=?, phone=?, gender=?, grade=?, point=? where userid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sVo.getPass());
			pstmt.setString(2, sVo.getName());
			pstmt.setString(3, sVo.getEmail());
			pstmt.setString(4, sVo.getZipcode());
			pstmt.setString(5, sVo.getAddress1());
			pstmt.setString(6, sVo.getAddress2());
			pstmt.setString(7, sVo.getPhone());
			pstmt.setInt(8, sVo.getGender());
			pstmt.setInt(9, sVo.getGrade());
			pstmt.setInt(10, sVo.getPoint());
			pstmt.setString(11, sVo.getUserid());
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("유저 수정오류(updateUser(ShopUserVO svo) error): " + e);
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public void deleteUserAuction(String userid) {
		String sql = "delete a where userid = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("유저 삭제오류(deleteUserAuction(String userid) error): " + e);
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public void deleteUserCart(String userid) {
		String sql = "delete cart where userid = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("유저 삭제오류(deleteUserCart(String userid) error): " + e);
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public void deleteUserUserlike(String userid) {
		String sql = "delete userlike where userid = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("유저 삭제오류(deleteUserUserlike(String userid) error): " + e);
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public void deleteUserPurchased(String userid) {
		String sql = "delete purchased where userid = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("유저 삭제오류(deleteUserPurchased(String userid) error): " + e);
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public void deleteUserHotdeal(String userid) {
		String sql = "delete hotdeal where userid = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("유저 삭제오류(deleteUserHotdeal(String userid) error): " + e);
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public void deleteUserFreeboard(String userid) {
		String sql = "delete freeboard where userid = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("유저 삭제오류(deleteUserFreeboard(String userid) error): " + e);
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public void deleteUser(String userid) {
		String sql = "delete shopuser where userid = ?"; // ON DELETE CASCADE 실행해줘야함
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("유저 삭제오류(deleteUser(String userid) error): " + e);
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 마이페이지 회원 정보 수정 메서드
	public int myPageUpdateUser(ShopUserVO sVo) {
		String sql = "update shopuser set pass=?, name=?, email=?, zipcode=?, address1=?, address2=?, phone=?, gender=? where userid=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sVo.getPass());
			pstmt.setString(2, sVo.getName());
			pstmt.setString(3, sVo.getEmail());
			pstmt.setString(4, sVo.getZipcode());
			pstmt.setString(5, sVo.getAddress1());
			pstmt.setString(6, sVo.getAddress2());
			pstmt.setString(7, sVo.getPhone());
			pstmt.setInt(8, sVo.getGender());
			pstmt.setString(9, sVo.getUserid());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}

	public void ShopUserexit(String userid) {
		String sql = "delete shopuser where userid=?";
		Connection conn =null;
		PreparedStatement pstmt = null;
		try {
			conn=DBManager.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}

}