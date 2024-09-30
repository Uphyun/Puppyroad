package com.puppyroad.app.payment.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.puppyroad.app.match.service.MatchVO;
import com.puppyroad.app.payment.mapper.PaymentMapper;
import com.puppyroad.app.payment.service.PaymentService;
import com.puppyroad.app.payment.service.PaymentVO;
import com.puppyroad.app.payment.service.VbankVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	PaymentMapper paymentMapper;
	
	@Override
	public Map<String, Object> getPayInfo(MatchVO matchVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		MatchVO info = paymentMapper.selectMatchInfo(matchVO);
		System.err.println(info);
		
		Date endTime = info.getEndTime();
		Date startTime = info.getStartTime();
		
		int min = (int) ((endTime.getTime() - startTime.getTime()) / (1000 * 60));
		
		map.put("info", info);
		map.put("min", min);
		
		return map;
	};

	@Override
	public Map<String, Object> addPayInfo(PaymentVO paymentVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isInfo = false;
		boolean isAddInfo = false;
		boolean isTrue = false;
		
		if(paymentMapper.insertPayInfo(paymentVO) == 1) {
			isInfo = true;
		}
		
		System.err.println("코드 확인 >>> " + paymentVO.getPaymentCode());
		
		if(paymentVO.getVbank_data().getBankAccount() != null) {
			paymentVO.getVbank_data().setPaymentCode(paymentVO.getPaymentCode());
			isTrue = true;
			if(paymentMapper.insertAddVbankInfo(paymentVO.getVbank_data()) == 1) {
				isAddInfo = true;
			}
		}
		
		map.put("isInfo", isInfo);
		map.put("isAddInfo", isAddInfo);
		map.put("info", paymentVO);
		map.put("success", isTrue);
		
		return map;
	}

}
