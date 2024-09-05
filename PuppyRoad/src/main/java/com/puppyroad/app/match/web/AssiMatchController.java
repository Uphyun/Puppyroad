package com.puppyroad.app.match.web;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppyroad.app.match.service.AssiMatchService;
import com.puppyroad.app.match.service.AssiMatchVO;
import com.puppyroad.app.match.service.MatchService;
import com.puppyroad.app.match.service.MatchVO;

@Controller
public class AssiMatchController {
	
	@Autowired
	AssiMatchService assiService;
	
	// 전체조회 : get
	@GetMapping("assiList")
	public String assiList(Model model) {
		List<AssiMatchVO> list = assiService.getAssiMatchList();
		model.addAttribute("assis", list);
		return "match/assiList";
	}
	
	// 단건조회 : get
	@GetMapping("assiInfo")
	public String assiInfo(AssiMatchVO matchVO, Model model) {
		AssiMatchVO findVO = assiService.getAssiMatchInfo(matchVO);
		model.addAttribute("match", findVO);
		return "match/assiInfo";
	}
	
	// 등록 - 페이지 : get
	@GetMapping("assiInsert")
	public String assiInsertForm() {
		return "match/assiInsert";
	}
	
	// 등록 - 처리 : post
	@PostMapping("assiInsert")
	public String assiInsertProcess(AssiMatchVO assiVO) {
		int bno = assiService.addAssiMatch(assiVO);
		String url = null;
		if(bno > -1) {
			url = "redirect:assiInfo?bulletinNo=" + bno;
		} else {
			url = "redirect:assiList";
		}
		return url;
	}
	
	// 수정 - 페이지
	@GetMapping("assiUpdate")
	public String assiUpdateForm(AssiMatchVO assiVO, Model model) {
		AssiMatchVO findVO = assiService.getAssiMatchInfo(assiVO);
		model.addAttribute("assi", findVO);
		return "match/assiUpdate";
	}
	// 수정 - 처리
	@PostMapping("assiUpdate")
	@ResponseBody // AJAX
	public Map<String, Object> assiUpdate(@RequestBody AssiMatchVO assiVO){
		return assiService.modifyAssiMatch(assiVO);
		
	}
	
	// 삭제 - 처리
	@GetMapping("assiDelete")
	public String assiDelete(Integer bulletinNo) {
		assiService.removeAssiMatch(bulletinNo);
		return "redirect:assiList";
	}
}
