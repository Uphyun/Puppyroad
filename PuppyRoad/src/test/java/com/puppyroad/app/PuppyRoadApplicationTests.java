package com.puppyroad.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.puppyroad.app.news.mapper.NewsMapper;
import com.puppyroad.app.news.service.NewsVO;

@SpringBootTest
class PuppyRoadApplicationTests {
	@Autowired
	NewsMapper newsMapper;

	// 전체조회
	// @Test
	void newsList() {
		List<NewsVO> list = newsMapper.selectNewsList();
		assertTrue(!list.isEmpty());
	}

	// 단건조회
	// @Test
	void newsInfo() {
		NewsVO newsVO = new NewsVO();
		newsVO.setBulletinNo(1);

		NewsVO findVO = newsMapper.selectNewsInfo(newsVO);
		assertEquals("title", findVO.getTitle());
	}

	// 등록
	// @Test
	void newsInsert() {
		NewsVO newsVO = new NewsVO();
		newsVO.setTitle("Test test");
		newsVO.setContent("test");
		newsVO.setWriter("Hong");
		newsVO.setAttachedFile("image");
		newsVO.setBulletinType(1);

		int result = newsMapper.insertNews(newsVO);
		System.out.println(newsVO.getBulletinNo());

		assertEquals(1, result);
	}

	// 수정
	//@Test
	public void newsUpdate() {
		// -1) 대상 원래 데이터 조회 : 단건조회
		NewsVO newsVO = new NewsVO();
		newsVO.setBulletinNo(2);
		NewsVO findVO = newsMapper.selectNewsInfo(newsVO);
		System.err.println(findVO);
		// -2) 사용자가 수정하는 내용 입력
		findVO.setContent("Kang");
		// -3) Update (두 개의 인자가 아닌 객체로 전달)
		int result = newsMapper.updateNews(findVO);

		assertEquals(1, result);
	}
	
	// 삭제
	//@Test
	public void newsDelete() {
		int result = newsMapper.deleteNews(2);
		assertEquals(1, result);
	}

}
