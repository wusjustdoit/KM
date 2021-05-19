package db;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;

public class JdbcUtil {

	// getConnection()메소드
	public static Connection getConnection() {
		
		Connection con = null;
		
		try {
			Context initCtx = new InitialContext();
			// context의 초기의 설정값이 담겨있는 객체를 생성한다.
			// 연결하고자 하는 DB가 아닌 기존 자바에 설정되어 있는 초기 설정값
			
			
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			// 새로 만든 context를 찾아간다.
			// "java:comp/env" 이 경로에 새로 만든 context들이 들어있다.
			
			
			
			DataSource ds = (DataSource)envCtx.lookup("jdbc/OracleDB");
			// ds : 새로 만든 context에서 name으로 지정한 속성값을 찾아서 DB에 connection 해준다.
			
			
			con = ds.getConnection();
			con.setAutoCommit(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return con;
	}
	
	
	// (1) con close
	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	// (2) stmt close
	public static void close(Statement stmt) {
		try {
			stmt.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	// (3) rs close
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	// (4) commit
	public static void commit(Connection con) {
		try {
			con.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	// (5) rollback
	public static void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
}






