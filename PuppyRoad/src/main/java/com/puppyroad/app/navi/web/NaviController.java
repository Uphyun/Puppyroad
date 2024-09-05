package com.puppyroad.app.navi.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppyroad.app.navi.service.NaviService;
import com.puppyroad.app.navi.service.NaviVO;

@Controller
public class NaviController {
	
	@Autowired
	NaviService naviService;
	
	@PostMapping("callNavi")
	@ResponseBody
	public List<NaviVO> callBackNaviAjax(@RequestBody NaviVO naviVO) {
		return naviService.callBackNavi(naviVO);
	}
}
