package com.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.product.dto.ProductVO;

import util.DBManager;

public class ProductDAO {

	private ProductDAO() {
	}

	private static ProductDAO instance = new ProductDAO();

	public static ProductDAO getInstance() {
		return instance;
	}

	public ProductVO getProduct(int num) { /* 상세보기 */
		ProductVO product = null;
		Connection conn = null;
		String sql = "select*from product where num=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				product = new ProductVO();
				product.setNum(rs.getInt("num"));
				product.setpGender(rs.getInt("pGender"));
				product.setbName(rs.getString("bName"));
				product.setKind(rs.getInt("kind"));
				product.setpName(rs.getString("pName"));
				product.setImgUrl(rs.getString("imgUrl"));
				product.setPrice(rs.getInt("price"));
				product.setpSize(rs.getString("pSize"));
				product.setExplain(rs.getString("explain"));
				product.setReadcount(rs.getInt("readcount"));
			}

			System.out.println("View 페이지에서 Product : " + product);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt, rs);

		}
		return product;
	}

	public ArrayList<ProductVO> selectAllProducts() {
		ArrayList<ProductVO> ProductList = new ArrayList<>();
		String sql = "select * from Product order by writedate desc";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductVO pVo = new ProductVO();
				pVo.setNum(rs.getInt("num"));
				pVo.setbName(rs.getString("bName"));
				pVo.setpGender(rs.getInt("pGender"));
				pVo.setpName(rs.getString("pName"));
				pVo.setKind(rs.getInt("kind"));
				pVo.setpSize(rs.getString("pSize"));
				pVo.setPrice(rs.getInt("price"));
				pVo.setBalance(rs.getInt("balance"));
				pVo.setWritedate(rs.getTimestamp("writedate"));
				pVo.setReadcount(rs.getInt("readcount"));

				ProductList.add(pVo);
			}

			System.out.println("ProductList : " + ProductList);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt, rs);

		}
		return ProductList;
	}

	public void insertProduct(ProductVO pVo) {
		String sql = "INSERT INTO product(num, pGender, bName, kind, pName, imgUrl, pSize, price, balance, explain) "
				+ "VALUES (product_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pVo.getpGender());
			pstmt.setString(2, pVo.getbName());
			pstmt.setInt(3, pVo.getKind());
			pstmt.setString(4, pVo.getpName());
			pstmt.setString(5, pVo.getImgUrl());
			pstmt.setString(6, pVo.getpSize());
			pstmt.setInt(7, pVo.getPrice());
			pstmt.setInt(8, pVo.getBalance());
			pstmt.setString(9, pVo.getExplain());
			pstmt.executeQuery();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public void deleteProduct(int num) { // 상품 삭제 2023-04-14 12:49 추가 by 종민
		String sql = "delete product where num = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			System.out.println("삭제 완료");
		} catch (Exception e) {
			System.out.println("제품 삭제 오류(deleteProduct(int num) error): " + e);
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public ProductVO getProductByNameSize(String pName, String pSize) { // 상품이름과 사이즈로 정보 불러오기 2023-04-14 12:49 추가 by 종민
		String sql = "select*from product where pName=? and pSize = ?";
		ProductVO product = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pName);
			pstmt.setString(2, pSize);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				product = new ProductVO();
				product.setNum(rs.getInt("num"));
				product.setpGender(rs.getInt("pGender"));
				product.setbName(rs.getString("bName"));
				product.setKind(rs.getInt("kind"));
				product.setpName(rs.getString("pName"));
				product.setImgUrl(rs.getString("imgUrl"));
				product.setPrice(rs.getInt("price"));
				product.setpSize(rs.getString("pSize"));
				product.setExplain(rs.getString("explain"));
				product.setReadcount(rs.getInt("readcount"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return product;
	}

	public ProductVO getProductByName(String pName) { // 상품이름으로 정보 불러오기 2023-04-14 10:27 추가 by 종민
		String sql = "select*from product where pName=?";
		ProductVO product = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				product = new ProductVO();
				product.setNum(rs.getInt("num"));
				product.setpGender(rs.getInt("pGender"));
				product.setbName(rs.getString("bName"));
				product.setKind(rs.getInt("kind"));
				product.setpName(rs.getString("pName"));
				product.setImgUrl(rs.getString("imgUrl"));
				product.setPrice(rs.getInt("price"));
				product.setpSize(rs.getString("pSize"));
				product.setExplain(rs.getString("explain"));
				product.setReadcount(rs.getInt("readcount"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt, rs);

		}
		return product;
	}

	public ArrayList<ProductVO> selectAllProductsNotDuplicated() { // 중복 삭제한 리스트 2023-04-14 9:53 추가 by 종민
		ArrayList<ProductVO> ProductList = new ArrayList<>();
		String sql = "SELECT num, pGender, bName, kind, pName, imgUrl, pSize, balance, price, purchasedNum, explain, writedate, readcount "
				+ "FROM (SELECT num, pGender, bName, kind, pName, imgUrl, pSize, balance, price, purchasedNum, explain, writedate, readcount,"
				+ "ROW_NUMBER() OVER (PARTITION BY pName, price ORDER BY num) RN FROM PRODUCT) WHERE RN = 1";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductVO pVo = new ProductVO();
				pVo.setNum(rs.getInt("num"));
				pVo.setbName(rs.getString("bName"));
				pVo.setpGender(rs.getInt("pGender"));
				pVo.setpName(rs.getString("pName"));
				pVo.setKind(rs.getInt("kind"));
				pVo.setpSize(rs.getString("pSize"));
				pVo.setPrice(rs.getInt("price"));
				pVo.setBalance(rs.getInt("balance"));
				pVo.setWritedate(rs.getTimestamp("writedate"));
				pVo.setReadcount(rs.getInt("readcount"));
				pVo.setPurchasedNum(rs.getInt("purchasedNum"));
				pVo.setExplain(rs.getString("explain"));
				ProductList.add(pVo);
			}

			// System.out.println("ProductList : " + ProductList);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt, rs);

		}
		return ProductList;
	}

	public void productEdit(ProductVO pVo) { // 2023-04-13 15:45 추가 by 종민
		String sql = "update product set pName = ?, pGender=?, kind=?, bName=?, price=?, pSize=?, balance=?,explain=?, imgUrl=? where num = ? ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pVo.getpName());
			pstmt.setInt(2, pVo.getpGender());
			pstmt.setInt(3, pVo.getKind());
			pstmt.setString(4, pVo.getbName());
			pstmt.setInt(5, pVo.getPrice());
			pstmt.setString(6, pVo.getpSize());
			pstmt.setInt(7, pVo.getBalance());
			pstmt.setString(8, pVo.getExplain());
			pstmt.setString(9, pVo.getImgUrl());
			pstmt.setInt(10, pVo.getNum());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("제품 수정 오류(productEdit(ProductVO pVo) error): " + e);
		} finally {
			DBManager.close(conn, pstmt);
		}

	}
	   public List<ProductVO> getProductSizeList(String pName) { // 2023-04-14 추가 by 종민
		      String sql = "select psize from product where pname = ?";
		      ProductVO pVo = null;
		      List<ProductVO> sList = new ArrayList<>();
		      Connection conn = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      try {
		         conn = DBManager.getConnection();
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, pName);
		         rs = pstmt.executeQuery();
		         while (rs.next()) {
		            pVo = new ProductVO();
		            pVo.setpSize(rs.getString("psize"));
		            sList.add(pVo);
		         }
		      } catch (Exception e) {
		         System.out.println("사이즈 불러오기 오류(getProductSizeList(String pName) error) : " + e);
		      } finally {
		         DBManager.close(conn, pstmt, rs);
		      }
		      return sList;
		   }
}
