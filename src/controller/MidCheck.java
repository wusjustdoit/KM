package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

@WebServlet("/idCheck")
public class MidCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MidCheck() {
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
		
		String kmId = request.getParameter("kmId");
		
		MemberService mSvc = new MemberService();
		String checkId = mSvc.idCheck(kmId);
		
		
		if(checkId==null) {
			request.setAttribute("kmId",kmId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("JoinFormS.jsp");
			dispatcher.forward(request, response);
			
		} else {
			response.sendRedirect("JoinFormF.jsp");
		}
		
	}	
}








