package com.puppyroad.app.admin.main.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManageMemberController {
	
	@GetMapping("admin/manageMember")
	public String manageMemberPage() {
		
		return "admin/manage/member";
	}
	
}
