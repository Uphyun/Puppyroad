package com.puppyroad.app.report.mapper;

import java.util.List;

import com.puppyroad.app.report.service.ReportVO;

public interface ReportMapper {
		// 전체 조회
		public List<ReportVO> selectReportList(ReportVO reportVO);

		// 단건 조회
		public ReportVO selectReportInfo(ReportVO reportVO);

		// 단건 등록
		public int insertReport(ReportVO reportVO);

		// 수정
		public int updateReport(ReportVO reportVO);

		// 삭제
		public int deleteReport(int rNo);
}
