package com.puppyroad.app.assi.service;



import lombok.Data;

@Data
public class WalkerAddInfoVO {
	private String  memberCode;
	private Integer gender;
	private String  bankCode;	
	private String  bankAccount;
	private String  picture;
	private String  workWeek;
	private String  workTime;	
	private String  preferenceLocation;
	private Integer approvalState;
	private String  memo;
	private Integer sSize;
	private Integer mSize;
	private Integer lSize;
	private Integer price;

}
