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

@WebServlet("/memberModifyForm")
public class MmodiForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MmodiForm() {
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
		
		// DB에서 한명(해당userId)의 회원정보 불러올 예정
		MemberService mSvc = new MemberService();
		KMMemberDTO member = new KMMemberDTO();
		
		member = mSvc.memberView(kmId);
		
		request.setAttribute("modify", member);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("MModify.jsp");
		dispatcher.forward(request, response);
	}

	
}
