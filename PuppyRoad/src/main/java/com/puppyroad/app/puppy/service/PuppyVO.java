package com.puppyroad.app.puppy.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PuppyVO {
	private String puppyCode;
	@NotBlank(message = "*애완견 이름은 필수 입력 항목입니다.")
	private String dogName;
	@NotBlank(message = "*견종은 필수 입력 항목입니다.")
	private String dogBreed;			    //견종
	@NotNull(message = "*애완견 나이는 필수 입력 항목입니다.")
	private int age;
	private int gender;						// 0: 남, 1: 여
	@NotBlank(message = "*애완견 크기는 필수 입력 항목입니다.")
	private String dogSize;					// "소형", "중형", "대형"
	@NotNull(message = "*애완견 몸무게는 필수 입력 항목입니다.")
	private double weight;
	@NotBlank(message = "*애완견 성격은 필수 입력 항목입니다.")
	private String personality;				//성격
	@NotNull(message = "*애완견 중성화 수술 유무는 필수 입력 항목입니다.")
	private int neutralizationPreAbs;		//중성화유무
	@NotNull(message = "*애완견 질병유무는 필수 입력 항목입니다.")
	private int diseasePreAbs;				//질병유무
	private String note;					//기타사항
	private String clientUserId;
	private String picture;
}
