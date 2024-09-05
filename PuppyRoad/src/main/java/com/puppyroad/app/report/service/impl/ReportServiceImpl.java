package com.puppyroad.app.report.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puppyroad.app.report.mapper.ReportMapper;
import com.puppyroad.app.report.service.ReportService;
import com.puppyroad.app.report.service.ReportVO;

@Service
public class ReportServiceImpl implements ReportService {
	private ReportMapper reportMapper;
	
	@Autowired
	ReportServiceImpl(ReportMapper reportMapper){
		this.reportMapper = reportMapper;
	}
	
	// 전체조회
	@Override
	public List<ReportVO> getReportList(ReportVO reportVO) {
		return reportMapper.selectReportList(reportVO);
	}
	
	// 단건조회
	@Override
	public ReportVO getReportInfo(ReportVO reportVO) {
		return reportMapper.selectReportInfo(reportVO);
	}
	
	// 등록
	@Override
	public int addReport(ReportVO reportVO) {
		int result = reportMapper.insertReport(reportVO);
		return result == 1 ? reportVO.getBulletinNo() : -1;
	}
	
	// 수정
	@Override
	public Map<String, Object> modifyReport(ReportVO reportVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = reportMapper.updateReport(reportVO);
		if(result == 1) {
			isSuccessed = true;
		}
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String updateDate = sdf.format(today);
		
		map.put("date", updateDate);
		map.put("result", isSuccessed);
		map.put("target", reportVO);
		
		return map;
	}
	
	// 삭제
	@Override
	public int removeReport(int rno) {
		return reportMapper.deleteReport(rno);
	}

}
