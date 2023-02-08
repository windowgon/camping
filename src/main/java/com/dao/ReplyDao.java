package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.common.ConnectionUtil;
import com.domain.BoardVO;
import com.domain.ReplyVO;

public class ReplyDao {

	private DataSource dataSource; 
	
	public ReplyDao() {
		dataSource = ConnectionUtil.getDatasource();
	}
	
	//댓글 목록
	public List<ReplyVO> list(int bno) {
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		String query = "SELECT * FROM goodreply_cam WHERE BNO = ?";
		try(Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			){
			pstmt.setInt(1, bno);
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					ReplyVO vo = ReplyVO.builder()
						.cno(rs.getInt("cno"))
						.bno(rs.getInt("bno"))
						.reply(rs.getString("reply"))
						.writer(rs.getString("writer"))
						.replyDate(rs.getDate("replyDate"))
						.modifyDate(rs.getDate("modifyDate"))
						.build();
					list.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return list;
	}
	//댓글쓰기
	public void addreply(ReplyVO vo) {
		String query = "insert into goodreply_cam (cno,bno,reply,writer) values(seq_camreply.nextval,?,?,?)";
		String query2 = "update CAMBOARD_MAIN set replyCount = replycount+1 where bno=?";
		try (Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			PreparedStatement pstmt2 = conn.prepareStatement(query2);
			){
				pstmt.setInt(1, vo.getBno());
				pstmt.setString(2,vo.getReply());
				pstmt.setString(3, vo.getWriter());
				pstmt.executeUpdate();
				pstmt2.setInt(1, vo.getBno());
				pstmt2.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	//댓글 삭제
	public void removeReply(ReplyVO vo) {
		System.out.println(vo.toString());
		String query ="DELETE FROM goodreply_cam WHERE CNO=?";
		String query2 = "update CAMBOARD_MAIN set replyCount = replycount-1 where bno=?";
		try (Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			PreparedStatement pstmt2 = conn.prepareStatement(query2);
			){
			pstmt.setInt(1, vo.getCno());
			pstmt.executeUpdate();
			pstmt2.setInt(1, vo.getBno());
			pstmt2.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

