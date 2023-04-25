package com.qnaboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qnaboard.dto.QnaBoardVO;

import util.DBManager;

public class QnaBoardDAO {
	private QnaBoardDAO() {

	}

	private static QnaBoardDAO instance = new QnaBoardDAO();

	public static QnaBoardDAO getInstance() {
		return instance;
	}

	public List<QnaBoardVO> selectAllQnaBoards() {
		String sql = "select * from qnaboard order by num desc"; // 내림차순
		
		List<QnaBoardVO> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				QnaBoardVO qbVo = new QnaBoardVO();
				qbVo.setNum(rs.getInt("num"));
				qbVo.setUserid(rs.getString("userid"));
				qbVo.setTitle(rs.getString("title"));
				qbVo.setContent(rs.getString("content"));
				qbVo.setImgurl(rs.getString("imgurl"));
				qbVo.setReadcount(rs.getInt("readcount"));
				qbVo.setWritedate(rs.getTimestamp("writedate"));
				list.add(qbVo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}

	public void insertQnaBoard(QnaBoardVO qbVo) {
		String sql = "insert into qnaboard(" + "num, userid, title, content, imgurl)"
				+ "values(qnaboard_seq.nextval,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			System.out.println(qbVo.getUserid());
			System.out.println(qbVo.getTitle());
			System.out.println(qbVo.getContent());
			System.out.println(qbVo.getImgurl());
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qbVo.getUserid());
			pstmt.setString(2, qbVo.getTitle());
			pstmt.setString(3, qbVo.getContent());
			pstmt.setString(4, qbVo.getImgurl());
			pstmt.executeUpdate();

		} catch (Exception e) {
			DBManager.close(conn, pstmt);
		}
	}

	public void updateReadCount(String num) {
		String sql = "update qnaboard set readcount=readcount+1 where num=?";
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

	public QnaBoardVO selectOneQnaBoardByNum(String num) {
		String sql = "select * from qnaboard where num = ?";
		QnaBoardVO qbVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				qbVo = new QnaBoardVO();
				qbVo.setNum(rs.getInt("num"));
				qbVo.setUserid(rs.getString("userid"));
				qbVo.setTitle(rs.getString("title"));
				qbVo.setContent(rs.getString("content"));
				qbVo.setWritedate(rs.getTimestamp("writedate"));
				qbVo.setImgurl(rs.getString("imgurl"));
				qbVo.setReadcount(rs.getInt("readcount"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return qbVo;
	}

	public void updateQnaBoard(QnaBoardVO qbVo) {
		String sql = "update qnaboard set userid=?, title=?, content=?, imgurl=? where num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qbVo.getUserid());
			pstmt.setString(2, qbVo.getTitle());
			pstmt.setString(3, qbVo.getContent());
			pstmt.setString(4, qbVo.getImgurl());
			pstmt.setInt(5, qbVo.getNum());
			pstmt.executeUpdate();
		} catch (Exception e) {
			DBManager.close(conn, pstmt);
		}

	}

	public void deleteQnaBoard(int num) {
		String sql = "delete qnaboard where num=?";
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
	public QnaBoardVO getBoardInfo(String num) {
		String sql = "select * from qnaboard where num = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QnaBoardVO qbVo = new QnaBoardVO();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				qbVo.setUserid(rs.getString("userid"));
				qbVo.setNum(rs.getInt("num"));
				qbVo.setTitle(rs.getString("title"));
				qbVo.setContent(rs.getString("content"));
				qbVo.setWritedate(rs.getTimestamp("writedate"));
				qbVo.setReadcount(rs.getInt("readcount"));
				qbVo.setImgurl(rs.getString("imgurl"));
			}
		} catch (Exception e) {
			System.out.println("게시글 불러오기 오류(getBoardInfo(String num) error) : " + e);
		} finally {
			DBManager.close(conn, pstmt);
		}
		return qbVo;
	}

	public void deleteBoardByNum(String num) {
		String sql = "delete qnaboard where num = ?";
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

	public List<QnaBoardVO> getAllBoardList() {
		String sql = "select * from qnaboard order by writedate desc";
		QnaBoardVO qbVo = null;
		List<QnaBoardVO> list = new ArrayList<>();
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
				qbVo = new QnaBoardVO();
				System.out.println(rs.getString("userid"));
				qbVo.setUserid(rs.getString("userid"));
				qbVo.setNum(rs.getInt("num"));
				qbVo.setTitle(rs.getString("title"));
				qbVo.setContent(rs.getString("content"));
				qbVo.setWritedate(rs.getTimestamp("writedate"));
				qbVo.setReadcount(rs.getInt("readcount"));
				qbVo.setImgurl(rs.getString("imgUrl"));
				list.add(qbVo);
				System.out.println(2);
			}

		} catch (Exception e) {
			System.out.println("전체 qna게시판 리스트 로딩 오류: " + e);
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		System.out.println(list);
		return list;

	}
	
	//페이징을 위해 레코드 개수 세는 메소드
		public int getListCount() {
			
			int x = 0; //전체 글의 개수를 담을 변수
			String sql = "select count(*) from qnaboard";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					x = rs.getInt(1);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				DBManager.close(conn, pstmt, rs);
			}
			return x;
		}
		
		public List<QnaBoardVO> getBoardList(int page, int limit){
			//sql문 작성시 " " 공백 꼭 작성!
			String sql = "select * from "
					+ "(select rownum rnum, num, userid, "
					+ "title, content, imgUrl, "
					+ "readcount, writedate from "
					+ "(select * from qnaboard order by writedate desc)) "
					+ "where rnum >= ? and rnum <= ?";	
			
			//ex
			//page 1 → 1 ~ 10
			//page 2 → 11 ~ 20
			//page 3 → 21 ~ 30
			
			List<QnaBoardVO> list = new ArrayList<QnaBoardVO>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int startrow =  (page - 1) * 10 + 1;
			int endrow = startrow + limit - 1;
			
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startrow);
				pstmt.setInt(2, endrow);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					QnaBoardVO qbVo = new QnaBoardVO();				
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
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				DBManager.close(conn, pstmt, rs);
			}
			return list;

		}

}
