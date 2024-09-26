package com.puppyroad.app.payment.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PaymentVO {
	private String paymentCode;
	private String sender;
	private String recipient;
	private String price;
	private String order_name;
	private String method;
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy/MM/dd HH:mm")
	private Date purchasedAt;
	private Date cancelledAt;
	private Date requestedAt;
	private int status;
}
