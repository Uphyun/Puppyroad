package com.puppyroad.app.saleschart.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppyroad.app.saleschart.service.SalesChartService;
import com.puppyroad.app.saleschart.service.SalesChartVO;
import com.puppyroad.app.saleschart.service.SalesVO;
import com.puppyroad.app.util.SecurityUtil;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class SalesChartController {
	
	private SalesChartService salesChartService ;
	
	//매출내역 조회
	@GetMapping("user/salesChartInfoProcess")
	@ResponseBody
	public List<SalesVO> salesChartInfoProcess(String uid) {
		String uId = SecurityUtil.userId();
		//List<SalesVO> list2 = salesChartService.salesChartList(uId);
		//System.err.println(list2);
		
		return salesChartService.salesChartList(uId);
		 
	}
	//매출내역페이지
	@GetMapping("user/salesChartInfo")
	public String salesChartInfo(SalesChartVO salesChartVO) {
		String mcode = SecurityUtil.memberCode();
		salesChartVO.setRecipient(mcode);
		salesChartService.salesChartInfo(salesChartVO);
		return "saleschart/salesChartInfo";
	}
	//매출내역 상세정보
	@GetMapping("user/detailChart")
	@ResponseBody
	public List<SalesVO> detailChartList(SalesVO salesVO){
		String uId = SecurityUtil.userId();
		salesVO.setRecipient(uId);
		 System.out.println("Received month: " + salesVO.getMonthDetail()); 
		return salesChartService.detailChartList(salesVO);
	}
	
}
