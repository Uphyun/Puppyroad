package com.puppyroad.app.member.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppyroad.app.member.service.MemberService;
import com.puppyroad.app.member.service.MemberVO;

import jakarta.validation.Valid;

@Controller
public class MemberController {
	private MemberService memberService;
	private PasswordEncoder passwordEncoder;
	@Autowired
	MemberController(MemberService memberService, PasswordEncoder passwordEncoder){
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}
	

	//회원가입등록 - 페이지
	@GetMapping("memberInsert")
	public String memberInsertForm(Model model) {
		
		model.addAttribute("memberVO", new MemberVO());
		return "member/memberInsert";
	}
	
	//회원가입등록 - 처리
	@PostMapping("memberInsert")
	public String memberInsertProcess(@Valid MemberVO memberVO, BindingResult bindingResult, Model model) {
		
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("memberVO", memberVO);
			
			return "member/memberInsert";
		}
		
		String password = passwordEncoder.encode(memberVO.getUserPw());
		memberVO.setUserPw(password);
			
		String mid = memberService.addMember(memberVO);
		
		if(!"fail".equals(mid)) {
			
			return "redirect:memberLogin";
			
		}else {	
			
			model.addAttribute("fail", "아이디 또는 비밀번호가 일치하지 않습니다.");
			return "memberInsert/memberInsert";
		}		
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
	
	//로그인페이지
	@GetMapping("memberLogin")
	public String memberLoginForm(Model model) {
		
		return "member/memberLogin";
	}
	
	//로그인처리
	@PostMapping("memberLogin")
	public String memberLogin(MemberVO memberVO, Model model) {
		MemberVO login = memberService.loginMember(memberVO);
		
		if(login != null && passwordEncoder.matches(memberVO.getUserPw(), login.getUserPw())) {
			
			return "redirect:/";
			
		}else{
			
			model.addAttribute("error", "실패");
			return "member/memberLogin";
		}
	}

	
		
}
