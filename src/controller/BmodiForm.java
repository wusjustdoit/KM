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

@WebServlet("/boardModifyForm")
public class BmodiForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BmodiForm() {
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
		
		int kmbNum = Integer.parseInt(request.getParameter("kmbNum"));
		
		BoardService bScv = new BoardService();
		KMBoardDTO board = new KMBoardDTO();
		
		board = bScv.boardView(kmbNum);
		request.setAttribute("modify", board);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("BModify.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
