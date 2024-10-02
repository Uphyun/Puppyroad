package com.puppyroad.app.walkjournal.service;

import java.util.Date;

import com.puppyroad.app.puppy.service.PuppyVO;

import lombok.Data;

@Data
public class WalkJournalVO {
	
    private String journalCode;
    private String title;
    private Date walkDate;
    private String journalContent;
    private String path;
    private String video;
    private Integer age;
    private Integer weight;
    private String dogBreed;
    private Date journalPreparationDate;
    private String walkerCode;
    private String puppyCode;
    private String dogName;
    private String picture;
    private String name;
    private String phone;
    private String Writer;
}
