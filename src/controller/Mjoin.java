package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.KMMemberDTO;
import service.MemberService;

@WebServlet("/memberJoin")
public class Mjoin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Mjoin() {
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

		// 파일 업로드 관련 설정
		int size = 10 * 1024 * 1024;
		String savePath = "G:/programming/JavaWorkspace/KM/WebContent/fileUpload";
		MultipartRequest multi = new MultipartRequest(request, savePath, size, "UTF-8", new DefaultFileRenamePolicy());

		// jsp에서 받아온 데이터 설정
		String kmId 	   = multi.getParameter("kmId");
		String kmPw 	   = multi.getParameter("kmPw");
		String kmName   = multi.getParameter("kmName");
		String kmBirth  = multi.getParameter("kmBirth");
		String kmGender = multi.getParameter("kmGender");
		String kmEmail  = multi.getParameter("kmEmail");
		String kmFile   = multi.getFilesystemName((String) multi.getFileNames().nextElement());

		// 받아온 데이터 MemberDTO타입의 member에 set
		KMMemberDTO member = new KMMemberDTO();
		member.setkmId(kmId);
		member.setkmPw(kmPw);
		member.setkmName(kmName);
		member.setkmBirth(kmBirth);
		member.setkmGender(kmGender);
		member.setkmEmail(kmEmail);
		member.setkmFile(kmFile);

		// member정보를 service로 이동
		MemberService mSvc = new MemberService();
		int result = mSvc.memberJoin(member);
		
		if(result>0) {
			response.sendRedirect("Success.jsp");
		} else {
			response.sendRedirect("JoinForm.jsp");
		}

	}

}
