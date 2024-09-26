package com.puppyroad.app.navi.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppyroad.app.navi.service.NaviService;
import com.puppyroad.app.navi.service.NaviVO;
import com.puppyroad.app.util.SecurityUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NaviController {
	
	@Autowired
	NaviService naviService;

	@GetMapping("user/map")
	public String mapPage(Model model) {
		String memberCode = SecurityUtil.memberCode();
		
		log.info(memberCode);
		
		model.addAttribute("bulletinNo", naviService.getBoardNo(memberCode));
		return "map/map";
	}
	
	@PostMapping("ajax/callNavi")
	@ResponseBody
	public List<NaviVO> callBackNaviAjax(@RequestBody NaviVO naviVO) {
		return naviService.callBackNavi(naviVO);
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
