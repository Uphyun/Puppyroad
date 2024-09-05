package com.puppyroad.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.puppyroad.app.navi.mapper.NaviMapper;
import com.puppyroad.app.navi.service.NaviVO;

@SpringBootTest
class YmcTest {
	
	@Autowired
	NaviMapper naviMapper;

	@Test
	@Transactional
	void prodTest() {
		NaviVO nvo = new NaviVO();
		nvo.setTurnNo(1);
		nvo.setX(128.593400);
		nvo.setY(35.8691089);
		nvo.setPuppyCode("test01");
		
		naviMapper.setCallDogNavi(nvo);
		
		List<NaviVO> list = nvo.getCNavi();
		
		
		System.out.println(list);
		
		assertEquals(2, list.size());
	}
	

}
