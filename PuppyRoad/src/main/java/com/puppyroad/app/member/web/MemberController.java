package com.puppyroad.app.member.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.puppyroad.app.member.service.MemberService;
import com.puppyroad.app.member.service.MemberVO;

@Controller
public class MemberController {
	private MemberService memberService;
	
	MemberController(MemberService memberService){
		this.memberService = memberService;
	}

		
}
