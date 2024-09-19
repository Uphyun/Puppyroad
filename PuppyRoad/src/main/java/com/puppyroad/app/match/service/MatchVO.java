package com.puppyroad.app.match.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MatchVO {
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
	
	private List<String> puppies;
}
