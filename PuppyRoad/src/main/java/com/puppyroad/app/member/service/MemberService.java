package com.puppyroad.app.member.service;


public interface MemberService {
	//회원가입
	public String addMember(MemberVO memberVO);
	//아이디조회
	public int idCheck(String userId);
	//휴대폰인증
	public void certifiedPhoneNumber(String phoneNumber, String cerNum);
	//아이디 찾기
	public MemberVO findId(MemberVO membervo);
}
