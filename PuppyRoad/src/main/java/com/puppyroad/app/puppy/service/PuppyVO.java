package com.puppyroad.app.puppy.service;

import lombok.Data;

@Data
public class PuppyVO {
	private String puppyCode;
	private String dogName;
	private String dogBreed;
	private int age;
	private int gender;
	private String dogSize;
	private double weight;
	private String personality;				//성격
	private int neutralizationPreAbs;		//중성화유무
	private int diseasePreAbs;				//질병유무
	private String note;
	private String clientUserId;
	private String picture;
}
