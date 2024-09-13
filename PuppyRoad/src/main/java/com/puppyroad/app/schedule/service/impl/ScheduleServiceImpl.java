package com.puppyroad.app.schedule.service.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleServiceImpl {
	@GetMapping("user/schedule")
	public String schedule() {
		return "schedule/schedule";
	}
	

}
