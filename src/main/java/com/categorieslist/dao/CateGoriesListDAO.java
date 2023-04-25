package com.categorieslist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.categorieslist.dto.CateGoriesListVO;

import util.DBManager;



public class CateGoriesListDAO {

    private CateGoriesListDAO() {}

    private static CateGoriesListDAO instance = new CateGoriesListDAO();

    public static CateGoriesListDAO getInstance() {
        return instance;
    }
    
    
    private static final long CACHE_EXPIRATION_TIME = 300000; // 5분

    private ArrayList<CateGoriesListVO> cachedCateGoriesList;
    private long cacheCreatedAt;

    public ArrayList<CateGoriesListVO> hotDealList() {
        // 캐시가 존재하고, 만료 시간이 지나지 않았으면 캐시를 반환
        if (cachedCateGoriesList != null && System.currentTimeMillis() - cacheCreatedAt < CACHE_EXPIRATION_TIME) {
            return cachedCateGoriesList;
        }

        ArrayList<CateGoriesListVO> CateGoriesList = new ArrayList<>();

        String sql = "SELECT * " +
                "FROM ( " +
                "  SELECT * " +
                "  FROM PRODUCT " +
                "  ORDER BY dbms_random.value " +
                ") " +
                "WHERE rownum <= 3 " +
                "AND pname IS NOT NULL";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        CateGoriesListVO cgVo = null;
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                cgVo = new CateGoriesListVO();
                cgVo.setNum(rs.getInt("num"));
                cgVo.setbName(rs.getString("bName"));
                cgVo.setpGender(rs.getInt("pGender"));
                cgVo.setpName(rs.getString("pName"));
                cgVo.setKind(rs.getInt("kind"));
                cgVo.setPrice(rs.getInt("price"));
                cgVo.setImgUrl(rs.getString("imgUrl"));
                CateGoriesList.add(cgVo);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        
        // 현재 시간과 함께 캐시를 저장
        cachedCateGoriesList = CateGoriesList;
        cacheCreatedAt = System.currentTimeMillis();
        
        return CateGoriesList;
    }
    
    public long getCacheRemainingTime() {
        long elapsedTime = System.currentTimeMillis() - cacheCreatedAt;
        long remainingTime = CACHE_EXPIRATION_TIME - elapsedTime;
        return remainingTime > 0 ? remainingTime : 0;
    }


	
	public ArrayList<CateGoriesListVO> searchProducts(String pname) {
		ArrayList<CateGoriesListVO> CateGoriesList = new ArrayList<>();

		String sql = "SELECT num, pGender, bName, kind, pName, imgUrl, pSize, balance, price, purchasedNum, explain, writedate, readcount "
				+ "FROM (SELECT num, pGender, bName, kind, pName, imgUrl, pSize, balance, price, purchasedNum, explain, writedate, readcount, "
				+ " ROW_NUMBER() OVER (PARTITION BY pName ORDER BY num) RN " + " FROM PRODUCT "
				+ " WHERE pname LIKE '%' || ? || '%') " + "WHERE RN = 1";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CateGoriesListVO cgVo = null;
		try {

			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, pname);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				cgVo = new CateGoriesListVO();
				cgVo.setNum(rs.getInt("num"));
				cgVo.setbName(rs.getString("bName"));
				cgVo.setpGender(rs.getInt("pGender"));
				cgVo.setpName(rs.getString("pName"));
				cgVo.setKind(rs.getInt("kind"));
				cgVo.setPrice(rs.getInt("price"));
				cgVo.setImgUrl(rs.getString("imgUrl"));
				CateGoriesList.add(cgVo);

			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt, rs);

		}
		return CateGoriesList;
	}
	
	
	
	public ArrayList<CateGoriesListVO> ProductsList(String bname) {
		ArrayList<CateGoriesListVO> CateGoriesList = new ArrayList<>();

		String sql = "SELECT num, pGender, bName, kind, pName, imgUrl, pSize, balance, price, purchasedNum, explain, writedate, readcount "
				+ "FROM (SELECT num, pGender, bName, kind, pName, imgUrl, pSize, balance, price, purchasedNum, explain, writedate, readcount, "
				+ "      ROW_NUMBER() OVER (PARTITION BY pName ORDER BY num) RN " + "      FROM PRODUCT "
				+ "      WHERE bname LIKE ? ) " + "WHERE RN = 1";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CateGoriesListVO cgVo = null;
		try {

			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bname);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				cgVo = new CateGoriesListVO();
				cgVo.setNum(rs.getInt("num"));
				cgVo.setbName(rs.getString("bName"));
				cgVo.setpGender(rs.getInt("pGender"));
				cgVo.setpName(rs.getString("pName"));
				cgVo.setKind(rs.getInt("kind"));
				cgVo.setPrice(rs.getInt("price"));
				cgVo.setImgUrl(rs.getString("imgUrl"));
				CateGoriesList.add(cgVo);

			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt, rs);

		}
		return CateGoriesList;
	}

	public ArrayList<CateGoriesListVO> BcollecionProducts(String bname) {
		ArrayList<CateGoriesListVO> CateGoriesList = new ArrayList<>();

		String sql = "SELECT num, pGender, bName, kind, pName, imgUrl, pSize, balance, price, purchasedNum, explain, writedate, readcount "
				+ "FROM (SELECT num, pGender, bName, kind, pName, imgUrl, pSize, balance, price, purchasedNum, explain, writedate, readcount, "
				+ "      ROW_NUMBER() OVER (PARTITION BY pName ORDER BY num) RN " + "      FROM PRODUCT "
				+ "      WHERE bname LIKE ? AND kind = 3) " + "WHERE RN = 1";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CateGoriesListVO cgVo = null;
		try {

			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bname);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				cgVo = new CateGoriesListVO();
				cgVo.setNum(rs.getInt("num"));
				cgVo.setbName(rs.getString("bName"));
				cgVo.setpGender(rs.getInt("pGender"));
				cgVo.setpName(rs.getString("pName"));
				cgVo.setKind(rs.getInt("kind"));
				cgVo.setPrice(rs.getInt("price"));
				cgVo.setImgUrl(rs.getString("imgUrl"));
				CateGoriesList.add(cgVo);

			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt, rs);

		}
		return CateGoriesList;
	}

	public ArrayList<CateGoriesListVO> BottomProducts(String bname) {
		ArrayList<CateGoriesListVO> CateGoriesList = new ArrayList<>();

		String sql = "SELECT num, pGender, bName, kind, pName, imgUrl, pSize, balance, price, purchasedNum, explain, writedate, readcount "
				+ "FROM (SELECT num, pGender, bName, kind, pName, imgUrl, pSize, balance, price, purchasedNum, explain, writedate, readcount, "
				+ "      ROW_NUMBER() OVER (PARTITION BY pName ORDER BY num) RN " + "      FROM PRODUCT "
				+ "      WHERE bname LIKE ? AND kind = 2) " + "WHERE RN = 1";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CateGoriesListVO cgVo = null;
		try {

			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bname);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				cgVo = new CateGoriesListVO();
				cgVo.setNum(rs.getInt("num"));
				cgVo.setbName(rs.getString("bName"));
				cgVo.setpGender(rs.getInt("pGender"));
				cgVo.setpName(rs.getString("pName"));
				cgVo.setKind(rs.getInt("kind"));
				cgVo.setPrice(rs.getInt("price"));
				cgVo.setImgUrl(rs.getString("imgUrl"));
				CateGoriesList.add(cgVo);

			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt, rs);

		}
		return CateGoriesList;
	}

	public ArrayList<CateGoriesListVO> TopProducts(String bname) {
		ArrayList<CateGoriesListVO> CateGoriesList = new ArrayList<>();

		String sql = "SELECT num, pGender, bName, kind, pName, imgUrl, pSize, balance, price, purchasedNum, explain, writedate, readcount "
				+ "FROM (SELECT num, pGender, bName, kind, pName, imgUrl, pSize, balance, price, purchasedNum, explain, writedate, readcount, "
				+ "      ROW_NUMBER() OVER (PARTITION BY pName ORDER BY num) RN " + "      FROM PRODUCT "
				+ "      WHERE bname LIKE ? AND kind = 1) " + "WHERE RN = 1";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CateGoriesListVO cgVo = null;
		try {

			conn = DBManager.getConnection();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bname);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				cgVo = new CateGoriesListVO();
				cgVo.setNum(rs.getInt("num"));
				cgVo.setbName(rs.getString("bName"));
				cgVo.setpGender(rs.getInt("pGender"));
				cgVo.setpName(rs.getString("pName"));
				cgVo.setKind(rs.getInt("kind"));
				cgVo.setPrice(rs.getInt("price"));
				cgVo.setImgUrl(rs.getString("imgUrl"));
				CateGoriesList.add(cgVo);

			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBManager.close(conn, pstmt, rs);

		}
		return CateGoriesList;
	}

	public CateGoriesListVO selectOneCGByNum(String num) {
		String sql = "select * from product where num = ?";
		CateGoriesListVO cgVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cgVo = new CateGoriesListVO();
				cgVo.setNum(rs.getInt("num"));
				cgVo.setpName(rs.getString("pname"));
				cgVo.setImgUrl(rs.getString("imgurl"));
				cgVo.setPrice(rs.getInt("price"));
				cgVo.setpSize(rs.getString("psize"));
				cgVo.setKind(rs.getInt("kind"));
				cgVo.setpGender(rs.getInt("pgender"));
				cgVo.setWritedate(rs.getTimestamp("writedate"));
				cgVo.setPurchasedNum(rs.getInt("purchasednum"));
				cgVo.setExplain(rs.getString("explain"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return cgVo;
	}
}
