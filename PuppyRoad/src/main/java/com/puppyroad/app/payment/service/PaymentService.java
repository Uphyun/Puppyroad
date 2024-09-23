package com.puppyroad.app.payment.service;

import java.util.Map;

import com.puppyroad.app.match.service.MatchVO;

public interface PaymentService {
	public Map<String, Object> getPayInfo(MatchVO matchVO);
	public Map<String, Object> addPayInfo(PaymentVO paymentVO, VbankVO vbankVO);
}
