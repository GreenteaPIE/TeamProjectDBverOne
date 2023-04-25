package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager { // 연결과 닫는 유틸
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			// jdbc/myoracle 이란 이름의 객체를 찾아서 datasource를 받는다.
			DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
			// ds가 생성되었음으로 Connection을 구함.
			conn = ds.getConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// select을 수행한 후 리소스 해제를 위한 메소드
	public static void close(Connection conn, Statement stmt, ResultSet rs) { // pstmt 가 stmt 를 상속받아 따로 작성할필요 없음
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// DML(insert, update, delete)을 수행한 후 리소스 해제를 위한 메소드
	public static void close(Connection conn, Statement stmt) { // pstmt 가 stmt 를 상속받아 따로 작성할필요 없음
		try {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
