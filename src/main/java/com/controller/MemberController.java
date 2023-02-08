package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.dao.MemberDao;
import com.domain.GradeVO;
import com.domain.MemberVO;
import com.domain.MemberVO.CMemberGrade;
import com.google.gson.Gson;
import com.service.MemberService;

@WebServlet("/camper/*")
public class MemberController extends HttpServlet {

	private MemberService service;
	private Gson gson;

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		service = new MemberService(new MemberDao());
		gson = new Gson();
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
		final String PREFIX = "/WEB-INF/views/member/";
		final String SUFFIX = ".jsp";
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		RequestDispatcher rd = null;
		String nextPage = null;
		
		
		
		// 회원가입 폼
		if (pathInfo.equals("/joinForm")) {
			nextPage = "joinForm";
		}
		// 회원가입
		else if(pathInfo.equals("/join")) {
			String id = request.getParameter("id");
			String pwd = (String) request.getAttribute("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			MemberVO vo = MemberVO.builder()
								.id(id)
								.pwd(pwd)
								.name(name)
								.email(email)
								.phone(phone)
								.build();
			service.memberJoin(vo);
			response.sendRedirect(contextPath + "/board");
			return;
		}
		// 로그인폼
		else if(pathInfo.equals("/loginForm")) {
			nextPage = "loginForm";
		}
		//로그인 처리
		else if(pathInfo.equals("/login")) {
			String id = request.getParameter("id");
			String pwd = (String) request.getAttribute("pwd");
			MemberVO vo = MemberVO.builder()
					.id(id)
					.pwd(pwd)
					.build();
			if (service.loginService(vo)) {
				HttpSession session = request.getSession();	
				// 회원 권한
				CMemberGrade grade = service.getCMemberGrade(vo.getId());
				GradeVO gradeVO = new GradeVO();
				gradeVO.setId(vo.getId());
				gradeVO.setGrade(grade);
				session.setAttribute("grade", gradeVO);
				System.out.println("완료했니");
				out.print(gson.toJson(id+"환영합니다"));
				return;
			}else {
				out.print(gson.toJson("실패 다시 시도바람"));
				return;
			}
		//로그아웃 처리
		}else if(pathInfo.equals("/logout")) {
			HttpSession session = request.getSession(false);
			session.removeAttribute("grade");
			response.sendRedirect(contextPath + "/board/");
			return;
			
		//멤버리스트
		}else if(pathInfo.equals("/camperList")) {
			List<MemberVO> camperList = service.camperList();
			request.setAttribute("camperList", camperList);
			nextPage = "camperList";
			
		//멤버삭제
		}else if(pathInfo.equals("/removeMember")) {
			String inputCno = request.getParameter("cno");
			try {int cno = Integer.parseInt(inputCno);
				 service.removeMember(cno);
				 response.sendRedirect(contextPath + "/camper/camperList");
				 return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		//아이디 중복체크
		}else if(pathInfo.equals("/idcheck")) {
			String id = request.getParameter("id");
			int checkId = service.idCheck(id);
			out.print(gson.toJson(checkId));
			return;
		//에러
		}else{
			System.out.println("페이지를 찾을 수 없습니다.");
			return;
		}
		rd = request.getRequestDispatcher(PREFIX + nextPage + SUFFIX);
		rd.forward(request, response);
	}
}