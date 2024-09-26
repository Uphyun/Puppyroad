package com.puppyroad.app.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.puppyroad.app.security.service.LoginMemberVO;

public class SecurityUtil {
	public static String memberCode(){
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if(!authentication.isAuthenticated()) {
    		return null;
    	}
    	LoginMemberVO loginMemberVO = (LoginMemberVO)authentication.getPrincipal();
    	String mcode = loginMemberVO.getMemberVO().getMemberCode();
    	return mcode;
	}
	
	public static String userId(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if(!authentication.isAuthenticated()) {
    		return null;
    	}
		LoginMemberVO loginMemberVO = (LoginMemberVO)authentication.getPrincipal();
		String userId = loginMemberVO.getMemberVO().getUserId();
		return userId;
	}
	
	public static String nickname(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if(!authentication.isAuthenticated()) {
    		return null;
    	}
		LoginMemberVO loginMemberVO = (LoginMemberVO)authentication.getPrincipal();
		String nickname = loginMemberVO.getMemberVO().getNickName();
		return nickname;
	}
}
