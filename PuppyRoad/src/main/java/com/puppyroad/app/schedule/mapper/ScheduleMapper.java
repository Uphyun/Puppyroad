package com.puppyroad.app.schedule.mapper;

import java.util.List;

import com.puppyroad.app.schedule.service.ScheduleVO;

public interface ScheduleMapper {
	
	//스케줄조회
	public List<ScheduleVO> ScheduleList(ScheduleVO scheduleVO);
	//휴가추가
	public int ScheduleUpdate(ScheduleVO scheduleVO);
}
