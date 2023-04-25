package com.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cart.dto.CartVO;
import com.categorieslist.dto.CateGoriesListVO;
import com.order.dto.OrderVO;

import util.DBManager;

public class OrderDAO {

	private OrderDAO() {

	}

	private static OrderDAO instance = new OrderDAO();

	public static OrderDAO getInstance() {
		return instance;
	}
	
	public List<OrderVO> selectOrderView(String orderNumber) {
		List<OrderVO> orderView = new ArrayList<>();
		String sql = "select * from order_view where ordernumber=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderNumber);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				OrderVO oVo = new OrderVO();
				oVo.setUserid(rs.getString("userid"));
				oVo.setIndate(rs.getTimestamp("indate"));
				oVo.setOrderNumber(rs.getInt("orderNumber"));
				oVo.setOrderDetailNumber(rs.getInt("orderDetailNumber"));
				oVo.setNum(rs.getInt("num"));
				oVo.setQuantity(rs.getInt("quantity"));
				oVo.setResult(rs.getString("result"));
				orderView.add(oVo);
			}

			System.out.println("selectOrderView : " + orderView);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("selectOrderView (OrderDAO)  error : " + e);
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return orderView;
	}
	

	public ArrayList<OrderVO> selectAllOderDetail() {
		ArrayList<OrderVO> OderList = new ArrayList<>();
		String sql = "SELECT MAX(orderDetailNumber) AS orderDetailNumber, orderNumber, MAX(totalPrice) AS totalPrice " +
	             "FROM order_detail " +
	             "WHERE (orderNumber, totalPrice) IN (" +
	             "SELECT orderNumber, MAX(totalPrice) " +
	             "FROM order_detail " +
	             "GROUP BY orderNumber " +
	             ") " +
	             "GROUP BY orderNumber, totalPrice " +
	             "ORDER BY orderDetailNumber DESC";


		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); 
			
			while (rs.next()) {
				OrderVO oVo = new OrderVO();
				oVo.setOrderDetailNumber(rs.getInt("orderdetailnumber"));
				oVo.setOrderNumber(rs.getInt("ordernumber"));
				oVo.setTotalprice(rs.getInt("totalprice"));
				
				
				OderList.add(oVo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return OderList;

	}

	public ArrayList<OrderVO> selectAllOders() {
		ArrayList<OrderVO> OderList = new ArrayList<>();
		String sql = "select * from orders order by indate desc";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); 
			
			while (rs.next()) {
				OrderVO oVo = new OrderVO();
				oVo.setOrderNumber(rs.getInt("ordernumber"));
				oVo.setUserid(rs.getString("userid"));
				oVo.setIndate(rs.getTimestamp("indate"));
				
				OderList.add(oVo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return OderList;

	}

	public int hotDealInsertOrder(CateGoriesListVO dealproduct, String userid, int total) {
		int maxOrderNumber = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs;

		try {

			conn = DBManager.getConnection();
			String selectMaxOrderNumber = "select max(orderNumber) from orders";
			pstmt = conn.prepareStatement(selectMaxOrderNumber);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				maxOrderNumber = rs.getInt(1);
			}
			pstmt.close();

			String insertOrder = "insert into orders(orderNumber,userid) values(" + "orders_seq.nextval,?)";
			pstmt = conn.prepareStatement(insertOrder);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
			hotDealInsertOrderDetail(dealproduct, maxOrderNumber + 1, total);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("insertOrder(OrderDAO) error : " + e);
		} finally {
			DBManager.close(conn, pstmt);
		}
		return maxOrderNumber + 1;
	}

	public void hotDealInsertOrderDetail(CateGoriesListVO cgVo, int maxOrderNumber, int total) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();

			String insertOrderDetail = "insert into order_detail(orderDetailNumber, orderNumber,"
					+ "num, TOTALPRICE, QUANTITY ) values(order_detail_seq.nextval,?,?,?,?)";
			pstmt = conn.prepareStatement(insertOrderDetail);
			pstmt.setInt(1, maxOrderNumber);
			pstmt.setInt(2, cgVo.getNum());
			pstmt.setDouble(3, total);
			pstmt.setInt(4, 1);
			pstmt.executeUpdate();
			pstmt.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("insertOrderDetail (OrderDAO) error : " + e);
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public int insertOrder(ArrayList<CartVO> listCart, String userid, int total) {
		int maxOrderNumber = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs;

		try {

			conn = DBManager.getConnection();
			String selectMaxOrderNumber = "select max(orderNumber) from orders";
			pstmt = conn.prepareStatement(selectMaxOrderNumber);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				maxOrderNumber = rs.getInt(1);
			}

			pstmt.close();

			String insertOrder = "insert into orders(orderNumber,userid) values(" + "orders_seq.nextval,?)";
			pstmt = conn.prepareStatement(insertOrder);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();

			for (CartVO cartVO : listCart) {
				insertOrderDetail(cartVO, maxOrderNumber + 1, total);
			}

			System.out.println("insertOrder (OrderDAO) 실행(+)");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("insertOrder(OrderDAO) error : " + e);
		} finally {
			DBManager.close(conn, pstmt);
		}
		return maxOrderNumber + 1;
	}

	public void insertOrderDetail(CartVO cartVO, int maxOrderNumber, int total) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();

			String insertOrderDetail = "insert into order_detail(orderDetailNumber, orderNumber,"
					+ "num, quantity, TOTALPRICE) values(order_detail_seq.nextval,?,?,?,?)";
			pstmt = conn.prepareStatement(insertOrderDetail);
			pstmt.setInt(1, maxOrderNumber);
			pstmt.setInt(2, cartVO.getNum());
			pstmt.setInt(3, cartVO.getQuantity());
			pstmt.setInt(4, total);
			pstmt.executeUpdate();
			pstmt.close();

			String updateCartResult = "update cart set result=2 where cartNum=?";
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

	public ArrayList<OrderVO> listOrderById(String userid, String result, int orderNumber) {
		ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
		String sql = "SELECT * FROM order_view WHERE userid = ? "
				+ "AND result LIKE '%' || ? || '%' AND orderNumber = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, result);
			pstmt.setInt(3, orderNumber);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OrderVO orderVO = new OrderVO();
				orderVO.setOrderDetailNumber(rs.getInt("orderDetailNumber"));
				System.out.println("listOrderById ~~~~~~~~~~~~~~~~~~~");
				System.out.println("orderDetailNumber : " + rs.getInt("orderDetailNumber"));

				orderVO.setOrderNumber(rs.getInt("orderNumber"));
				System.out.println("orderNumber : " + rs.getInt("orderNumber"));

				orderVO.setUserid(rs.getString("userid"));
				System.out.println("userid : " + rs.getString("userid"));

				orderVO.setIndate(rs.getTimestamp("indate"));
				System.out.println("indate : " + rs.getTime("indate"));

//				orderVO.setName(rs.getString("name"));
//				System.out.println("name : " + rs.getString("name"));

//				orderVO.setZipcode(rs.getString("zipcode"));
//				System.out.println("zipcode : " + rs.getString("zipcode"));

//				orderVO.setAddress1(rs.getString("address1"));
//				System.out.println("address1 : " + rs.getString("address1"));

//				orderVO.setAddress2(rs.getString("address2"));
//				System.out.println("address2 : " + rs.getString("address2"));

//				orderVO.setPhone(rs.getString("phone"));
//				System.out.println("phone : " + rs.getString("phone"));

				orderVO.setNum(rs.getInt("num"));
				System.out.println("num : " + rs.getInt("num"));

				orderVO.setQuantity(rs.getInt("quantity"));
				System.out.println("quantity : " + rs.getInt("quantity"));

//				orderVO.setbName(rs.getString("bName"));
//				System.out.println("bName: " + rs.getString("bName"));

//				orderVO.setpName(rs.getString("pName"));
//				System.out.println("pName : " + rs.getString("pName"));

//				orderVO.setPrice(rs.getInt("price"));
//				System.out.println("price : " + rs.getInt("price"));

				orderVO.setResult(rs.getString("result"));
				System.out.println("result : " + rs.getString("result"));

				orderList.add(orderVO);
				System.out.println(orderList);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ArrayList<OrderVO> listOrderById (OrderDAO) error : " + e);
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return orderList;
	}

	public ArrayList<Integer> selectSeqOrdering(String userid) {
		ArrayList<Integer> orderNumberList = new ArrayList<Integer>();
		String sql = "select distinct orderNumber from order_view "
				+ "where userid=? and result='1' order by orderNumber desc";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				orderNumberList.add(rs.getInt(1));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ArrayList<Integer> selectSeqOrdering (OrderDAO) error : " + e);
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return orderNumberList;

	}


}
