package com.puppyroad.app.payment.service;

import java.util.Date;

import lombok.Data;

@Data
public class VbankVO {
	private String bankCode;
	private String bankName;
	private String bankAccount;
	private String bankUsername;
	private Date expiredAt;
	private String paymentCode;
}
