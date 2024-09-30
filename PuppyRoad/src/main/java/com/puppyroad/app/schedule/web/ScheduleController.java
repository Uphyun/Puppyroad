package com.puppyroad.app.schedule.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	
	//일정표 전체조회
	@GetMapping("user/scheduleListprocess")
	@ResponseBody
	public List<ScheduleVO> scheduleList(ScheduleVO scheduleVO, Model model) {
		
		String mcode = SecurityUtil.memberCode();
		scheduleVO.setWalkerCode(mcode);
		List<ScheduleVO> list = scheduleService.scheduleList(scheduleVO);
		model.addAttribute("lists", list);
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
	
	@PostMapping("/user/scheduleInsert")
	@ResponseBody
	public int scheduleInsert(ScheduleVO scheduleVO) {
		String mcode = SecurityUtil.memberCode();
		scheduleVO.setWalkerCode(mcode);
		return scheduleService.Scheduleinsert(scheduleVO);
	}
	
	@GetMapping("user/SchedulePayList")
	@ResponseBody
	public List<ScheduleVO> SchedulePayList(String recipient){
		String userId = SecurityUtil.userId();
		return scheduleService.payList(userId);
	}
	
}
