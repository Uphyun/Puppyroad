package com.puppyroad.app.assi.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppyroad.app.assi.service.AssiService;
import com.puppyroad.app.assi.service.AssiVO;
import com.puppyroad.app.assi.service.WalkerAddInfoVO;
import com.puppyroad.app.match.service.MatchService;
import com.puppyroad.app.util.SecurityUtil;

@Controller
public class AssiController {

	@Autowired
	AssiService assiService;
	
	@Autowired
	MatchService matchService;
	
	// 전체조회 : get
	@GetMapping("user/assiList")
	public String assiList(AssiVO assiVO, Model model) {
		List<AssiVO> list = assiService.getAssiList(assiVO);
		model.addAttribute("assis", list);
		return "assi/assiList";
	}
	
	// 단건조회 : get
	@GetMapping("user/assiInfo")
	@Transactional
	public String assiInfo(AssiVO assiVO, Model model) {
		AssiVO findVO = assiService.getAssiInfo(assiVO);
		String writer = findVO.getWriter();
		
		WalkerAddInfoVO findVO2 = assiService.getWalkerInfo(writer);
		
		model.addAttribute("assi", findVO);
		model.addAttribute("walker", findVO2);
		
		return "assi/assiInfo";
		
	}
	
	// 등록 - get
	@GetMapping("user/assiInsert")
	public String assiInsertForm(Model model) {
		String mcode = SecurityUtil.memberCode();
		WalkerAddInfoVO findVO = assiService.getMyWalkerInfo(mcode);
		
		model.addAttribute("myPro" ,findVO);
		
		return "assi/assiInsert";
	}
	
	// 등록 - post
	@PostMapping("user/assiInsert")
	@ResponseBody
	public int assiInsertProcess(AssiVO assiVO) {
		String mcode = SecurityUtil.memberCode();
		assiVO.setWriter(mcode);
		int bno = assiService.addAssiInfo(assiVO);
		
		return bno;
	}
	
	// 수정 - get
	@GetMapping("user/assiUpdate")
	public String assiUpdateForm() {
		
		return "assi/assiUpdate";
	}
	
	// 수정 - 처리
	@PostMapping("user/assiUpdate")
	@ResponseBody // AJAX
	public Map<String, Object> assiUpdate(){
		
		return null;
	}
	
	
	// 단건삭제
	@GetMapping("user/assiDelete")
	public String assiDelete(Integer bulletinNo) {
		matchService.removeMatch(bulletinNo);
		return "redirect:/user/assiList";
	}
	
 }
