package com.freeboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.freeboard.dto.FreeBoardVO;

import util.DBManager;

public class FreeBoardDAO {
	private FreeBoardDAO() {

	}

	private static FreeBoardDAO instance = new FreeBoardDAO();

	public static FreeBoardDAO getInstance() {
		return instance;
	}

	public List<FreeBoardVO> getMyBoardList(int page, int limit, String userid) {
		String sql = "select * from " + "(select rownum rnum, num, userid, " + "title, content, imgUrl, "
				+ "readcount, writedate from " + "(select * from freeboard where userid=? order by writedate desc)) "
				+ "where rnum >= ? and rnum <= ?";
		
		// ex
		// page 1 → 1 ~ 10
		// page 2 → 11 ~ 20
		// page 3 → 21 ~ 30

		List<FreeBoardVO> list = new ArrayList<FreeBoardVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				FreeBoardVO qbVo = new FreeBoardVO();
				qbVo.setUserid(rs.getString("userid"));
				qbVo.setNum(rs.getInt("num"));
				qbVo.setTitle(rs.getString("title"));
				qbVo.setContent(rs.getString("content"));
				qbVo.setWritedate(rs.getTimestamp("writedate"));
				qbVo.setReadcount(rs.getInt("readcount"));
				qbVo.setImgurl(rs.getString("imgUrl"));
				list.add(qbVo);
				System.out.println(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;

	}



	public List<FreeBoardVO> selectAllFreeBoards() {
		String sql = "select * from freeboard order by num desc"; // 내림차순
		List<FreeBoardVO> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				FreeBoardVO fbVo = new FreeBoardVO();
				fbVo.setNum(rs.getInt("num"));
				fbVo.setUserid(rs.getString("userid"));
				fbVo.setTitle(rs.getString("title"));
				fbVo.setContent(rs.getString("content"));
				fbVo.setImgurl(rs.getString("imgurl"));
				fbVo.setReadcount(rs.getInt("readcount"));
				fbVo.setWritedate(rs.getTimestamp("writedate"));
				list.add(fbVo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}

	public void insertFreeBoard(FreeBoardVO fbVo) {
		String sql = "insert into freeboard(" + "num, userid, title, content, imgurl)"
				+ "values(freeboard_seq.nextval,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			System.out.println(fbVo.getUserid());
			System.out.println(fbVo.getTitle());
			System.out.println(fbVo.getContent());
			System.out.println(fbVo.getImgurl());

			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fbVo.getUserid());
			pstmt.setString(2, fbVo.getTitle());
			pstmt.setString(3, fbVo.getContent());
			pstmt.setString(4, fbVo.getImgurl());
			pstmt.executeUpdate();

		} catch (Exception e) {
			DBManager.close(conn, pstmt);
		}
	}

	public void updateReadCount(String num) {
		String sql = "update freeboard set readcount=readcount+1 where num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

	}

	public FreeBoardVO selectOneFreeBoardByNum(String num) {
		String sql = "select * from freeboard where num = ?";
		FreeBoardVO fbVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				fbVo = new FreeBoardVO();
				fbVo.setNum(rs.getInt("num"));
				fbVo.setUserid(rs.getString("userid"));
				fbVo.setTitle(rs.getString("title"));
				fbVo.setContent(rs.getString("content"));
				fbVo.setWritedate(rs.getTimestamp("writedate"));
				fbVo.setImgurl(rs.getString("imgurl"));
				fbVo.setReadcount(rs.getInt("readcount"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return fbVo;
	}

	public void updateFreeBoard(FreeBoardVO fbVo) {
		String sql = "update freeboard set userid=?, title=?, content=?, imgurl=? where num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fbVo.getUserid());
			pstmt.setString(2, fbVo.getTitle());
			pstmt.setString(3, fbVo.getContent());
			pstmt.setString(4, fbVo.getImgurl());
			pstmt.setInt(5, fbVo.getNum());
			pstmt.executeUpdate();
		} catch (Exception e) {
			DBManager.close(conn, pstmt);
		}

	}

	public void deleteFreeBoard(int num) {
		String sql = "delete freeboard where num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

	}

	// 관리자 페이지
	public FreeBoardVO getBoardInfo(String num) {
		String sql = "select * from freeboard where num = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FreeBoardVO fbVo = new FreeBoardVO();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				fbVo.setUserid(rs.getString("userid"));
				fbVo.setNum(rs.getInt("num"));
				fbVo.setTitle(rs.getString("title"));
				fbVo.setContent(rs.getString("content"));
				fbVo.setWritedate(rs.getTimestamp("writedate"));
				fbVo.setReadcount(rs.getInt("readcount"));
				fbVo.setImgurl(rs.getString("imgurl"));
			}
		} catch (Exception e) {
			System.out.println("게시글 불러오기 오류(getBoardInfo(String num) error) : " + e);
		} finally {
			DBManager.close(conn, pstmt);
		}
		return fbVo;
	}

	public void deleteBoardByNum(String num) {
		String sql = "delete freeboard where num = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시판 삭제 오류(deleteBoardByNum(String num) error): " + e);
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public List<FreeBoardVO> getAllBoardList() {
		String sql = "select * from freeboard order by writedate desc";
		FreeBoardVO fbVo = null;
		List<FreeBoardVO> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			System.out.println(conn);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println("111gkdl");
				fbVo = new FreeBoardVO();
				System.out.println(rs.getString("userid"));
				fbVo.setUserid(rs.getString("userid"));
				fbVo.setNum(rs.getInt("num"));
				fbVo.setTitle(rs.getString("title"));
				fbVo.setContent(rs.getString("content"));
				fbVo.setWritedate(rs.getTimestamp("writedate"));
				fbVo.setReadcount(rs.getInt("readcount"));
				fbVo.setImgurl(rs.getString("imgUrl"));
				list.add(fbVo);
				System.out.println(2);
			}

		} catch (Exception e) {
			System.out.println("전체 자유게시판 리스트 로딩 오류: " + e);
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		System.out.println(list);
		return list;

	}

	// 페이징을 위해 레코드 개수 세는 메소드
	public int getListCount() {

		int x = 0; // 전체 글의 개수를 담을 변수
		String sql = "select count(*) from freeboard";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return x;
	}

	public List<FreeBoardVO> getBoardList(int page, int limit) {
		// sql문 작성시 " " 공백 꼭 작성!
		String sql = "select * from " + "(select rownum rnum, num, userid, " + "title, content, imgUrl, "
				+ "readcount, writedate from " + "(select * from freeboard order by writedate desc)) "
				+ "where rnum >= ? and rnum <= ?";

		// ex
		// page 1 → 1 ~ 10
		// page 2 → 11 ~ 20
		// page 3 → 21 ~ 30

		List<FreeBoardVO> list = new ArrayList<FreeBoardVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				FreeBoardVO qbVo = new FreeBoardVO();
				qbVo.setUserid(rs.getString("userid"));
				qbVo.setNum(rs.getInt("num"));
				qbVo.setTitle(rs.getString("title"));
				qbVo.setContent(rs.getString("content"));
				qbVo.setWritedate(rs.getTimestamp("writedate"));
				qbVo.setReadcount(rs.getInt("readcount"));
				qbVo.setImgurl(rs.getString("imgUrl"));
				list.add(qbVo);
				System.out.println(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;

	}

}