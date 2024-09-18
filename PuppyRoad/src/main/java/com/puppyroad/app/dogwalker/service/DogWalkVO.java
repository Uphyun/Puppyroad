package com.puppyroad.app.dogwalker.service;

import java.util.Date;

import lombok.Data;
@Data
public class DogWalkVO {
	
	private String memberCode;
	private Integer gender;
	private String bankCode;
	private String bankAccount;
	private String picture;
	private String workWeek;
	private String workTime;
	private String preferenceLocation;
	private String approvalState;
	private String memo;
	private Integer sSize;
	private Integer mSize;
	private Integer lSize;
	
}
