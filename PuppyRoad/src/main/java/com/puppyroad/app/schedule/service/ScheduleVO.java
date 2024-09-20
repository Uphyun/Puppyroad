package com.puppyroad.app.schedule.service;

import java.util.Date;

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
	private Date holidayStart;
	private Date holidayEnd;
	private Integer approvalState;
	private String name;
	private String dogName;
	private Date startTime;
	private Date endTime;
	private String phone;
	private String workWeek;
	private String workTime;
	

}
