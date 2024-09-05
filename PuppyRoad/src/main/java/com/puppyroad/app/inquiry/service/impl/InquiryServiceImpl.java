package com.puppyroad.app.inquiry.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puppyroad.app.inquiry.mapper.InquiryMapper;
import com.puppyroad.app.inquiry.service.InquiryService;
import com.puppyroad.app.inquiry.service.InquiryVO;

@Service
public class InquiryServiceImpl implements InquiryService{
	private InquiryMapper inquiryMapper;
	
	@Autowired
	InquiryServiceImpl(InquiryMapper inquiryMapper){
		this.inquiryMapper= inquiryMapper;
	}
	
	// 전체조회
	@Override
	public List<InquiryVO> getInquiryList(InquiryVO inquriyVO) {
		return inquiryMapper.selectInquiryList(inquriyVO);
	}
	
	// 단건조회
	@Override
	public InquiryVO getInquiryInfo(InquiryVO inquiryVO) {
		return inquiryMapper.selectInquiryInfo(inquiryVO);
	}

	// 등록
	@Override
	public int addInquiry(InquiryVO inquiryVO) {
		int result = inquiryMapper.insertInquiry(inquiryVO);
		return result == 1 ? inquiryVO.getBulletinNo()  : -1;
	}

	// 수정
	@Override
	public Map<String, Object> modifyInquiry(InquiryVO inquiryVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = inquiryMapper.updateInquiry(inquiryVO);
		if(result == 1) {
			isSuccessed = true;
		}
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String updateDate = sdf.format(today);
		
		map.put("date", updateDate);
		map.put("result", isSuccessed);
		map.put("target", inquiryVO);
		
		return map;
	}

	// 삭제
	@Override
	public int removeInquiry(int ino) {
		return inquiryMapper.deleteInquriy(ino);
	}

}
