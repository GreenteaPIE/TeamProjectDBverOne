package com.auction.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.auction.dto.AuctionVO;

import util.DBManager;

public class AuctionDAO {
	private AuctionDAO() {
		// TODO Auto-generated constructor stub
	}
	
	private static AuctionDAO instance = new AuctionDAO();
	
	public static AuctionDAO getInstance() {
		return instance;
	}
	
	public void insertAuction(AuctionVO aVo) {
		String sql = "insert into auction (userId, bName, pName, price, startPrice, endTime, onOff, pSize, imgUrl,num) values(?,?,?,?,?,?,?,?,?,auction_seq.nextval)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aVo.getUserId());
			pstmt.setString(2, aVo.getbName());
			pstmt.setString(3, aVo.getpName());
			pstmt.setInt(4, aVo.getPrice());
			pstmt.setInt(5, aVo.getStartPrice());
			pstmt.setTimestamp(6, aVo.getEndTime());
			pstmt.setInt(7, aVo.getOnOff());
			pstmt.setString(8, aVo.getpSize());
			pstmt.setString(9, aVo.getImgUrl());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("옥션 삽입 오류(insertAuction(AuctionVO aVo) error): "+e);
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	public List<AuctionVO> getAuctionList(){
		String sql = "select*from auction order by endTime desc, onOff desc";
		List<AuctionVO> auList = new ArrayList<>();
		AuctionVO auVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				auVo = new AuctionVO();
				auVo.setbName(rs.getString("bName"));
				auVo.setEndPrice(rs.getInt("endPrice"));
				auVo.setEndTime(rs.getTimestamp("endTime"));
				auVo.setImgUrl(rs.getString("imgUrl"));
				auVo.setOnOff(rs.getInt("onOff"));
				auVo.setpName(rs.getString("pName"));
				auVo.setPrice(rs.getInt("price"));
				auVo.setpSize(rs.getString("pSize"));
				auVo.setStartPrice(rs.getInt("startPrice"));
				auVo.setUserId(rs.getString("userId"));
				auVo.setNum(rs.getInt("num"));
				auList.add(auVo);
			}
		} catch (Exception e) {
			System.out.println("옥션 리스트 호출 오류(getAuctionList() error): "+e);
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return auList;
	}
	
	public AuctionVO getAuctionDetail(int num) {
		String sql = "select * from auction where num = ?";
		AuctionVO auVo = new AuctionVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				auVo.setbName(rs.getString("bName"));
				auVo.setEndPrice(rs.getInt("endPrice"));
				auVo.setEndTime(rs.getTimestamp("endTime"));
				auVo.setImgUrl(rs.getString("imgUrl"));
				auVo.setOnOff(rs.getInt("onOff"));
				auVo.setpName(rs.getString("pName"));
				auVo.setPrice(rs.getInt("price"));
				auVo.setpSize(rs.getString("pSize"));
				auVo.setStartPrice(rs.getInt("startPrice"));
				auVo.setUserId(rs.getString("userId"));
				auVo.setNum(rs.getInt("num"));
			}
		} catch (Exception e) {
			System.out.println("옥션 디테일 호출 오류(getAuctionDetail(int num) error): "+e);
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return auVo;
	}
	
	public void dealAuction(int num, int price, String loginUser) {
		String sql = "update auction set price=?,userId=? where num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, price);
			pstmt.setString(2, loginUser);
			pstmt.setInt(3, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("AuctionDAO dealAuction(int num, int price, String loginUser) error:"+e);
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	public void auctionComplete(int num) {
		String sql = "update auction set onOff=?where num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("AuctionDAO auctionComplete(int num) error:"+e);
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
}
