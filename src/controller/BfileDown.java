package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.KMBoardDTO;
import service.BoardService;

@WebServlet("/fileDown")
public class BfileDown extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BfileDown() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// 가져온 값 정의(로그인 아이디 kmbId)
		String kmbId = request.getParameter("kmbId");
		int kmbPoint = Integer.parseInt(request.getParameter("kmbPoint"));
		KMBoardDTO board = new KMBoardDTO();
		
		board.setkmbPoint(kmbPoint);
		board.setkmbId(kmbId);

		BoardService bSvc = new BoardService();
		
		int result = bSvc.filedown(board);
		
		if (result > 0) {
			System.out.println("차감 완료");
		} else {
			System.out.println("차감 실패");;
		}


		// 얻음
	}
}
