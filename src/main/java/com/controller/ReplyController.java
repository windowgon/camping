package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.domain.BoardVO;
import com.domain.MemberVO;
import com.domain.ReplyVO;
import com.google.gson.Gson;
import com.service.ReplyService;

@WebServlet("/reply/*")
public class ReplyController extends HttpServlet {
	
	private ReplyService service; 
	private Gson gson; 
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext sc = config.getServletContext();
		service = (ReplyService) sc.getAttribute("replyService");
		gson = new Gson();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		final String PREFIX = "/WEB-INF/views/reply/";
		final String SUFFIX = ".jsp";
		
		String pathInfo = request.getPathInfo(); 
		String contextPath = request.getContextPath();
		
		//댓글목록
		if(pathInfo.equals("/list")) {
			String paramBno = request.getParameter("bno");
			int bno = Integer.parseInt(paramBno);
			List<ReplyVO> replyList = service.list(bno);
			out.print(gson.toJson(replyList));
			
		//댓글작성
		}else if(pathInfo.equals("/write")) {
			String paramBno = request.getParameter("bno");
			long currenTime = System.currentTimeMillis();
			HttpSession session = request.getSession(false);
			session.setAttribute("lastWriting", currenTime);
			ReplyVO vo = ReplyVO.builder()
								.bno(Integer.parseInt(paramBno))
								.reply(request.getParameter("reply"))
								.writer(request.getParameter("writer")).build();
			service.writer(vo);
			String result = gson.toJson("댓글이 등록되었습니다.");
			out.print(result);
				
		//댓글 삭제
		}else if(pathInfo.equals("/delreply")) {
			String inputCno = request.getParameter("cno");
			String paramBno = request.getParameter("bno");
			try {
				ReplyVO vo = ReplyVO.builder()
							.bno(Integer.parseInt(paramBno))
							.cno(Integer.parseInt(inputCno)).build();
				 service.delreply(vo);
				 
				 return;
			}catch (Exception e) {
				e.printStackTrace();
			}
			String json = gson.toJson("성공했습니다.");
			out.print(json);
		}
	}
}