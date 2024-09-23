package com.puppyroad.app.schedule.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ScheduleVO {
	private String scheduleCode;
	private String clientName;
	private String puppyName;
	private Date walkTime;
	private Integer walkFare;
	private String address;
	private String walkPath;
	private String walkerCode;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date holidayStart;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date holidayEnd;
	private Integer approvalState;
	private String name;
	private String dogName;
	private Date startTime;
	private Date endTime;
	private String phone;
	private String workWeek;
	private String workTime;
	private String scheduleTitle;
	

}
