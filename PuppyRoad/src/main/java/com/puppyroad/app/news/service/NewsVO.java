package com.puppyroad.app.news.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class NewsVO {
	private int bulletinNo;
	private String title;
	private String content;
	private String writer;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date writingDate;
	private String attachedFile;
	private int bulletinType;
}
