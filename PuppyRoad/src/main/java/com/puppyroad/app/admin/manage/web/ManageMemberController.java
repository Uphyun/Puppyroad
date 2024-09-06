package com.puppyroad.app.admin.manage.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.puppyroad.app.admin.manage.service.ManageMemberService;

@Controller
public class ManageMemberController {
	
	@Autowired
	ManageMemberService manageMemberService;
	
	//의뢰인 리스트
	@GetMapping("admin/manageMember")
	public String manageMemberPage() {
		
		
		
		return "admin/manage/member";
	}

	//봉사인 리스트
	@GetMapping("admin/manageHelper")
	public String manageHelperPage() {
		
		return "admin/manage/helper";
	}

	//도그워커 리스트
	@GetMapping("admin/manageWalker")
	public String manageWaklerPage() {
		
		return "admin/manage/walker";
	}
}
