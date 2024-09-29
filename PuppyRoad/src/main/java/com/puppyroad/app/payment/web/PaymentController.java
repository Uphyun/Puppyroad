package com.puppyroad.app.payment.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppyroad.app.match.service.MatchVO;
import com.puppyroad.app.payment.service.PaymentService;
import com.puppyroad.app.payment.service.PaymentVO;
import com.puppyroad.app.payment.service.VbankVO;

@Controller
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	//결제창
	@GetMapping("user/pay")
	public String paymentPage(Model model, MatchVO matchVO) {
		Map<String, Object> map = paymentService.getPayInfo(matchVO);
		
		MatchVO info = (MatchVO) map.get("info");
		int min = (int) map.get("min");
		
		model.addAttribute("info", info);
		model.addAttribute("min", min);
		
		return "payment/payment";
	}
	
	//결제 내역 등록
	@PostMapping("ajax/pay")
	@ResponseBody
	public Map<String, Object> addPayAjax(@RequestBody PaymentVO paymentVO) {
		System.err.println("커스텀");
		System.err.println(paymentVO);
		return paymentService.addPayInfo(paymentVO);
	}

	//가상계좌 업그레이드
	@PostMapping("ajax/payback")
	@ResponseBody
	public Map<String, Object> payBack(@RequestBody PaymentVO paymentVO) {
		System.err.println("페이백");
		System.err.println(paymentVO);
		Map<String, Object> map = paymentService.addPayInfo(paymentVO);
		
		return map;
	}
	

}
