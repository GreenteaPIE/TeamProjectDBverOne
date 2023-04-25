package com.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.cart.dto.CartVO;

import util.DBManager;

public class CartDAO {

	private CartDAO() {
	}

	private static CartDAO instance = new CartDAO();

	public static CartDAO getinstance() {
		return instance;
	}
	
	public ArrayList<CartVO> listCart2(String userId) { // 0419 purchasedDetail에서 쓸거에요 //
		ArrayList<CartVO> cartList = new ArrayList<>();
		String sql = "select * from cart where userId=? order by orderDate desc";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CartVO cartVO = new CartVO();
				cartVO.setCartNum(rs.getInt("cartNum"));
				cartVO.setUserId(rs.getString("userId"));
				cartVO.setNum(rs.getInt("num"));
				cartVO.setpSize(rs.getString("pSize"));
				cartVO.setQuantity(rs.getInt("quantity"));
				cartVO.setBalance(rs.getInt("balance"));
				cartVO.setOrderdate(rs.getTimestamp("orderdate"));
				cartList.add(cartVO);
			}

			System.out.println("cartList2 : " + cartList);

		} catch (Exception e) {
			System.out.println("ArrayList<CartVO> cartList2 에러 : " + e);
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return cartList;

	}

	public void deleteCart(int cartNum) { // 0414 15:20
		System.out.println("deleteCart DAO test");
		String sql = "delete cart where cartNum=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cartNum);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("deleteCart 에러 : " + e);
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public void insertCart(CartVO cartVO) { // 0414 정자윤 변경사항
		String sql = "insert into cart(cartNum, userId, num, pSize, quantity, balance)"
				+ " values(cart_seq.nextval,?, ?, ?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cartVO.getUserId());
			pstmt.setInt(2, cartVO.getNum());
			pstmt.setString(3, cartVO.getpSize());
			pstmt.setInt(4, cartVO.getQuantity());
			pstmt.setInt(5, cartVO.getBalance());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("insertCart 에러 : " + e);
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public ArrayList<CartVO> listCart(String userId) { // 0414 정자윤 변경사항
		ArrayList<CartVO> cartList = new ArrayList<>();
		String sql = "select * from cart where userId=? and result ='1' order by orderDate desc";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CartVO cartVO = new CartVO();
				cartVO.setCartNum(rs.getInt("cartNum"));
				cartVO.setUserId(rs.getString("userId"));
				cartVO.setNum(rs.getInt("num"));
				cartVO.setpSize(rs.getString("pSize"));
				cartVO.setQuantity(rs.getInt("quantity"));
				cartVO.setBalance(rs.getInt("balance"));
				cartVO.setOrderdate(rs.getTimestamp("orderdate"));
				cartList.add(cartVO);
			}

			System.out.println("cartList : " + cartList);

		} catch (Exception e) {
			System.out.println("ArrayList<CartVO> cartList 에러 : " + e);
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return cartList;

	}

}
