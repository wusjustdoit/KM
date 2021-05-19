package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.KMMemberDTO;
import service.MemberService;

@WebServlet("/deductPoint")
public class deductPoint extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public deductPoint() {
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
		
		int kmPoint = Integer.parseInt(request.getParameter("kmPoint"));
		String kmId = request.getParameter("kmId");
		
		KMMemberDTO member = new KMMemberDTO();
		member.setkmPoint(kmPoint);
		member.setkmId(kmId);
		System.out.println(member);
		
		MemberService mSvc = new MemberService();
		int result = mSvc.deductPoint(member);
		
		if(result>0) {
			response.sendRedirect("memberView?kmId="+kmId);
		} else {
			// 회원목록(memberList) servlet으로
			response.sendRedirect("memberList");
		}
		
		
	}

}
