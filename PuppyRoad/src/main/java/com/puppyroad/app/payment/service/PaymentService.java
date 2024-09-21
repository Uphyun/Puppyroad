package com.puppyroad.app.payment.service;

import java.util.Map;

public interface PaymentService {
	public Map<String, Object> addPayInfo(PaymentVO paymentVO, VbankVO vbankVO);
}
