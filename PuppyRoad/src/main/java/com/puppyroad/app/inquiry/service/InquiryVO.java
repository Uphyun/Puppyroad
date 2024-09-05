package com.puppyroad.app.inquiry.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class InquiryVO {
	private int bulletinNo;
	private String title;
	private String content;
	private String writer;
	private String attachedFile;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date inquiryDate;
	private String answer;
	private String inquiryType;
	private String inquiryState;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date processedDate;
}
