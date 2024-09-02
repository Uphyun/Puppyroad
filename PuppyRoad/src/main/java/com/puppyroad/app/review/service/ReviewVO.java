package com.puppyroad.app.review.service;

import lombok.Data;

@Data
public class ReviewVO {
	private String reviewCode;
	private String bulletinPicture;
	private String title;
	private Integer score;
	private String content;
	private String picture;
	private String writer;
	private Integer bulletinNo;

}
