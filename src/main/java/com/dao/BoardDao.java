package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.common.ConnectionUtil;
import com.domain.BoardVO;

public class BoardDao {
	
	private DataSource dataSource;
	
	public BoardDao() {
		dataSource = ConnectionUtil.getDatasource();
	}
	
	// 게시판 글 리스트
	public List<BoardVO> selectAll() {
		String query = "SELECT * FROM CAMBOARD_MAIN ORDER BY BNO DESC";
		List<BoardVO> list = new ArrayList<>();
		try(Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			){
		while(rs.next()) {
			BoardVO vo = BoardVO.builder()
						.bno(rs.getInt("bno"))
						.title(rs.getString("title"))
						.content(rs.getString("content"))
						.writer(rs.getString("writer"))
						.writeDate(rs.getDate("writeDate"))
						.imageFileName(rs.getString("imageFileName"))
						.replyCount(rs.getInt("replyCount"))
						.good(rs.getInt("good"))
						.build();
			list.add(vo);
				}
			} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//글 상세
	public BoardVO selectOne(int bno) {
		BoardVO vo = null;
		String query = "SELECT * FROM CAMBOARD_MAIN WHERE BNO=?";
		try(Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);	
			){
			pstmt.setInt(1, bno);
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					vo = BoardVO.builder()
								.bno(rs.getInt("bno"))
								.title(rs.getString("title"))
								.content(rs.getString("content"))
								.writer(rs.getString("writer"))
								.writeDate(rs.getDate("writeDate"))
								.good(rs.getInt("good"))
								.replyCount(rs.getInt("replyCount"))
								.imageFileName(rs.getString("imageFileName"))
								.build();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return vo;
	}
	//글 작성하기
	public int insertBoard(BoardVO vo) {
		String query = "INSERT INTO CAMBOARD_MAIN(BNO,TITLE,CONTENT,WRITER,imageFileName) VALUES (?,?,?,?,?)";
		int camboardNo = getNewBno();
		try(Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);	
		){	
			pstmt.setInt(1, camboardNo);
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getWriter());
			pstmt.setString(5, vo.getImageFileName());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return camboardNo;
	}

	//새로운 글 번호 생성합니다
	private int getNewBno() {
		int camboardNo = 0;
		String query = "SELECT MAX(BNO)+1 as camboardNo FROM CAMBOARD_MAIN";
		try (Connection conn = dataSource.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query);
			 ResultSet rs = pstmt.executeQuery();
		){
		if(rs.next()) {
			camboardNo = rs.getInt("camboardNo");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return camboardNo;
	}
	//글을 수정합니다
	public void updateBoard(BoardVO vo) {
	
		String imageFileName = vo.getImageFileName();
		int bno = vo.getBno();
		
		String query = "UPDATE CAMBOARD_MAIN SET TITLE=?,CONTENT=?";
		if(imageFileName!=null) {
			query+=",imageFileName=? where bno=?";
		}else {// 이미지 파일 x
			query+=" where bno=?";
		}
		try(Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);	
			){
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			if(imageFileName != null) {
				pstmt.setString(3, imageFileName);
				pstmt.setInt(4, bno);
			}else {
				pstmt.setInt(3, bno);
			}
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 글 삭제
	public void removeBoard(int bno) {
		String query = "DELETE FROM CAMBOARD_MAIN WHERE BNO=?";
		try(Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			){
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 내가 쓴 글 가져오기
		public List<BoardVO> iwrote(String i) {
			String query = "SELECT * FROM CAMBOARD_MAIN WHERE WRITER=?";
			List<BoardVO> list = new ArrayList<>();
			try(
				Connection coon = dataSource.getConnection();
				PreparedStatement pstmt = coon.prepareStatement(query);
				){
				pstmt.setString(1, i);
				try(ResultSet rs = pstmt.executeQuery();){
					while(rs.next()) {
						BoardVO vo = BoardVO.builder()
								.bno(rs.getInt("bno"))
								.title(rs.getString("title"))
								.content(rs.getString("content"))
								.writer(rs.getString("writer"))
								.writeDate(rs.getDate("writeDate"))
								.imageFileName(rs.getString("imageFileName"))
								.replyCount(rs.getInt("replyCount"))
								.good(rs.getInt("good"))
								.build();
					list.add(vo);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
      //게시물 좋아요  
		public void LikeBoard(int bno) throws SQLException {
			String query = "update CAMBOARD_MAIN set good = good + 1 where bno = ?";
			try(
				Connection coon = dataSource.getConnection();
				PreparedStatement pstmt = coon.prepareStatement(query);
				){
				pstmt.setInt(1, bno);
				pstmt.executeUpdate();
			}catch (Exception e) {
			e.printStackTrace();
			}
		}
}

