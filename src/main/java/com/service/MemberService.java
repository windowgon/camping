package com.service;

import java.util.List;

import com.dao.MemberDao;
import com.domain.MemberVO;
import com.domain.MemberVO.CMemberGrade;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public class MemberService {

	private MemberDao dao;

	//회원가입
	public void memberJoin(MemberVO vo) {
		dao.insertMember(vo);
	}
	//로그인 체크
	public boolean loginService(MemberVO vo) {
		return dao.loginCheck(vo);
	}
	//회원등급
	public CMemberGrade getCMemberGrade(String id) {
		return dao.findMemberGradeById(id);
	}
	//회원 리스트
	public List<MemberVO> camperList() {
		return dao.camperList();
	}
	//회원 삭제
	public void removeMember(int cno) {
		dao.delMember(cno);
	}
	//아이디 중복체크
	public int idCheck(String id) {
		return dao.findId(id);
	}
}
