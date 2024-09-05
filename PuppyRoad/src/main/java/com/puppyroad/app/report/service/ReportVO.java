package com.puppyroad.app.report.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReportVO {
	private int bulletinNo;
	private String complainant;
	private String reportTarget;
	private String reportType;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date reportDate;
	private String content;
	private String attachedFile;
	private int category;
	private String penaltyContent;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date penaltyDate;
	private String reportState;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private	Date processedDate;
}
