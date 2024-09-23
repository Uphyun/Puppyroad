package com.puppyroad.app.schedule.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.puppyroad.app.schedule.mapper.ScheduleMapper;
import com.puppyroad.app.schedule.service.ScheduleService;
import com.puppyroad.app.schedule.service.ScheduleVO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
	
	private ScheduleMapper scheduleMapper;
	
	@Override
	public List<ScheduleVO> scheduleList(ScheduleVO scheduleVO) {
		
		return scheduleMapper.ScheduleList(scheduleVO);
	}

	@Override
	public int ScheduleUpdate(ScheduleVO scheduleVO) {
		
		return scheduleMapper.ScheduleUpdate(scheduleVO);
	}

	@Override
	public int Scheduleinsert(ScheduleVO scheduleVO) {
		
		return scheduleMapper.ScheduleInsert(scheduleVO);
	}
	

	

}
