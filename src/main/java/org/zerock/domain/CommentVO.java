package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CommentVO {
	private Long bno;
	private Long cno;
	private String content;
	private String writer;
	private Date regdate;
	private Date updatedate;
}
