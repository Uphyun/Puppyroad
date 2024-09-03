package com.puppyroad.app.member.mapper;



import java.util.List;

import com.puppyroad.app.member.service.MemberVO;

public interface MemberMapper {
	//회원가입
	public int insertMember(MemberVO memberVO);
	//중복아이디조회
	public int idCheck(String userId);
	//로그인
	public MemberVO memberLogin(MemberVO memberVO);
	
}
