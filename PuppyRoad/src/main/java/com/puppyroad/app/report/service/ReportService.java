package com.puppyroad.app.report.service;

import java.util.List;
import java.util.Map;


public interface ReportService {
		// 전체 조회
		public List<ReportVO> getReportList(ReportVO reportVO);
		
		// 단건조회
		public ReportVO getReportInfo(ReportVO reportVO);
		
		// 등록
		public int addReport(ReportVO reportVO);
		
		// 수정
		public Map<String, Object> modifyReport(ReportVO reportVO);
		
		// 삭제
		public int removeReport(int rno);
}
