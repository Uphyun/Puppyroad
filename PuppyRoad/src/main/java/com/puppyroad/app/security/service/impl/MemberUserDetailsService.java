package com.puppyroad.app.security.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.puppyroad.app.member.mapper.MemberMapper;
import com.puppyroad.app.member.service.MemberVO;
import com.puppyroad.app.security.service.LoginMemberVO;

@Service
public class MemberUserDetailsService implements UserDetailsService {
	
	private MemberMapper memberMapper;
	
	public MemberUserDetailsService(MemberMapper memberMapper) {
		this.memberMapper= memberMapper;
	}
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		// Mapper를 활용해서 DB 접근
		MemberVO memberVO = memberMapper.getUserInfo(userId);
		
		if(memberVO == null) {
			throw new UsernameNotFoundException(userId);
		}
		
		return new LoginMemberVO(memberVO);
	}
}
