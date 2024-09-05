package com.puppyroad.app.member.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
	MemberController(MemberService memberService, PasswordEncoder passwordEncoder) {
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}

	// 회원가입등록 - 페이지
	@GetMapping("memberInsert")
	public String memberInsertForm(Model model) {

		model.addAttribute("memberVO", new MemberVO());
		return "member/memberInsert";
	}



	// 회원가입등록 - 처리
	@PostMapping("memberInsert")
	public String memberInsertProcess(@Valid MemberVO memberVO, BindingResult bindingResult, Model model) {

		String password = passwordEncoder.encode(memberVO.getUserPw());
		memberVO.setUserPw(password);
		String mid = memberService.addMember(memberVO);
		
		// 입력 값에 오류가 있는 경우
		if (bindingResult.hasErrors()) {
			model.addAttribute("memberVO", memberVO);
			return "member/memberInsert"; // 입력 폼으로 다시 이동
		}
		try {
			System.out.println(mid + "333");
			if ("fail".equals(mid)) {
				return "redireact:member/memberInsert";     
			}else {
			return "redirect:memberLogin";
			}
		} catch (DuplicateKeyException e) {
			
			return "redirect:member/memberInsert";
			
		} catch (Exception e) {
			
			return "redireact:member/memberInsert";
		}
	}

	// 아이디중복체크
	@PostMapping("idCheck")
	@ResponseBody
	public Map<String, Object> idCheck(String userId) {
		System.out.println("아이디체크");
		int count = 0;
		Map<String, Object> map = new HashMap<>();
		count = memberService.idCheck(userId);
		map.put("cnt", count);
		
		return map;
	}

	// 로그인페이지
	@GetMapping("memberLogin")
	public String memberLoginForm() {
		System.out.println("로그인");
		return "member/memberLogin";
	}

}
