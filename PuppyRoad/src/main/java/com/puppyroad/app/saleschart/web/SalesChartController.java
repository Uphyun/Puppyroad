package com.puppyroad.app.saleschart.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.puppyroad.app.saleschart.service.SalesChartService;
import com.puppyroad.app.saleschart.service.SalesChartVO;
import com.puppyroad.app.util.SecurityUtil;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class SalesChartController {
	
	private SalesChartService salesChartService ;
	
	@GetMapping("user/salesChartInfo")
	public String salesChartInfo(SalesChartVO salesChartVO) {
		String mcode = SecurityUtil.memberCode();
		salesChartVO.setRecipient(mcode);
		salesChartService.salesChartInfo(salesChartVO);
		return "saleschart/salesChartInfo";
	}
}
