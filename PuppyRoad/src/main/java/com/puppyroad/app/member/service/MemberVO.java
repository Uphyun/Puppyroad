package com.puppyroad.app.member.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberVO {
	private String memberCode;
	private String userId;
	private String userPw;
	private String name;
	private String nickName;
	private String address;
	private String email;
	private String phone;
	private String position;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date activityDate;
	private Date joinDate;
	private Date withdrawDate;
	private String intention;
}
