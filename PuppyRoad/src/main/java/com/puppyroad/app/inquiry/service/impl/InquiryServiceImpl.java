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
	public InquiryVO getInquiryInfo(InquiryVO inquriyVO) {
		return inquiryMapper.selectInquiryInfo(inquriyVO);
	}

	// 등록
	@Override
	public int addInquiry(InquiryVO inquriyVO) {
		int result = inquiryMapper.insertInquiry(inquriyVO);
		return result == 1 ? inquriyVO.getBulletinNo()  : -1;
	}

	// 수정
	@Override
	public Map<String, Object> modifyInquiry(InquiryVO inquriyVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = inquiryMapper.updateInquiry(inquriyVO);
		if(result == 1) {
			isSuccessed = true;
		}
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String updateDate = sdf.format(today);
		
		map.put("date", updateDate);
		map.put("result", isSuccessed);
		map.put("target", inquriyVO);
		
		return map;
	}

	// 삭제
	@Override
	public int removeInquiry(int ino) {
		return inquiryMapper.deleteInquiry(ino);
	}

	

}
