package com.puppyroad.app.member.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppyroad.app.member.service.MemberService;
import com.puppyroad.app.member.service.MemberVO;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
//@RequestMapping("member")
public class MemberController {
	private MemberService memberService;
	private PasswordEncoder passwordEncoder;

	// 회원가입등록 - 페이지
	@GetMapping("memberInsert")
	public String memberInsertForm(Model model) {

		model.addAttribute("memberVO", new MemberVO());
		return "member/memberInsert";
	}

	// 회원가입등록 - 처리
	@PostMapping("memberInsert")
	public String memberInsertProcess(@Valid MemberVO memberVO, 
			                          BindingResult bindingResult, 
			                          Model model) {


		// 입력 값에 오류가 있는 경우
		if (bindingResult.hasErrors() ) {
			model.addAttribute("memberVO", memberVO);
			return "member/memberInsert"; // 입력 폼으로 다시 이동
		} else {
			String password = passwordEncoder.encode(memberVO.getUserPw());
			memberVO.setUserPw(password);
			String mid = memberService.addMember(memberVO);
			if (mid.equals("fail")) {
				return "redirect:/memberInsert";
			} else {				
				return "redirect:/memberJoin";
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
	@GetMapping("memberLookingId")
	@ResponseBody
	public List<MemberVO> memberFindIdProcess(MemberVO memberVO, Model model) {
		
		return memberService.findId(memberVO.getPhone());
	}
	
	//아이디찾기 - 페이지
	@GetMapping("memberFindId")
	public String memberFindPwProcess() {
		return "member/memberFindId";
	}
	
	//회원정보조회
	@GetMapping("user/memberGetInfo")
	public String memberGetInfo(MemberVO memberVO, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		memberVO.setUserId(authentication.getName());
		MemberVO list = memberService.memberGetInfo(memberVO);
		model.addAttribute("list", list);
		return "member/memberGetInfo";
	}
	
	//비밀번호체크
	@PostMapping("user/checkPassword")
	@ResponseBody
	public String checkPassword(@RequestParam("userPw") String inputPw, MemberVO memberVO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		memberVO.setUserId(authentication.getName());
		MemberVO userPw = memberService.memberGetInfo(memberVO);
		if(passwordEncoder.matches(inputPw , userPw.getUserPw())) {
			return "비밀번호일치";
		}else {
			return "비밀번호 불일치";
		}
	}
	
	//정보확인하기
	@GetMapping("user/checkDetail")
	public String checkDetail() {
		return "member/checkDetail";
	}
	
	//회원정보수정
	@PostMapping("user/memberUpdate")
	@ResponseBody
	public String memberUpdate(@RequestBody MemberVO memberVO) {
		System.out.println(memberVO);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		memberVO.setUserId(authentication.getName());
		String password = passwordEncoder.encode(memberVO.getUserPw());
		memberVO.setUserPw(password);
		return memberService.memberUpdate(memberVO);
	}
	
	//회원탈퇴-페이지
	@GetMapping("user/memberDelete")
	public String memberDeletePage() {
		return "member/memberDelete";
	}
	
	//회원탈퇴-처리
	@GetMapping("user/memberDeleteDone")
	public String memberDelete(MemberVO memberVO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		memberVO.setUserId(authentication.getName());
		memberService.memberDelete(memberVO);
		return "redirect:/";
	}
}
