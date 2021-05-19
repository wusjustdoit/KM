package service;

import java.sql.Connection;
import java.util.ArrayList;

import dao.KMDAO;
import dto.KMMemberDTO;

import static dao.KMDAO.*;
import static db.JdbcUtil.*;

public class MemberService {

	// 아이디 중복체크 메소드 idCheck()
	public String idCheck(String kmId) {
		KMDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		String checkId = dao.idCheck(kmId);

		return checkId;
	}

	// 회원가입 메소드 memberJoin()
	public int memberJoin(KMMemberDTO member) {
		KMDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		int result = dao.memberJoin(member);

		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return result;
	}

	// 로그인 메소드 memberLogin()
	public String memberLogin(String kmId, String kmPw) {
		KMDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		String loginId = dao.memberLogin(kmId, kmPw);

		close(con);

		return loginId;
	}

	// 전체 회원수를 구하는 메소드 mListCount()
	public int mListCount() {
		KMDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		int listCount = dao.mListCount();

		close(con);

		return listCount;
	}

	// 회원목록 메소드 memberList()
	public ArrayList<KMMemberDTO> memberList(int startRow, int endRow) {
		KMDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<KMMemberDTO> mList = dao.memberList(startRow, endRow);
		
		close(con);		
		
		return mList;
	}

	// 회원정보 상세보기 메소드 memberView()
	public KMMemberDTO memberView(String kmId) {
		KMDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		KMMemberDTO member = dao.memberView(kmId);
		
		close(con);
		
		return member;
		
	}

	// 회원탈퇴 메소드 memberDelete()
	public int memberDelete(String kmId) {
		KMDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int result = dao.memberDelete(kmId);
		
		if(result>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}
	// 회원수정 메소드 memberModify()
	public int memberModify(KMMemberDTO member) {
		KMDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int result = dao.memberModify(member);
		
		if(result>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
		
	}
	// KM Point 충전
	public int chargePoint(KMMemberDTO member) {
		KMDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int result = dao.chargePoint(member);
		
		if(result>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		
		return result;
	}

	public int deductPoint(KMMemberDTO member) {
		KMDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int result = dao.deductPoint(member);
		
		if(result>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		
		return result;
	}

}
