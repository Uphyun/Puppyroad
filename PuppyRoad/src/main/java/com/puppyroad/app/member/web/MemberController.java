package com.puppyroad.app.member.web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppyroad.app.member.service.MemberService;
import com.puppyroad.app.member.service.MemberVO;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
//@RequestMapping("member")
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
	public String memberInsertProcess(@Valid MemberVO memberVO, BindingResult bindingResult, Model model, String pwck) {


		// 입력 값에 오류가 있는 경우
		if (bindingResult.hasErrors() ) {
			model.addAttribute("memberVO", memberVO);
			return "member/memberInsert"; // 입력 폼으로 다시 이동
		} else {
					String password = passwordEncoder.encode(memberVO.getUserPw());
					memberVO.setUserPw(password);
					String mid = memberService.addMember(memberVO);
					if (mid.equals("fail")) {
						return "redireact:member/memberInsert";
					} else {
						
						return "redirect:member/memberJoin";
					}
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

		return "member/memberLogin";
	}

	// 인증번호
	@PostMapping("sendSMS")
	@ResponseBody
	public String sendSMS(String phoneNumber) {
		Random random = new Random();
		String randNum = "";
		for (int i = 0; i < 4; i++) {
			String ran = Integer.toString(random.nextInt(10));
			randNum += ran;
		}
		System.out.println(randNum);
		System.out.println("수신자 번호 : " + phoneNumber);
		System.out.println("인증번호 : " + randNum);
		memberService.certifiedPhoneNumber(phoneNumber, randNum);
		return randNum;
	}
	
	//회원가입축하페이지
	@GetMapping("memberJoin")
	public String memberJoin() {
		return "member/memberJoin";
	}
	
	//아이디,비밀번호 찾기 - 페이지
	@GetMapping("memberFind")
	public String memberFind() {
		return "member/memberFind";
	}
	
	//아이디찾기 - 처리
	@PostMapping("memberFindId")
	public String memberFindIdProcess(MemberVO memberVO, Model model) {
		MemberVO id = memberService.findId(memberVO);
		
		model.addAttribute("id", id);
		
		return "member/memberFindId";
	}
	
	//아이디찾기 - 페이지
	@GetMapping("memberFindId")
	public String memberFindPwProcess() {
		return "member/memberFindId";
	}
}
