package com.puppyroad.app.member.service;

import java.util.List;

public interface MemberService {
	//회원가입
	public String addMember(MemberVO memberVO);
	//아이디조회
	public int idCheck(String userId);
	//로그인
	public MemberVO loginMember(MemberVO memberVO);


}
