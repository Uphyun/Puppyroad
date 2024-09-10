package com.puppyroad.app.member.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.puppyroad.app.puppy.service.PuppyVO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberVO {
	private String memberCode;	
	@NotBlank(message = "*아이디는 필수 입력 항목입니다.")
	private String userId;
	@NotBlank(message = "*비밀번호는 필수 입력 항목입니다.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "*비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	private String userPw;
	@NotBlank(message = "*비밀번호확인는 필수 입력 항목입니다.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "*비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	private String userPwck;
	@NotBlank(message = "*이름는 필수 입력 항목입니다..")
	private String name;
	@NotBlank(message = "*닉네임는 필수 입력 항목입니다.")
	@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "*닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
	private String nickName;
	@NotBlank(message = "*주소는 필수 입력 항목입니다.")
	private String address;
	@NotBlank
	@Email(message = "*잘못된 이메일 형식입니다.")
	private String email;
	@NotBlank(message = "*휴대폰는 필수 입력 항목입니다.")
	private String phone;
	@NotBlank(message = "*직함란을 선택해주세요.")
	private String position;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date activityDate;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date joinDate;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date withdrawDate;
	private String intention;
	private int accountState;
	
	private List<PuppyVO> puppyList;
}
