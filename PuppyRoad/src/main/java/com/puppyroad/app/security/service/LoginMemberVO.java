package com.puppyroad.app.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.puppyroad.app.member.service.MemberVO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter //memberVO의 접근이 가능하도록 함
public class LoginMemberVO implements UserDetails {
	
	private MemberVO memberVO;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //map 사용불가
		// extends GrantedAuthority를 상속한 대상중에서 (하나 제한한다는 개념) <=> super 반대개념
		List<GrantedAuthority> auths = new ArrayList<>();
		auths.add(new SimpleGrantedAuthority(memberVO.getPosition()));
		return auths;
	} //권한을 체크할때 그 값을 확인하는 용도

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return memberVO.getUserPw();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return memberVO.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() { //계정 패스워드 만료여부
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // 계정잠금여부
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { //계정 패스워드 만료여부
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() { //계정 사용여부
		// TODO Auto-generated method stub
		return true;
	}
	
}
