package com.puppyroad.app.member.service;

import java.util.List;

public interface MemberService {
	//회원가입
	public String addMember(MemberVO memberVO);
	//아이디조회
	public int idCheck(String userId);
	//휴대폰인증
	public void certifiedPhoneNumber(String phoneNumber, String cerNum);
	//아이디 찾기
	public List<MemberVO> findId(String phoneNumber);
	//회원정보조회(단건조회)
	public MemberVO memberGetInfo(MemberVO memberVO);
	//회원정보업데이트
	public String memberUpdate(MemberVO memberVO);
}
