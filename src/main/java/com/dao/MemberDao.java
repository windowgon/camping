package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.common.ConnectionUtil;
import com.domain.BoardVO;
import com.domain.MemberVO;
import com.domain.MemberVO.CMemberGrade;

public class MemberDao {

	private DataSource dataSource;

	public MemberDao() {
		dataSource = ConnectionUtil.getDatasource();
	}
	
	//회원가입
	public void insertMember(MemberVO vo) {
		String query = "INSERT INTO C_CAMPER(CNO,ID,PWD,NAME,EMAIL,PHONE) VALUES(CNO_SEQ.NEXTVAL,?, ?, ?, ?, ?)";
		try (Connection conn = dataSource.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query);
			){
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getPhone());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//로그인 체크
	public boolean loginCheck(MemberVO vo) {
		boolean result = false;
		String query = "select decode(count(*),1,'true','false') as result from C_CAMPER where id=? and pwd=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query);) {
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			try (ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					result = Boolean.parseBoolean(rs.getString("result"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 회원등급 조회
	public CMemberGrade findMemberGradeById(String id) {
		CMemberGrade grade = null;
		String query = "SELECT GRADE FROM C_CAMPER WHERE ID=?";
		try (Connection conn = dataSource.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement(query);
				){
			pstmt.setString(1, id);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) grade = CMemberGrade.valueOf(rs.getString("grade"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grade;
	}
	
	//캠퍼 리스트 조회
	public List<MemberVO> camperList() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		String query = "SELECT * FROM C_CAMPER";
		try(Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			){
			while(rs.next()){
			MemberVO vo = new MemberVO();
			vo.setCno(rs.getInt("cno"));
			vo.setId(rs.getString("id"));
			vo.setName(rs.getString("pwd"));
			vo.setName(rs.getString("name"));
			vo.setEmail(rs.getString("email"));
			vo.setPhone(rs.getString("phone"));
			vo.setDate(rs.getDate("joindate"));
			list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//멤버삭제
	public void delMember(int cno) {
		String query ="DELETE FROM C_CAMPER WHERE CNO=?";
		try(Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			){
		pstmt.setInt(1, cno);
		pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//아이디 중복확인
	public int findId(String id) {
		int idCheck = 0; //오류 발생
		String query = "SELECT ID FROM C_CAMPER WHERE ID=?";
		try(Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
				){
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					idCheck=1;  // 이미 존재하는 경우, 생성 불가능
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idCheck;
	}
}
	


	