package com.puppyroad.app.payment.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppyroad.app.payment.service.PaymentService;
import com.puppyroad.app.payment.service.PaymentVO;
import com.puppyroad.app.payment.service.VbankVO;

@Controller
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	

	@GetMapping("user/pay")
	public String paymentPage() {
		return "payment/payment";
	}
	
	@PostMapping("ajax/pay")
	@ResponseBody
	public Map<String, Object> addPayAjax(@RequestBody PaymentVO paymentVO, VbankVO vbankVO, String orderId) {
		paymentVO.setPaymentCode(orderId);
		return paymentService.addPayInfo(paymentVO, vbankVO);
	}

	@PutMapping("ajax/payback")
	public Map<String, Object> payBack() {
				//PaymentVO paymentVO) {
		System.err.println("-------------------------여기로 옴 ---------------------");
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		map.put("success", true);
		return map;
	}
	

}
