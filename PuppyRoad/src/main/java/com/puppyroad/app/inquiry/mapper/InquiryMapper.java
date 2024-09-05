package com.puppyroad.app.inquiry.mapper;

import java.util.List;

import com.puppyroad.app.inquiry.service.InquiryVO;

public interface InquiryMapper {
	// 전체 조회
	public List<InquiryVO> selectInquiryList(InquiryVO inquriyVO);
	
	// 단건 조회
	public InquiryVO selectInquiryInfo(InquiryVO inquriyVO);
	
	// 단건 등록
	public int insertInquiry(InquiryVO inquriyVO);
	
	// 수정
	public int updateInquiry(InquiryVO inquriyVO);
	
	// 삭제
	public int deleteInquriy(int iNo);
}
