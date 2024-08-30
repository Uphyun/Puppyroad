package com.puppyroad.app.match.mapper;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.puppyroad.app.match.service.MatchVO;


public interface MatchMapper {
	// 전체 자율게시판 조회
	public List<MatchVO> selectMatchList();
	// 단건 조회
	public MatchVO selectMatchInfo(MatchVO matchVO);
	// 등록
	public int insertMatch(MatchVO matchVO);
	// 수정
	public int updateMatch(@Param("bNo") int matchId, @Param("match") MatchVO matchVO);
	// 삭제
	public int deleteMatch(int bNo);
}
