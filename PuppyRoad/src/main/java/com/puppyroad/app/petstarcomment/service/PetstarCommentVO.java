package com.puppyroad.app.petstarcomment.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PetstarCommentVO {
	private int commentCode;
	private String writer;
	private String content;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate;
	private int bulletinNo;
	private String profilePicture;
}
