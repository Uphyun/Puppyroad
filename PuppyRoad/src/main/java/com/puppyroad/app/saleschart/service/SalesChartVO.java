package com.puppyroad.app.saleschart.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SalesChartVO {
	
	private String paymentCode;
	private String sender;
	private String recipient;
	private String method;
	private Integer price;
	private String orderName;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
	private Date purchasedAt;
	private Integer status;
	
}
