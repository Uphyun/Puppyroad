package com.puppyroad.app.assi.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class AssiVO {
	private Integer bulletinNo;
	private String  coverPhoto;
	private String  title;
	private String  writer;
	private Integer matchingState;           // 매칭상태
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date    startTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date    endTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date    writingTime;
	private Integer animalCount;
	private String  walkPlaceAddress;         // 산책장소
	private String  matchingKind;             // 매칭 종류
	private String  clientCode;               // 회원 코드
	private String  content;
	private String  memberCode;
	private String  nickName;
	private String  name;
	private String  position;
	private String  walkerUserId;
	private Integer dayFare;
	private Integer regularityFare;

}
