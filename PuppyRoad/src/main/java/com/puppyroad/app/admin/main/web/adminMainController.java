package com.puppyroad.app.admin.main.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.puppyroad.app.admin.main.service.AdminMainService;

@Controller
public class adminMainController {
	
	@Autowired
	AdminMainService adminMainService;

	@GetMapping("admin")
	public String adminPage(Model model) {
		int newWalkers = adminMainService.getNewWalkerCnt();
		
		model.addAttribute("newWalkers", newWalkers);
		
		return "admin/index";
	}
}
