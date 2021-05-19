package dao;

import static db.JdbcUtil.close;

import java.sql.*;
import java.util.ArrayList;

import dto.KMBoardDTO;

public class KMBDAO {

	private static KMBDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	private ArrayList<KMBoardDTO> bListC;

	public static KMBDAO getInstance() {
		if (dao == null) {
			dao = new KMBDAO();
		}

		return dao;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	// 게시글 작성
	public int boardWrite(KMBoardDTO board) {
		int result = 0;

		String sql = "INSERT INTO KM_BOARD VALUES(KMBNUM_SEQ.NEXTVAL,?,?,?,?,SYSDATE,0,?,?,0)";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, board.getkmbId());
			pstmt.setString(2, board.getkmbCategory());
			pstmt.setString(3, board.getkmbTitle());
			pstmt.setString(4, board.getkmbContents());
			pstmt.setString(5, board.getkmbFile());
			pstmt.setInt(6, board.getkmbPoint());
			result = pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 게시글 갯수
	public int ListCount() {
		int listCount = 0;
		String sql = "SELECT COUNT(*) FROM KM_BOARD";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return listCount;
	}
	
	// 게시판 보기
	public ArrayList<KMBoardDTO> BList(int startRow, int endRow) {
		ArrayList<KMBoardDTO> bList = new ArrayList<KMBoardDTO>();
		KMBoardDTO board = null;

		// BETWEEN [startRow] AND [endRow]
		String sql = "SELECT * FROM BLIST WHERE RN BETWEEN ? AND ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				board = new KMBoardDTO();

				board.setkmbNum(rs.getInt(1));
				board.setkmbId(rs.getString(2));
				board.setkmbCategory(rs.getString(3));
				board.setkmbTitle(rs.getString(4));
				board.setkmbContents(rs.getString(5));
				board.setkmbDate(rs.getDate(6));
				board.setkmbHits(rs.getInt(7));
				board.setkmbFile(rs.getString(8));
				board.setkmbPoint(rs.getInt(9));

				bList.add(board);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return bList;
	}
	// 신고 게시판 보기
	public ArrayList<KMBoardDTO> BList2(int startRow, int endRow) {
		ArrayList<KMBoardDTO> bList2 = new ArrayList<KMBoardDTO>();
		KMBoardDTO board = null;

		// BETWEEN [startRow] AND [endRow]
		String sql = "SELECT * FROM BLIST2 WHERE RNS BETWEEN ? AND ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				board = new KMBoardDTO();

				board.setkmbNum(rs.getInt(1));
				board.setkmbId(rs.getString(2));
				board.setkmbCategory(rs.getString(3));
				board.setkmbTitle(rs.getString(4));
				board.setkmbContents(rs.getString(5));
				board.setkmbDate(rs.getDate(6));
				board.setkmbHits(rs.getInt(7));
				board.setkmbFile(rs.getString(8));
				board.setkmbPoint(rs.getInt(9));

				bList2.add(board);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return bList2;
	}
	
	// 게시글 조회수
	public int boardHits(int kmbNum) {
		int result = 0;

		String sql = "UPDATE KM_BOARD SET KMBHITS=KMBHITS+1 WHERE KMBNUM=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, kmbNum);
			result = pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 게시글 보기
	public KMBoardDTO boardView(int kmbNum) {
		KMBoardDTO board = null;

		String sql = "SELECT * FROM KM_BOARD WHERE KMBNUM=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, kmbNum);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				board = new KMBoardDTO();
				board.setkmbNum(rs.getInt(1));
				board.setkmbId(rs.getString(2));
				board.setkmbCategory(rs.getString(3));
				board.setkmbTitle(rs.getString(4));
				board.setkmbContents(rs.getString(5));
				board.setkmbDate(rs.getDate(6));
				board.setkmbHits(rs.getInt(7));
				board.setkmbFile(rs.getString(8));
				board.setkmbPoint(rs.getInt(9));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return board;
	}
	// 게시글 수정
	public int boardModify(KMBoardDTO board) {
		int result = 0;

		String sql = "UPDATE KM_BOARD SET KMBTITLE=?, KMBCONTENTS=?, KMBFILE=?, KMBCATEGORY=?, KMBPOINT=? WHERE KMBNUM=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getkmbTitle());
			pstmt.setString(2, board.getkmbContents());
			pstmt.setString(3, board.getkmbFile());
			pstmt.setString(4, board.getkmbCategory());
			pstmt.setInt(5, board.getkmbPoint());
			pstmt.setInt(6, board.getkmbNum());
			result = pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	// 게시글 삭제
	public int boardDelete(int kmbNum) {
		int result = 0;

		String sql = "DELETE FROM KM_BOARD WHERE KMBNUM=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, kmbNum);
			result = pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	// 파일 다운로드(구매자 포인트 차감)
	public int filedown(KMBoardDTO board) {
		int result = 0;
		
		String sql = "UPDATE KM_MEMBER SET KMPOINT=(KMPOINT-?) WHERE KMID=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board.getkmbPoint());
			pstmt.setString(2, board.getkmbId());
			result = pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
			System.out.println(board);
		}
		return result;
	}
	// 파일 다운로드(작성자 포인트 증가)
	public int filedown2(KMBoardDTO board) {
		int result = 0;
		
		String sql = "UPDATE KM_MEMBER SET KMPOINT=(KMPOINT+?) WHERE KMID=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board.getkmbPoint());
			pstmt.setString(2, board.getkmbId());
			result = pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
			System.out.println(board);
		}
		return result;
	}
	
	// 게시물 신고
	public int boardSue(int kmbNum) {
		int result = 0;

		String sql = "UPDATE KM_BOARD SET KMBPROBLEM = '1' WHERE KMBNUM=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, kmbNum);
			result = pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	// 게시물 신고 철회(관리자용)
	public int boardRecover(int kmbNum) {
		int result = 0;

		String sql = "UPDATE KM_BOARD SET KMBPROBLEM = '0' WHERE KMBNUM=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, kmbNum);
			result = pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	

}
