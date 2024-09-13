package com.puppyroad.app.news.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puppyroad.app.news.mapper.NewsMapper;
import com.puppyroad.app.news.service.NewsService;
import com.puppyroad.app.news.service.NewsVO;

@Service
public class NewsServiceImpl implements NewsService {
	private NewsMapper newsMapper;
	
	@Autowired
	NewsServiceImpl(NewsMapper newsMapper){
		this.newsMapper = newsMapper;
	}
	
	// 전체조회
	@Override
	public List<NewsVO> getNewsList(NewsVO newsVO) {
		return newsMapper.selectNewsList(newsVO);
	}
	
	// 단건조회
	@Override
	public NewsVO getNewsInfo(NewsVO newsVO) {
		return newsMapper.selectNewsInfo(newsVO);
	}
	
	// 등록
	@Override
	public int addNews(NewsVO newsVO) {
		int result = newsMapper.insertNews(newsVO);
		return result == 1 ? newsVO.getBulletinNo() : -1;
	}
	
	// 수정
	@Override
	public Map<String, Object> modifyNews(NewsVO newsVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = newsMapper.updateNews(newsVO);
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed);
		map.put("target", newsVO);
		
		return map;
	}

	// 삭제
	@Override
	public int removeNews(int bno) {
		return newsMapper.deleteNews(bno);
	}

	

}
