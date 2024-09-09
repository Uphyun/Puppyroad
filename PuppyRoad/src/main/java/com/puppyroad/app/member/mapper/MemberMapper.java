package com.puppyroad.app.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.puppyroad.app.member.service.MemberVO;

public interface MemberMapper {
	//회원가입
	public int insertMember(MemberVO memberVO);
	//중복아이디조회
	public int idCheck(String userId);
	//로그인
	public MemberVO getUserInfo(String userId);	
	//아이디찾기
	public List<MemberVO> idFind(String phoneNumber);
	//비밀번호찾기
	public int pwFind(String phoneNumber);//아직안함
	//회원정보조회
	public MemberVO memberGetInfo(MemberVO memberVO);
	//회원정보수정
	public int memberUpdate(MemberVO memberVO);
}
