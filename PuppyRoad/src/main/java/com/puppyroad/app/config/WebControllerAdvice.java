package com.puppyroad.app.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice	//모든 컨트롤러에 공통적으로 적용되는 기능 별도 관리
public class WebControllerAdvice {
	//@ExceptionHandler		=> 특정 예외에 대한 처리 등록
	
	@ModelAttribute("contextPath")	//모든 페이지에서 공통으로 사용 => 이거는 contextPath 라는 이름 사용
	public String contextPath(final HttpServletRequest req) {
		return req.getContextPath();
	}
}
