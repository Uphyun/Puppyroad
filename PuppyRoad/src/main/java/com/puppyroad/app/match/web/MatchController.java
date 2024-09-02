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

import com.puppyroad.app.match.service.MatchService;
import com.puppyroad.app.match.service.MatchVO;

@Controller
public class MatchController {
	
	@Autowired
	MatchService matchService;
	
	// 전체조회 : get
	@GetMapping("matchList")
	public String matchList(Model model) {
		List<MatchVO> list = matchService.getMatchList();
		model.addAttribute("matchs", list);
		return "match/matchList";
	}
	
	// 단건조회 : get
	@GetMapping("matchInfo")
	public String matchInfo(MatchVO matchVO, Model model) {
		MatchVO findVO = matchService.getMatchInfo(matchVO);
		model.addAttribute("match", findVO);
		return "match/matchInfo";
	}
	
	// 등록 - 페이지 : get
	@GetMapping("matchInsert")
	public String matchInsertForm() {
		return "match/matchInsert";
	}
	
	// 등록 - 처리 : post
	@PostMapping("matchInsert")
	public String matchInsertProcess(MatchVO matchVO) {
		int bno = matchService.addMatch(matchVO);
		String url = null;
		if(bno > -1) {
			url = "redirect:matchInfo?bulletinNo=" + bno;
		} else {
			url = "redirect:matchList";
		}
		return url;
	}
	
	// 수정 - 페이지
	@GetMapping("matchUpdate")
	public String matchUpdateForm(MatchVO matchVO, Model model) {
		MatchVO findVO = matchService.getMatchInfo(matchVO);
		model.addAttribute("match", findVO);
		return "match/matchUpdate";
	}
	// 수정 - 처리
	@PostMapping("matchUpdate")
	@ResponseBody // AJAX
	public Map<String, Object> matchUpdate(@RequestBody MatchVO matchVO){
		return matchService.modifyMatch(matchVO);
		
	}
	
	// 삭제 - 처리
	@GetMapping("matchDelete")
	public String matchDelete(Integer bulletinNo) {
		matchService.removeMatch(bulletinNo);
		return "redirect:matchList";
	}
}
