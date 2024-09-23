package com.puppyroad.app.saleschart.service;

import java.util.Date;

import lombok.Data;

@Data
public class SalesChartVO {
	
	private String paymentCode;
	private String sender;
	private String recipient;
	private String method;
	private Integer price;
	private String orderName;
	private Date pucharsedAt;
	private Integer status;
	
}
