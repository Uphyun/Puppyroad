package com.puppyroad.app.news.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
	private String bulletinType;
	
	public List<String> getAttachedFileList (){
		return (attachedFile != null && !attachedFile.isEmpty())
				? new ArrayList<>(Arrays.asList(attachedFile.split(","))) : new ArrayList<>();
	}
}
