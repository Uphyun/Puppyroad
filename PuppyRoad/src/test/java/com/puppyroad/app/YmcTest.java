package com.puppyroad.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YmcTest {
	

	@Test
	void prodTest() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date day1 = sdf.parse("2024-09-23 09:00:00");
		Date day2 = sdf.parse("2024-09-23 11:00:00");
		
		System.err.println((day2.getTime() - day1.getTime()) / (1000 * 60));
		
		assertEquals("1", "1");
	}
	

}
