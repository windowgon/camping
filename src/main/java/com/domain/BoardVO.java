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
@ToString
@Builder
public class BoardVO {
	private int bno;
	private String title;
	private String content;
	private String writer;
	private Date writeDate;
	private String imageFileName;
	private int replyCount;
	private int good;
}
