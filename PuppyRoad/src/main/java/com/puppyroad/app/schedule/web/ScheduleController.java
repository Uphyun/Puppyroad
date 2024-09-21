package com.puppyroad.app.schedule.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppyroad.app.schedule.service.ScheduleService;
import com.puppyroad.app.schedule.service.ScheduleVO;
import com.puppyroad.app.util.SecurityUtil;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ScheduleController {
	
	private ScheduleService scheduleService;
	
	@GetMapping("user/scheduleListprocess")
	@ResponseBody
	public List<ScheduleVO> scheduleList(ScheduleVO scheduleVO) {
		
		String mcode = SecurityUtil.memberCode();
		scheduleVO.setWalkerCode(mcode);
		List<ScheduleVO> list = scheduleService.scheduleList(scheduleVO);
		return list;
	}
	
	@GetMapping("user/scheduleList")
	public String scheduleListPage() {
		
		return "schedule/scheduleList";
	}
	
	@PostMapping("user/scheduleUpdate")
	@ResponseBody
	public int scheduleUpdate(ScheduleVO scheduleVO) {
		
		String mcode = SecurityUtil.memberCode();
		scheduleVO.setWalkerCode(mcode);
		return scheduleService.ScheduleUpdate(scheduleVO);
	}
	
}
