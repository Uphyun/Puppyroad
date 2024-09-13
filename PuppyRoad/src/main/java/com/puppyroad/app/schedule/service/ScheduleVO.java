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
	

}
