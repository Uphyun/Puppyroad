package com.puppyroad.app.payment.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.puppyroad.app.payment.mapper.PaymentMapper;
import com.puppyroad.app.payment.service.PaymentService;
import com.puppyroad.app.payment.service.PaymentVO;
import com.puppyroad.app.payment.service.VbankVO;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	PaymentMapper paymentMapper;

	@Override
	public Map<String, Object> addPayInfo(PaymentVO paymentVO, VbankVO vbankVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isInfo = false;
		boolean isAddInfo = false;
		
		if(paymentMapper.insertPayInfo(paymentVO) == 1) {
			isInfo = true;
		}
		
		if(vbankVO != null) {
			vbankVO.setPaymentCode(paymentVO.getPaymentCode());
			if(paymentMapper.insertAddVbankInfo(vbankVO) == 1) {
				isAddInfo = true;
			}
		}
		
		map.put("isInfo", isInfo);
		map.put("isAddInfo", isAddInfo);
		map.put("info", paymentVO);
		map.put("addInfo", vbankVO);
		
		return map;
	}

}
