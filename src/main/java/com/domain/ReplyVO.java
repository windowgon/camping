package com.domain;

import java.util.Date;

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
public class ReplyVO {
	private int cno; 
	private int bno; 
	private String reply;
	private String writer; 
	private Date replyDate; 
	private Date modifyDate; 
}