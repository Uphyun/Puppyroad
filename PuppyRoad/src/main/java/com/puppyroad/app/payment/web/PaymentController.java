package com.puppyroad.app.payment.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {

	@GetMapping("user/pay")
	public String paymentPage() {
		
		return "payment/payment";
	}
}
