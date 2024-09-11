package com.puppyroad.app.puppy.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.puppyroad.app.puppy.service.PuppyService;
import com.puppyroad.app.puppy.service.PuppyVO;
import com.puppyroad.app.util.SecurityUtil;

import jakarta.validation.Valid;

@Controller
public class PuppyController {
	
	private PuppyService puppyservice;
	//private SecurityUtil securityUtil;
	
	@Autowired
	public PuppyController(PuppyService puppyservice) {
		
		this.puppyservice = puppyservice;
		//this.securityUtil = securityUtil;
	}
	
	//강아지 프로필 등록 - 페이지
	@GetMapping("user/insertPuppy")
	public String insertPuppyPage(Model model) {
		model.addAttribute("puppyVO", new PuppyVO());
		return "puppy/insertPuppy";
	}
	
	//강아지 프로필 등록 - 처리
	@PostMapping("user/insertPuppy")
	public String insertPuppyProcess(@Valid PuppyVO puppyVO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

		String mcode = SecurityUtil.memberCode();
		puppyVO.setClientUserId(mcode);
		System.out.println(puppyVO.getClientUserId());
		if(bindingResult.hasErrors()) {
			model.addAttribute("puppyVO", puppyVO);
			
			return "puppy/insertPuppy";
		}else {
			String addPuppy = puppyservice.addPuppy(puppyVO);
			if(addPuppy.equals("fail")) {
				return "redirect:puppy/puppyInsert";
			}else {
				redirectAttributes.addFlashAttribute("successMsg", "댕댕이 정상 등록 완료!");
				return "redirect:/user/listPuppy"; 
			}
		}
	}
	//강아지 리스트
	@GetMapping("user/listPuppy")
	public String listPuppys(String clientUserId, Model model) {
		List<PuppyVO> list = puppyservice.ListPuppy(SecurityUtil.memberCode());
		model.addAttribute("list", list);
		return "puppy/selectPuppy";
	}
	
	//강아지 조회
	@GetMapping("user/getInfoPuppy")
	public String getInfoPuppy(PuppyVO puppyVO, Model model) {
		String mcode = SecurityUtil.memberCode();
		puppyVO.setClientUserId(mcode);
		PuppyVO Pvo = puppyservice.getInfoPuppy(puppyVO);
		model.addAttribute("pvo", Pvo);
		return "puppy/getInfoPuppy";
	}
	
	//강아지 삭제
	
	//강아지 수정
	
	
	
	
}//end of controller
