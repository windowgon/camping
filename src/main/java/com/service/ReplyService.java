package com.service;

import java.util.List;

import com.dao.ReplyDao;
import com.domain.ReplyVO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReplyService {

	private ReplyDao dao;
	
	//댓글 목록 불러오기
	public List<ReplyVO> list(int bno) {
		return dao.list(bno);
	}
	//댓글 쓰기
	public void writer(ReplyVO vo) {
		dao.addreply(vo);
	}
	//댓글 삭제
	public void delreply(ReplyVO vo) {
		dao.removeReply(vo);
	}
}
