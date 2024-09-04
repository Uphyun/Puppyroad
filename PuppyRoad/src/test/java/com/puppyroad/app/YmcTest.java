package com.puppyroad.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.puppyroad.app.navi.mapper.NaviMapper;
import com.puppyroad.app.navi.service.NaviVO;

@SpringBootTest
class YmcTest {
	
	@Autowired
	NaviMapper naviMapper;

	@Test
	void prodTest() {
		NaviVO nvo = new NaviVO();
		nvo.setTurnNo(0);
		nvo.setX(128.593387);
		nvo.setY(35.8691089);
		nvo.setPuppyCode("test01");
		
		List<NaviVO> list = naviMapper.setCallDogNavi(nvo);
		
		assertEquals(0, list.get(0).getTurnNo());
	}
	

}
