package com.purchased.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.cart.dto.CartVO;
import com.purchased.dto.PurchasedVO;

import util.DBManager;

public class PurchasedDAO {
	private PurchasedDAO() {
	}

	private static PurchasedDAO instance = new PurchasedDAO();

	public static PurchasedDAO getInstance() {
		return instance;
	}

	public void insertPurchased(CartVO cartVO, int orderNumber) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();

			String insertOrderDetail = "INSERT INTO purchased(orderDetailNumber, orderNumber, num, quantity) VALUES(order_detail_seq.NEXTVAL,?,?,?)";
			pstmt = conn.prepareStatement(insertOrderDetail);
			pstmt.setInt(1, orderNumber);
			pstmt.setInt(2, cartVO.getNum());
			pstmt.setInt(3, cartVO.getQuantity());
			pstmt.executeUpdate();
			pstmt.close();

			String updateCartResult = "UPDATE cart SET result=2 WHERE cartNum=?";
			pstmt = conn.prepareStatement(updateCartResult);
			pstmt.setInt(1, cartVO.getCartNum());
			pstmt.executeUpdate();

			System.out.println("insertOrderDetail (OrderDAO) 실행(+)");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("insertOrderDetail (OrderDAO) error : " + e);
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

}
