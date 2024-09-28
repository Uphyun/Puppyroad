package com.puppyroad.app.admin.main.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppyroad.app.admin.main.service.AdminMainService;

@Controller
public class adminMainController {
	
	@Autowired
	AdminMainService adminMainService;

	@GetMapping("admin")
	public String adminPage() {
		
		return "admin/index";
	}
	
	@GetMapping("ajax/walkerCnt")
	@ResponseBody
	public int walkerCntAjax() {
		return adminMainService.getNewWalkerCnt();
	}
}
