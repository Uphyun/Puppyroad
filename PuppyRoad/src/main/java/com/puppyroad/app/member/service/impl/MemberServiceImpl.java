package com.puppyroad.app.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<MemberVO> selectTest(MemberVO memberVO){
		return memberMapper.testSelect(memberVO);
	}
	
	@Override
	public int insertMember(MemberVO memberVO) {
		
		return memberMapper.insertMember(memberVO) ;
	}

}
