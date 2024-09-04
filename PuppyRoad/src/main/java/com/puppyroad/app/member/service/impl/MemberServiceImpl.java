package com.puppyroad.app.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.puppyroad.app.member.mapper.MemberMapper;
import com.puppyroad.app.member.service.MemberService;
import com.puppyroad.app.member.service.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	private MemberMapper memberMapper;
	
	@Autowired
	public MemberServiceImpl(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	@Override
	public String addMember(MemberVO memberVO) {

		int result = memberMapper.insertMember(memberVO);
		return result == 1 ? memberVO.getMemberCode() : "fail";
	}

	@Override
	public int idCheck(String userId) {
		
		return memberMapper.idCheck(userId);
	}

	@Override
	public MemberVO loginMember(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return null;
	}




}
