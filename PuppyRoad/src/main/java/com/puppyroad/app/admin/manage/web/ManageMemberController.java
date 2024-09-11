package com.puppyroad.app.admin.manage.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppyroad.app.admin.manage.service.ManageMemberService;
import com.puppyroad.app.member.service.MemberVO;

@Controller
public class ManageMemberController {
	
	@Autowired
	ManageMemberService manageMemberService;
	
	//의뢰인 리스트
	@GetMapping("admin/manageMember")
	public String manageMemberPage(Model model) {
		model.addAttribute("memberList", manageMemberService.getMemberList());
		
		return "admin/manage/member";
	}
	
	@GetMapping("ajax/manageMember")
	@ResponseBody
	public MemberVO manageMemberAjax(MemberVO memberVO) {
		
		return manageMemberService.getMemberInfo(memberVO.getMemberCode());
	}
	
	@PutMapping("ajax/changeState")
	@ResponseBody
	public Map<String, Object> changeAccountState(@RequestBody MemberVO memberVO) {
		System.out.println(memberVO);
		
		return manageMemberService.updateAccountState(memberVO);
	}
	
	@GetMapping("ajax/searchMember")
	@ResponseBody
	public List<MemberVO> searchMembers( MemberVO memberVO, String condition) {
		System.out.println(memberVO);
		System.out.println(condition);
		
		return manageMemberService.getSearchMemberList(memberVO, condition);
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
