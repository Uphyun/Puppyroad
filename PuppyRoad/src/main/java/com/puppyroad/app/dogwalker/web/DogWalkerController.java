package com.puppyroad.app.dogwalker.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.puppyroad.app.dogwalker.service.DogWalkVO;
import com.puppyroad.app.dogwalker.service.DogWalkerSerivce;

@Controller
public class DogWalkerController {
	
	private DogWalkerSerivce dogWalkerSerivce;
	
	@Autowired
	public DogWalkerController(DogWalkerSerivce dogWalkerSerivce) {
		this.dogWalkerSerivce = dogWalkerSerivce;
	}
	
	//도그워커 프로필 - 페이지
	@GetMapping("user/insertWalker")
	public String insertWalkerPage() {
		
		return "walker/insertWalker";
	}
	//도그워커 프로필 - 페이지
	@PostMapping("user/insertWalker")
	public String insertWalkerProcess(DogWalkVO dogWalkVO) {
		int result = dogWalkerSerivce.walkerinsert(dogWalkVO);
		String comment = "";
		if(result == 1) {
			
		  comment = "success";
		  
		}else {
			
			comment = "fail";
		}
		
		return comment;
		
	}

}
