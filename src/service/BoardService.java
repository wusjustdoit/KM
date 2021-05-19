package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.KMBDAO;
import dto.KMBoardDTO;
import static dao.KMBDAO.*;
import static db.JdbcUtil.*;

public class BoardService {

	public int boardWrite(KMBoardDTO board) {
		KMBDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		int result = 0;

		result = dao.boardWrite(board);

		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		close(con);

		return result;

	}

	public int ListCount() {
		KMBDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		int listCount = dao.ListCount();

		close(con);

		return listCount;
	}

	public ArrayList<KMBoardDTO> BList(int startRow, int endRow) {
		KMBDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		ArrayList<KMBoardDTO> bList = dao.BList(startRow, endRow);

		close(con);

		return bList;
	}
	public ArrayList<KMBoardDTO> BList2(int startRow, int endRow) {
		KMBDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		ArrayList<KMBoardDTO> bList2 = dao.BList2(startRow, endRow);

		close(con);

		return bList2;
	}

	public KMBoardDTO boardView(int kmbNum) {
		KMBDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		int result = dao.boardHits(kmbNum);

		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		KMBoardDTO board = dao.boardView(kmbNum);

		close(con);

		return board;
	}

	public int boardModify(KMBoardDTO board) {
		KMBDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);

		int result = dao.boardModify(board);

		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		return result;
	}

	public int boardDelete(int kmbNum) {
		KMBDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int result = dao.boardDelete(kmbNum);
		
		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		return result;
	}

	public boolean filedown(String kmbId, int kmbPoint) {
		
		return false;
	}

	public int filedown(KMBoardDTO board) {
		KMBDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int result = dao.filedown(board);

		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		return result;
	}

	public int filedown2(KMBoardDTO board) {
		KMBDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int result = dao.filedown2(board);
		
		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		return result;
	}

	public int boardSue(int kmbNum) {
		KMBDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int result = dao.boardSue(kmbNum);
		
		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		return result;
	}

	public int boardRecover(int kmbNum) {
		KMBDAO dao = getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int result = dao.boardRecover(kmbNum);
		
		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}

		return result;
	}



}
