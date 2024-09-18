package com.puppyroad.app.match.mapper;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.puppyroad.app.match.service.MatchVO;


public interface MatchMapper {
	// 전체 자율게시판 조회
	public List<MatchVO> selectMatchList();
	
	// 개 전체 조회
	public List<MatchVO> selectDogMatchList(MatchVO matchVO);
	// 개 단건 조회
	public MatchVO selectDogMatchInfo(MatchVO matchVO);
	
	// 단건 조회
	public MatchVO selectMatchInfo(MatchVO matchVO);
	// 등록
	public int insertMatch(MatchVO matchVO);
	// 수정
	public int updateMatch(@Param("match") MatchVO matchVO);
	// 삭제
	public int deleteMatch(int bNo);
	
	//매칭내역조회
	public List<MatchVO> selectMatchingBoard(String writer);
}
