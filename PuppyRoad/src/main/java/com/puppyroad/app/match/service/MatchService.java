package com.puppyroad.app.match.service;

import java.util.List;
import java.util.Map;

import com.puppyroad.app.puppy.service.PuppyVO;

public interface MatchService {
	// 전체 자율매칭정보 조회
	public List<MatchVO> getMatchList();
	// 단건 조회
	public MatchVO getMatchInfo(MatchVO matchVO);
	// 단건 등록
	public int addMatch(MatchVO matchVO);
	// 단건 수정
	public int modifyMatch(MatchVO matchVO);
	// 단건 삭제
	public Map<String, Object> removeMatch(int matchId);
	
	// 개 정보 불러오기
	public List<PuppyVO> getDogList(PuppyVO puppyVO);
	// 단건 개 정보
	public PuppyVO getDogInfo(PuppyVO puppyVO);
	
	//개인매칭내역조회
	public List<MatchVO> myMatchingList(String writer);
	
	//매칭견 등록
	public int addMatchingPuppy(MatchingPuppyVO matchingPuppyVO);
	
	//채팅견 등록
	public int addChatPuppy(List<MatchingPuppyVO> matchingPuppyVO);
	
	//매칭견 조회
	public List<PuppyVO> getMatchingDogList(Integer bulletinNo);
	
	//대리조회
	public List<MatchVO> getIdList(MatchVO matchVO);
	

}
