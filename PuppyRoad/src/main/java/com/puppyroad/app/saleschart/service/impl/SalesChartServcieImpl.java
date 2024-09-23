package com.puppyroad.app.saleschart.service.impl;

import org.springframework.stereotype.Service;

import com.puppyroad.app.saleschart.mapper.SalesChartMapper;
import com.puppyroad.app.saleschart.service.SalesChartService;
import com.puppyroad.app.saleschart.service.SalesChartVO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SalesChartServcieImpl implements SalesChartService {
	
	private SalesChartMapper salesChartMapper ;
	
	@Override
	public SalesChartVO salesChartInfo(SalesChartVO salesChartVO) {
		
		return salesChartMapper.salesChartInfo(salesChartVO);
	}
	
}
