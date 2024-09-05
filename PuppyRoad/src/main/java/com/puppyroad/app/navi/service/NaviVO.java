package com.puppyroad.app.navi.service;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class NaviVO {
	private String naviCode;
	private int turnNo;
	private double x;
	private double y;
	private Date createdDate;
	private Date updateDate;
	private String puppyCode;
	private String journalCode;
	private String scheduleCode;
	private List<NaviVO> cNavi;
}
