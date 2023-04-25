package com.brand.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.brand.dto.BrandVO;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;
import com.mysql.cj.xdevapi.DbDoc;
import com.product.dto.ProductVO;

import util.DBManager;

public class BrandDAO {
	private BrandDAO() {

	}

	private static BrandDAO instance = new BrandDAO();

	public static BrandDAO getInstance() {
		return instance;
	}
	
	public List<BrandVO> getAllBrandList(){
		String sql = "select * from brand order by bname";
		List<BrandVO> list = new ArrayList<>();
		BrandVO bVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bVo = new BrandVO();
				bVo.setbName(rs.getString("bname"));
				bVo.setImgUrl(rs.getString("imgUrl"));
				list.add(bVo);
			}
		} catch (Exception e) {
			System.out.println("브랜드 리스트 불러오기 오류(adminGetBrandList() error) : "+e);
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public void deleteBrand(String bname) {
		String sql = "delete brand where bname = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bname);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("브랜드 삭제 오류(deleteBrand(String bname) error) : "+e);
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
	   
	  public void brandWrite(BrandVO bVo) {
		  String sql = "insert into brand (bName, imgUrl) values (?, ?)";
		  Connection conn = null;
		  PreparedStatement pstmt = null;
		  try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bVo.getbName());
			pstmt.setString(2, bVo.getImgUrl());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("브랜드 등록 에러(brandWrite(BrandVO bVo) error):"+e);
		}finally {
			DBManager.close(conn, pstmt);
		}
	  }
}
