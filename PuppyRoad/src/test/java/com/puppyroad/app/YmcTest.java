package com.puppyroad.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.puppyroad.app.admin.main.mapper.AdminMainMapper;
import com.puppyroad.app.navi.mapper.NaviMapper;
import com.puppyroad.app.navi.service.NaviVO;

@SpringBootTest
class YmcTest {
	
	@Autowired
	AdminMainMapper adminMapper;

	@Test
	void prodTest() {
		assertEquals(14, adminMapper.getWalkerCnt());
	}
	

}
