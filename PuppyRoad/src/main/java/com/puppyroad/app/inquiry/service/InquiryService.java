package com.puppyroad.app.inquiry.service;

import java.util.List;
import java.util.Map;


public interface InquiryService {
	
	// 전체 조회
	
	public List<InquiryVO> getInquiryList(InquiryVO inquiryVO);
	// 단건조회
	public InquiryVO getInquiryInfo(InquiryVO inquiryVO);
	
	// 등록
	public int addInquiry(InquiryVO inquiryVO);
	
	// 수정
	public Map<String, Object> modifyInquiry(InquiryVO inquiryVO);
	
	// 삭제
	public int removeInquiry(int ino);
}
