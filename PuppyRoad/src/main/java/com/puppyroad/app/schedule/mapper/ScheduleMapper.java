package com.puppyroad.app.schedule.mapper;

import java.util.List;

import com.puppyroad.app.schedule.service.ScheduleVO;

public interface ScheduleMapper {
	
	//스케줄조회
	public List<ScheduleVO> ScheduleList(ScheduleVO scheduleVO);
	//휴가수정
	public int ScheduleUpdate(ScheduleVO scheduleVO);
	//휴가추가
	public int ScheduleInsert(ScheduleVO scheduleVO);
	//결제일
	public List<ScheduleVO> payList(String recipient);
}
