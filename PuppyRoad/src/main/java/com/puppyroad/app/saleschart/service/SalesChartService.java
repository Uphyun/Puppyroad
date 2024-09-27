package com.puppyroad.app.saleschart.service;

import java.util.List;
import java.util.Map;

public interface SalesChartService {
	
	public List<SalesChartVO> salesChartInfo(SalesChartVO salesChartVO);
	
	public List<SalesVO> salesChartList(String userId);

}
