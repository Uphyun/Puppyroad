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

		String mid = memberService.addMember(memberVO);
		// 입력 값에 오류가 있는 경우
		if (bindingResult.hasErrors()) {
			model.addAttribute("memberVO", memberVO);
			return "member/memberInsert"; // 입력 폼으로 다시 이동
		}
		try {

			if (!"fail".equals(mid)) {
				String password = passwordEncoder.encode(memberVO.getUserPw());
				memberVO.setUserPw(password);
			}
			return "redirect:memberLogin";

		} catch (DuplicateKeyException e) {
			model.addAttribute("key", "중복 아이디값 존재");
			return "memberInsert/memberInsert";
		} catch (Exception e) {
			return "redirect:/signup?error_code=-99";
		}
	}

	// 아이디중복체크
	@PostMapping("idCheck")
	@ResponseBody
	public Map<String, Object> idCheck(String userId) {

		int count = 0;
		Map<String, Object> map = new HashMap<>();

		count = memberService.idCheck(userId);
		map.put("cnt", count);

		return map;
	}

	// 로그인페이지
	@GetMapping("memberLogin")
	public String memberLoginForm() {

		return "member/memberLogin";
	}

}
