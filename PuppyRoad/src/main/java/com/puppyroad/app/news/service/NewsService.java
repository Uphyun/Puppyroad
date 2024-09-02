package com.puppyroad.app.news.service;

import java.util.List;
import java.util.Map;

public interface NewsService {
	
	// 전체조회
	public List<NewsVO> getNewsList();
	
	// 단건조회
	public NewsVO getNewsInfo(NewsVO newsVO);
	
	// 등록
	public int addNews(NewsVO newsVO);
	
	// 수정
	public Map<String, Object> modifyNews(NewsVO newsVO);
	
	// 삭제
	public int removeNews(int bno);
}
