package com.puppyroad.app.member.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppyroad.app.member.service.MemberService;
import com.puppyroad.app.member.service.MemberVO;

@Controller
public class MemberController {
	private MemberService memberService;
	
	@Autowired
	MemberController(MemberService memberService){
		this.memberService = memberService;
	}

	//회원가입등록 - 페이지
	@GetMapping("memberInsert")
	public String memberInsertForm() {
		return "member/memberInsert";
	}
	
	//회원가입등록 - 처리
	@PostMapping("memberInsert")
	public String memberInsertProcess(MemberVO memberVO) {
		String mid = memberService.addMember(memberVO);
		String url = "redirect:/fail";
		
		if(!"fail".equals(mid)) {
			url = "redirect:/";
		}
		
		return url;
	}
	
	//아이디중복체크
	@PostMapping("idCheck")
		@ResponseBody
		public Map<String, Object> idCheck(String userId){
		
		int count = 0;
		Map<String, Object> map = new HashMap<>();
		
		count = memberService.idCheck(userId);
		map.put("cnt", count);
		
		return map;
	}
	
		
}
