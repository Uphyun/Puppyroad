package com.puppyroad.app.walkjournal.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.puppyroad.app.util.SecurityUtil;
import com.puppyroad.app.walkjournal.service.WalkJournalService;
import com.puppyroad.app.walkjournal.service.WalkJournalVO;

@Controller
public class WalkJournalController {
	
	private WalkJournalService walkJournalService;
	
	@Autowired
	public WalkJournalController(WalkJournalService walkJournalService) {
		
		this.walkJournalService = walkJournalService;
	}
	
	//산책일지 리스트
	@GetMapping("user/walkJournalList")
	public String walkJournalList(Model model) {
		
		List<WalkJournalVO> list = walkJournalService.WalkJournalList();
		
		model.addAttribute("list", list);
		
		return "walkJournal/walkJournalList";
		
	}
	
	//산책일지 단건조회
	@GetMapping("user/walkJournalGetInfo")
	public String walkJournalGetInfo(WalkJournalVO walkJournalVO, Model model) {
		
		WalkJournalVO result = walkJournalService.WalkJournalGetInfo(walkJournalVO);
		model.addAttribute("result", result);
		
		return "walkJournal/walkJournalInfo";
	}
	
	//도그워커 산책일지 조회
	@GetMapping("user/dogwalkJournalList")
	public String dogwalkJournalList(WalkJournalVO walkJournalVO, Model model) {
		String mcode = SecurityUtil.memberCode();
		walkJournalVO.setWalkerCode(mcode);
		List<WalkJournalVO> list = walkJournalService.dogWalkJournalList(walkJournalVO);
		
		model.addAttribute("list", list);
		
		return "walkJournal/dogWalkJournalList"; 
	}
	
	//산책일지 단건조회
	@GetMapping("user/dogwalkJournalGetInfo")
	public String dogWalkJournalGetInfo(WalkJournalVO walkJournalVO, Model model) {
		String mcode = SecurityUtil.memberCode();
		walkJournalVO.setWalkerCode(mcode);
		WalkJournalVO result = walkJournalService.dogWalkJournalGetInfo(walkJournalVO);
		model.addAttribute("result", result);
		
		return "walkJournal/dogWalkJournalInfo";
	}
	
	
	
	
	
	
}//end
