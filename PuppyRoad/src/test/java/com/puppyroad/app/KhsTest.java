package com.puppyroad.app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import com.puppyroad.app.inquiry.mapper.InquiryMapper;
import com.puppyroad.app.inquiry.service.InquiryVO;
import com.puppyroad.app.news.service.NewsVO;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@Configuration
@EnableEncryptableProperties
@SpringBootTest
class KhsTest {
	@Autowired
	InquiryMapper inquiryMapper;

	// 전체조회
	 @Test
	void newsList() {
		 InquiryVO inquiryVO = new InquiryVO();
		List<InquiryVO> list = inquiryMapper.selectInquiryList(inquiryVO);
		assertTrue(!list.isEmpty());
	}

	
	

}
