package com.puppyroad.app.schedule.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date holidayStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
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
	private String paymentCode;
	private String sender;
	private String recipient;
	private int price;
	private String orderName;
	private String method;
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date purchasedAt;
	private Date cancelledAt;
	private Date requestedAt;
	private int status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date nextFriday;
	private Integer totalPrice;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date monday;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date sunday;

}
