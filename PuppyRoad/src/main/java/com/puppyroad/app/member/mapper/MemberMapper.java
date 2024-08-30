package com.puppyroad.app.member.mapper;



import com.puppyroad.app.member.service.MemberVO;

public interface MemberMapper {
	//회원가입
	public int insertMember(MemberVO memberVO);

}
