package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;

@WebServlet("/boardRecover")
public class boardRecover extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public boardRecover() {
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

		BoardService bSvc = new BoardService();
		
		int result = bSvc.boardRecover(kmbNum);
		
		
		if(result>0) {
			response.sendRedirect("Success.jsp");
		} else {
			response.sendRedirect("index.jsp");
		}
	}

}
