package com.puppyroad.app.navi.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppyroad.app.match.service.MatchVO;
import com.puppyroad.app.navi.service.NaviService;
import com.puppyroad.app.navi.service.NaviVO;
import com.puppyroad.app.util.SecurityUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NaviController {
	
	@Autowired
	NaviService naviService;

	@GetMapping("user/walkMap")
	public String walkMapPage(Model model, String bulletinNo) {
		log.info(bulletinNo);
		
		model.addAttribute("puppyList", naviService.getPuppyList(bulletinNo));
		
		return "map/walkMap";
	}

	@GetMapping("user/matchMap")
	public String matchMapPage(Model model) {
		String memberCode = SecurityUtil.memberCode();
		
		log.info(memberCode);
		
		model.addAttribute("bulletinNo", naviService.getBoardNo(memberCode));
		
		return "map/matchMap";
	}
	
	@PostMapping("ajax/callNavi")
	@ResponseBody
	public Map<String, Object> callBackNaviAjax(@RequestBody NaviVO naviVO) {
		return naviService.callBackNavi(naviVO);
	}
	
	@PostMapping("ajax/markInfo")
	@ResponseBody
	public MatchVO markInfoAjax(@RequestBody MatchVO matchVO) {
		System.err.println(matchVO);
		return naviService.getMarkerInfo(matchVO.getBulletinNo());
	}
	
	@PostMapping("ajax/setMatch")
	@ResponseBody
	public Map<String, Object> setMatchAjax(@RequestBody NaviVO naviVO) {
		return naviService.setMatching(naviVO);
	}
	
	@GetMapping("ajax/endMatch")
	@ResponseBody
	public String endMatchAjax(int matchCode) {
		System.out.println(matchCode);
		return naviService.endMatching(matchCode);
	}
}
