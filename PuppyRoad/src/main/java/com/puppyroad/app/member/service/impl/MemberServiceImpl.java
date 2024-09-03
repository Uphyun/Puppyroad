package com.puppyroad.app.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.puppyroad.app.member.mapper.MemberMapper;
import com.puppyroad.app.member.service.MemberService;
import com.puppyroad.app.member.service.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	private MemberMapper memberMapper;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public MemberServiceImpl(MemberMapper memberMapper,PasswordEncoder passwordEncoder) {
		this.memberMapper = memberMapper;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public String addMember(MemberVO memberVO) {
		
		// 암호화
		String SecurityPw = passwordEncoder.encode(memberVO.getUserPw());
		memberVO.setUserPw(SecurityPw); 
		
		int result = memberMapper.insertMember(memberVO);
		
		return result == 1 ? memberVO.getMemberCode() : "fail";
	}

	@Override
	public int idCheck(String userId) {
		
		return memberMapper.idCheck(userId);
	}


}
