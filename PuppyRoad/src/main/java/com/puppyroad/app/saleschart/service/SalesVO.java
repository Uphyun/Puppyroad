package com.puppyroad.app.saleschart.service;

import java.util.Date;

import lombok.Data;

@Data
public class SalesVO {
	
	private Integer price;
	private String month;
	private String paymentCode;
	private String sender;
	private String recipient;
	private String orderName;
	private Date purchasedAt;
	private String name;
	private String monthDetail;
}
