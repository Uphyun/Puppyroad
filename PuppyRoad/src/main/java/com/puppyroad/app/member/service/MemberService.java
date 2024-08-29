package com.puppyroad.app.member.service;

import java.util.List;

public interface MemberService {
	//회원가입
	public int insertMember(MemberVO memberVO);

	List<MemberVO> selectTest(MemberVO memberVO);
}
