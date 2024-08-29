package com.puppyroad.app.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.puppyroad.app.member.service.MemberVO;

public interface MemberMapper {
	//회원가입
	public int insertMember(MemberVO memberVO);
	//테스트조회
	@Select("select * from test_01")
	public List<MemberVO> testSelect(MemberVO memberVO);
}
