package com.puppyroad.app.saleschart.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.puppyroad.app.saleschart.mapper.SalesChartMapper;
import com.puppyroad.app.saleschart.service.SalesChartService;
import com.puppyroad.app.saleschart.service.SalesChartVO;
import com.puppyroad.app.saleschart.service.SalesVO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SalesChartServcieImpl implements SalesChartService {
	
	private SalesChartMapper salesChartMapper ;
	
	@Override
	public List<SalesChartVO> salesChartInfo(SalesChartVO salesChartVO) {
		
		return salesChartMapper.salesChartInfo(salesChartVO);
	}

	@Override
	public List<SalesVO> salesChartList(String userId) {
		
		return salesChartMapper.salesChartList(userId);
	}

	@Override
	public List<SalesVO> detailChartList(SalesVO salesVO) {
		
		return salesChartMapper.detailChartList(salesVO);
	}
	
}
