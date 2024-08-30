package com.puppyroad.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.puppyroad.app.member.mapper.MemberMapper;
import com.puppyroad.app.member.service.MemberVO;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@EnableEncryptableProperties
@SpringBootTest
class LshTest {
	@Autowired
	MemberMapper memberMapper;
	@Test
	void insertMember() {
		MemberVO memberVO = new MemberVO();
		memberVO.setUserId("SHYUN8");
		memberVO.setUserPw("1234");
		memberVO.setNickName("천채");
		memberVO.setName("이상현");
		memberVO.setAddress("서울 강남");
		memberVO.setEmail("bb@navr.com");
		memberVO.setPhone("010-5555-7777");
		memberVO.setPosition("봉사인");
		memberVO.setIntention("그냥 내맘이잖아?");
		
		
		int result = memberMapper.insertMember(memberVO);
		assertEquals(1, result);
	}
	
	
	

}
