package com.puppyroad.app.member.mapper;

import com.puppyroad.app.member.service.MemberVO;

public interface MemberMapper {
	//회원가입
	public int insertMember(MemberVO memberVO);
	//중복아이디조회
	public int idCheck(String userId);
	//로그인
	public MemberVO getUserInfo(String userId);	
	//아이디찾기
	public MemberVO idFind(MemberVO memberVO);
	//비밀번호찾기
	public int pwFind(String phoneNumber);
}
