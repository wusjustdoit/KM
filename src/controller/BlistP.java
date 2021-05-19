package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.KMBoardDTO;
import dto.KMPageDTO;
import service.BoardService;

@WebServlet("/BlistP")
public class BlistP extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BlistP() {
        super();
        // TODO Auto-generated constructor stub
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
		
		// 페이지의 초기값을 1로 설정
		int page = 1;

		// 한페이지에 5개의 게시글만 보이게 초기값 설정.
		int limit = 5;
		
		
		// page 정보를 받을 수 있다면 page값 설정
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		// limit 정보를 받을 수 있다면 limit값 설정
		if (request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}

		// page 정보를 받을수 없다면 초기값 1을 사용
		// limit 정보를 받을수 없다면 초기값 5를 사용

		// PageService만들기(service 패키지)
		BoardService bSvc = new BoardService();

		// 전체 게시글 갯수를 가져오기 위한 PageService의 ListCount()메소드 호출
		
		int listCount = bSvc.ListCount();
		// pageSvc.ListCount() = 15;
		// listCount = 15;

		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;

		ArrayList<KMBoardDTO> bList2 = bSvc.BList2(startRow, endRow);

		// 최대로 필요한 페이지 갯수 계산
		// 6
		int maxPage = (int) ((double) listCount / limit + 0.9);

		// 현재 페이지에 보여줄 시작페이지
		// 1, 11, 21
		int startPage = (((int) ((double) page / 5 + 0.9)) - 1) * 5 + 1;

		// 현재 페이지에 보여줄 마지막페이지
		int endPage = startPage + 5 - 1;

		if (endPage > maxPage) {
			endPage = maxPage;
		}

		KMPageDTO paging = new KMPageDTO();
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		paging.setListCount(listCount);
		paging.setLimit(limit);

		request.setAttribute("paging", paging);
		request.setAttribute("bList", bList2);

		RequestDispatcher dispatcher = request.getRequestDispatcher("boardProblem.jsp");
		dispatcher.forward(request, response);
		
		
		
		
		
	}

}
