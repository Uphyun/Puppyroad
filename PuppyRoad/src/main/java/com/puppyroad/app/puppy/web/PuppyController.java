package com.puppyroad.app.puppy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String insertPuppyProcess(@Valid PuppyVO puppyVO, BindingResult bindingResult, Model model) {

		String mcode = SecurityUtil.memberCode();
		puppyVO.setClientUserId(mcode);
		System.out.println(puppyVO.getClientUserId());
		if(bindingResult.hasErrors()) {
			model.addAttribute("puppyVO", puppyVO);
			System.out.println("vaild에러");
			return "puppy/insertPuppy";
		}else {
			
			String addPuppy = puppyservice.addPuppy(puppyVO);
			if(addPuppy.equals("fail")) {
				System.out.println("mapper문제");
				
				return "redirect:puppy/puppyInsert";
				
			}else {
				
				return "redirect:/"; 
			}
		}
		
	}
	
	
}//end of controller
