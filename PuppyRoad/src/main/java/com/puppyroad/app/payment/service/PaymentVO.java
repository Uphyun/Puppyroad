package com.puppyroad.app.payment.service;

import java.util.Date;

import lombok.Data;

@Data
public class PaymentVO {
	private String paymentCode;
	private String sender;
	private String recipient;
	private int price;
	private String order_name;
	private String method;
	private Date purchasedAt;
	private Date cancelledAt;
	private Date requestedAt;
	private int status;
}
