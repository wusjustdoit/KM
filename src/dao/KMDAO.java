package dao;

import java.sql.*;
import java.util.ArrayList;

import static db.JdbcUtil.*;
import dto.KMMemberDTO;

public class KMDAO {

	private static KMDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static KMDAO getInstance() {
		if(dao==null) {
			dao = new KMDAO();
		}
		
		return dao;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	
	// 아이디 중복체크 메소드 idCheck()
	public String idCheck(String kmId) {
		String checkId = null;

		String sql = "SELECT KMID FROM KM_MEMBER WHERE KMID=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, kmId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				checkId = rs.getString(1);
			}
			
			
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
		return checkId;
	}

	// 회원가입 메소드 memberJoin()
	public int memberJoin(KMMemberDTO member) {
		int result = 0;
		
		String sql="INSERT INTO KM_MEMBER VALUES(?,?,?,?,?,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getkmId());
			pstmt.setString(2, member.getkmPw());
			pstmt.setString(3, member.getkmName());
			pstmt.setString(4, member.getkmBirth());
			pstmt.setString(5, member.getkmGender());
			pstmt.setString(6, member.getkmEmail());
			pstmt.setString(7, member.getkmFile());
			pstmt.setInt(8, member.getkmPoint());
			
			result = pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	// 로그인 메소드
	public String memberLogin(String kmId, String kmPw) {
		String loginId = null;
		String sql="SELECT KMID FROM KM_MEMBER WHERE KMID=? AND KMPW=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, kmId);
			pstmt.setString(2, kmPw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loginId = rs.getString(1);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return loginId;
	}

	// 전체 회원수를 구하는 메소드 mListCount()
	public int mListCount() {
		int listCount = 0;
		String sql ="SELECT COUNT(*) FROM KM_MEMBER";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
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
	// 멤버 리스트
	public ArrayList<KMMemberDTO> memberList(int startRow, int endRow) {
		ArrayList<KMMemberDTO> mList = new ArrayList<KMMemberDTO>();
		KMMemberDTO member = null;
		
		String sql = "SELECT KMID,KMNAME,TO_CHAR(KMBIRTH,'YYYY-MM-DD'), KMPOINT FROM KM_BMLIST WHERE RN BETWEEN ? AND ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member = new KMMemberDTO();
				
				member.setkmId(rs.getString(1));
				member.setkmName(rs.getString(2));
				member.setkmBirth(rs.getString(3));
				member.setkmPoint(rs.getInt(4));
				
				
				mList.add(member);
			
			}
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return mList;
	}

	// 회원정보 상세보기 메소드 memberView()
	public KMMemberDTO memberView(String kmId) {
KMMemberDTO member = null;
		
		String sql ="SELECT KMID, KMPW, KMNAME, TO_CHAR(KMBIRTH,'YYYY-MM-DD'), KMGENDER, KMEMAIL, KMFILE, KMPOINT FROM KM_MEMBER WHERE KMID=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, kmId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new KMMemberDTO();
				member.setkmId(rs.getString(1));
				member.setkmPw(rs.getString(2));
				member.setkmName(rs.getString(3));
				member.setkmBirth(rs.getString(4));
				member.setkmGender(rs.getString(5));
				member.setkmEmail(rs.getString(6));
				member.setkmFile(rs.getString(7));
				member.setkmPoint(rs.getInt(8));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		System.out.println(member);
		return member;

	}

	// 회원탈퇴 메소드 memberDelete()
	public int memberDelete(String kmId) {
int result = 0;
		
		String sql="DELETE FROM KM_MEMBER WHERE KMID=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, kmId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int memberModify(KMMemberDTO member) {
		int result = 0;
		String sql="UPDATE KM_MEMBER SET KMPW=?, KMNAME=?, KMBIRTH=?, "
				 + "KMGENDER=?, KMEMAIL=?, KMFILE=? WHERE KMID=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getkmPw());
			pstmt.setString(2, member.getkmName());
			pstmt.setString(3, member.getkmBirth());
			pstmt.setString(4, member.getkmGender());
			pstmt.setString(5, member.getkmEmail());
			pstmt.setString(6, member.getkmFile());
			pstmt.setString(7, member.getkmId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	// KM Point 충전
	public int chargePoint(KMMemberDTO member) {
		int result =0;
		String sql = "UPDATE KM_MEMBER SET KMPOINT =(KMPOINT+ ? ) WHERE KMID=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member.getkmPoint());
			pstmt.setString(2, member.getkmId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	// KM Point 감소
	public int deductPoint(KMMemberDTO member) {
		int result =0;
		String sql = "UPDATE KM_MEMBER SET KMPOINT =(KMPOINT- ? ) WHERE KMID=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member.getkmPoint());
			pstmt.setString(2, member.getkmId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
