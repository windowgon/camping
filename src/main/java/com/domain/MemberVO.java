package com.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberVO {
	private int cno;
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String phone;
	private Date date;
	private CMemberGrade grade;
	
	public enum CMemberGrade{
		ROLE_ADMIN, ROLE_CAMPER
	}
	public static void main(String[] args) {
		MemberVO vo = new MemberVO();
		vo.setGrade(CMemberGrade.ROLE_ADMIN);
		vo.setGrade(CMemberGrade.ROLE_CAMPER);
		
		CMemberGrade g = CMemberGrade.valueOf("ROLE_ADMIN");
		System.out.println(g);
	}
}