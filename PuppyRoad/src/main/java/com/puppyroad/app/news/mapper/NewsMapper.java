package com.puppyroad.app.news.mapper;

import java.util.List;

import com.puppyroad.app.news.service.NewsVO;


public interface NewsMapper {
	// 전체조회
	public List<NewsVO> selectNewsList(NewsVO newVO);
	
	// 단건 조회
	public NewsVO selectNewsInfo(NewsVO newVO);
	
	// 단건 등록
	public int insertNews(NewsVO newsVO);
	
	// 수정
	public int updateNews(NewsVO newsVO);
	
	// 삭제
	public int deleteNews(int bNo);
}
