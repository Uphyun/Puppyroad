package com.puppyroad.app.schedule.service;

import java.util.List;

public interface ScheduleService {
	
	//스케줄조회
	public List<ScheduleVO> scheduleList(ScheduleVO scheduleVO);
	//휴가추가
	public int ScheduleUpdate(ScheduleVO scheduleVO);
}
