package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.KMMemberDTO;
import service.MemberService;


@WebServlet("/memberView")
public class Mview extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Mview() {
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
		
		KMMemberDTO member = new KMMemberDTO();
		MemberService mSvc = new MemberService();
		
		member = mSvc.memberView(kmId);
		
		request.setAttribute("member", member);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("MView.jsp");
		dispatcher.forward(request, response);
	
	}
	

}
