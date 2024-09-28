package com.puppyroad.app.saleschart.mapper;

import java.util.List;

import com.puppyroad.app.saleschart.service.SalesChartVO;
import com.puppyroad.app.saleschart.service.SalesVO;

public interface SalesChartMapper {
	
	public List<SalesChartVO> salesChartInfo(SalesChartVO salesChartVO);
	public List<SalesVO> salesChartList (String userId);
	//상세정보 리스트
	public List<SalesVO> detailChartList(SalesVO salesVO);
}
