package com.puppyroad.app.petstarbulletin.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PetstarBulletinVO {
	private int bulletinNo;
	private String content;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date writingDate;
	private String attachedFile;
	private String memberCode;
}
