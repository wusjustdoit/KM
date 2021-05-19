package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.KMBoardDTO;
import service.BoardService;


@WebServlet("/boardView")
public class Bview extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Bview() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// reqeust.getParameter(""); 는 String값으로 받아온다.
		// Integer.parseInt() : 문자를 숫자로 변환하는 메소드
		int kmbNum = Integer.parseInt(request.getParameter("kmbNum"));
		int page = 1;
		
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		KMBoardDTO board = new KMBoardDTO();
		
		BoardService bSvc = new BoardService();
		board = bSvc.boardView(kmbNum);
		
		request.setAttribute("board", board);
		request.setAttribute("page", page);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("BView.jsp");
		dispatcher.forward(request, response);
		
		
		
		
		
		
		
	}

}
