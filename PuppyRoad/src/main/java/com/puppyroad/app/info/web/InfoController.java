package com.puppyroad.app.info.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.puppyroad.app.info.service.InfoService;
import com.puppyroad.app.info.service.InfoVO;

@Controller
public class InfoController {
	
	@Autowired
	InfoService service;
	
	@RequestMapping("/infoList")
	public String infoList(Model model) {
		List<InfoVO> list = service.infoList();
		model.addAttribute("infos",list);
		return "info/infoList";
	}
}
