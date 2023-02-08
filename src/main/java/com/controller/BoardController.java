package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.FileUpload;
import com.dao.BoardDao;
import com.domain.BoardVO;
import com.domain.GradeVO;
import com.service.BoardService;


@WebServlet("/board/*")
public class BoardController extends HttpServlet {

	private BoardService service;
	private FileUpload mutiReq; 
	
	@Override
	public void init() throws ServletException {
		BoardDao dao = new BoardDao();
		service = new BoardService(dao);
		mutiReq = new FileUpload("board/");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doHandle(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doHandle(request, response);
	}
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo(); 
		String contextPath = request.getContextPath();
		final String PREFIX = "/WEB-INF/views/board/";
		final String SUFFIX = ".jsp";
		
		RequestDispatcher rd = null;
		String nextPage = null;
		
		// 게시판 글 리스트
		if(pathInfo.equals("/list")) {
			List<BoardVO> boardList = service.boardList();
			request.setAttribute("list", boardList);
			nextPage = "list";
			
		// 상세글
		}else if(pathInfo==null || pathInfo.equals("/") || pathInfo.equals("/home")){
			nextPage = "home";
		}
		else if(pathInfo.equals("/detail")){
			String paramBno = request.getParameter("bno").trim();
			int bno = Integer.parseInt(paramBno);
			BoardVO board = service.findBoard(bno);
			request.setAttribute("board", board);
			nextPage = "detail";
		}
		// 글쓰기로 가기
		else if(pathInfo.equals("/writeForm")) {
		nextPage = "writeForm";
		}
		// 글쓰기
		else if(pathInfo.equals("/write")){
			Map<String, String> req = mutiReq.getMutipartRequest(request);
			String imageFileName = req.get("imageFileName");
			BoardVO vo = BoardVO.builder()
						.title(req.get("title"))
						.content(req.get("content"))
						.writer(req.get("writer"))
						.imageFileName(req.get("imageFileName")).build();
			int camboardNo = service.addBoard(vo);
		
		//이미지파일 첨부 했을 때
		if(imageFileName !=null && imageFileName.length()>0) {
			mutiReq.uploadImage(camboardNo, imageFileName);
		}
		response.sendRedirect(contextPath+"/board");
		return;
	}
		//글 수정
		else if(pathInfo.equals("/modBoard")) {
			Map<String, String> req = mutiReq.getMutipartRequest(request);
			String paramBno = req.get("bno");
			int bno = Integer.parseInt(paramBno);
			String title = req.get("title");
			String Content = req.get("content");
			String imageFileName = req.get("imageFileName");
			
			BoardVO vo = BoardVO.builder()
						.bno(bno)
						.title(title)
						.content(Content)
						.imageFileName(imageFileName)
						.build();
			service.modBoard(vo);
			
			if(imageFileName!=null) { // 이미지 파일이 있을 때 
				String originFileName = req.get("originFileName");
				mutiReq.uploadImage(bno, imageFileName);
				if(originFileName!=null) {
					File oldFile = new File("c:/file_repo/"+bno+"/"+originFileName);
					oldFile.delete();
				}
			}
			response.sendRedirect(contextPath+"/board");
			return;
		}
		//삭제처리
		else if(pathInfo.equals("/deleteBoard")) {
			Map<String, String> req = mutiReq.getMutipartRequest(request);
			String paramBno = req.get("bno");
			int bno = Integer.parseInt(paramBno);
			service.deleteBoard(bno);
			mutiReq.deleteAllImage(bno);
			response.sendRedirect(contextPath+"/board");
			return;
			
		//내가 쓴 글 보기
		}else if(pathInfo.equals("/iWorte")) {
			HttpSession session = request.getSession();	
			GradeVO vo = (GradeVO) session.getAttribute("grade");
			List<BoardVO> wroteList = service.wroteList(vo.getId());
			request.setAttribute("wroteList", wroteList);
			nextPage = "wroteList";

		//좋아요 기능
		}else if(pathInfo.equals("/bGood")) {
			String paramBno = request.getParameter("bno");
			int bno = Integer.parseInt(paramBno);
			service.goodBoard(bno);
			return;
			
		}else {
			System.out.println("존재하지 않는 페이지입니다.");
		}
		rd = request.getRequestDispatcher(PREFIX+nextPage+SUFFIX);
		rd.forward(request, response);
	}

}
