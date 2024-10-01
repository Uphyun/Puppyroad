package com.puppyroad.app.config;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.puppyroad.app.dogwalker.service.DogWalkerSerivce;
import com.puppyroad.app.security.service.LoginMemberVO;
import com.puppyroad.app.util.SecurityUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@ControllerAdvice // 모든 컨트롤러에 공통적으로 적용되는 기능 별도 관리
@RequiredArgsConstructor
public class WebControllerAdvice {
	// @ExceptionHandler => 특정 예외에 대한 처리 등록
	private final DogWalkerSerivce dogWalkerService; 

	@ModelAttribute("contextPath") // 모든 페이지에서 공통으로 사용 => 이거는 contextPath 라는 이름 사용
	public String contextPath(final HttpServletRequest req) {
		String contextPath = null;
		
		if(req.getContextPath() != null) {
			contextPath = req.getContextPath();
		}
		return contextPath;
	}

	@ModelAttribute("auth")
	public String anth(final @AuthenticationPrincipal LoginMemberVO loginVO) {
		String auth = null;

		if (loginVO != null) {
			auth = loginVO.getAuthorities().toString();
			auth = auth.substring(6, auth.length() - 1);
		}
		
		return auth;
	}
	
	@ModelAttribute("hasWalkerInfo")
	public boolean hasWalkerInfo() {
		String memberCode = SecurityUtil.memberCode();
		return dogWalkerService.walkerInfoCount(memberCode);
	}
}
