package com.puppyroad.app.match.service;

import java.util.List;
import java.util.Map;

public interface MatchService {
	// 전체 자율매칭정보 조회
	public List<MatchVO> getMatchList();
	// 단건 조회
	public MatchVO getMatchInfo(MatchVO matchVO);
	// 단건 등록
	public int addMatch(MatchVO matchVO);
	// 단건 수정
	public Map<String, Object> modifyMatch(MatchVO matchVO);
	// 단건 삭제
	public Map<String, Object> removeMatch(int matchId);
	
	// 개 정보 불러오기
	public List<MatchVO> getDogList(MatchVO matchVO);
	// 단건 개 정보
	public MatchVO getDogInfo(MatchVO matchVO);
}
