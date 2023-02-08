package com.domain;

import com.domain.MemberVO.CMemberGrade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class GradeVO {
	private String id;
	private CMemberGrade grade;
}
