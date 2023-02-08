package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.BoardDao;
import com.domain.BoardVO;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public class BoardService {
	
	private BoardDao dao;

	//게시판 리스트
	public List<BoardVO> boardList() {
		return dao.selectAll();
	}
	//글 상세보기
	public BoardVO findBoard(int bno) {
		return dao.selectOne(bno);
	}
	//글 쓰기
	public int addBoard(BoardVO vo) {
		return dao.insertBoard(vo);
	}
	//글 수정
	public void modBoard(BoardVO vo) {
		dao.updateBoard(vo);
	}
	//글 삭제
	public void deleteBoard(int bno) {
		dao.removeBoard(bno);
	}
	//내가 쓴 글 가져오기
	public List<BoardVO> wroteList(String i) {
		return dao.iwrote(i);
	}
	//좋아요 기능 추가하기
	public void goodBoard(int bno){
		try {
			dao.LikeBoard(bno);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
