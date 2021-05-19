package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.KMBoardDTO;
import service.BoardService;


@WebServlet("/boardModify")
public class Bmodify extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Bmodify() {
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

		int size = 10 * 1024 * 1024;

		String savePath = "G:/programming/JavaWorkspace/KM/WebContent/fileUpload";

		MultipartRequest multi = new MultipartRequest(request, // 담아온 정보
				savePath, // 저장경로
				size, // 파일크기
				"UTF-8", // 인코딩 방식
				new DefaultFileRenamePolicy() // 중복 파일 이름바꾸기
		);

		int kmbNum = Integer.parseInt(multi.getParameter("kmbNum"));
		String kmbCategory = multi.getParameter("kmbCategory");
		int kmbPoint = Integer.parseInt(multi.getParameter("kmbPoint"));
		String kmbTitle = multi.getParameter("kmbTitle");
		String kmbContents = multi.getParameter("kmbContents");
		String kmbFile = multi.getFilesystemName((String)multi.getFileNames().nextElement());

		KMBoardDTO board = new KMBoardDTO();

		board.setkmbNum(kmbNum);
		board.setkmbCategory(kmbCategory);
		board.setkmbTitle(kmbTitle);
		board.setkmbContents(kmbContents);
		board.setkmbFile(kmbFile);
		board.setkmbPoint(kmbPoint);
		
		BoardService bSvc = new BoardService();
		
		int result = bSvc.boardModify(board);

		if (result > 0) {
			response.sendRedirect("boardView?kmbNum=" + kmbNum);
		} else {
			response.sendRedirect("boardList");
		}
	}

	

}
