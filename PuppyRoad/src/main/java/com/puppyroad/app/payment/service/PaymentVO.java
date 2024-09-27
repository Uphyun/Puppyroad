package com.puppyroad.app.payment.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PaymentVO {
	private String paymentCode;
	private String payment_code;
	private String sender;
	private String recipient;
	private int price;
	private String orderName;
	private String order_name;
	private String method;
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy/MM/dd HH:mm")
	private Date purchasedAt;
	private Date purchased_at;
	private Date cancelledAt;
	private Date cancelled_at;
	private Date requestedAt;
	private Date requested_at;
	private int status;
	private String name;

	void setPayment_code(String payment_code) {
		this.payment_code = payment_code;
		this.paymentCode = payment_code;
	}
	
	void setOrder_name(String order_name) {
		this.order_name = order_name;
		this.orderName = order_name;
	}
	
	void setPurchased_at(Date purchased_at) {
		this.purchased_at = purchased_at;
		this.purchasedAt = purchased_at;
	}
	
	void setCancelled_at(Date cancelled_at) {
		this.cancelled_at = cancelled_at;
		this.cancelledAt = cancelled_at;
	}
	
	void setRequested_at(Date requested_at) {
		this.requested_at = requested_at;
		this.requestedAt = requested_at;
	}
	
}
