package com.puppyroad.app.match.mapper;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.puppyroad.app.match.service.MatchVO;
import com.puppyroad.app.match.service.MatchingPuppyVO;
import com.puppyroad.app.puppy.service.PuppyVO;


public interface MatchMapper {
	// 전체 자율게시판 조회
	public List<MatchVO> selectMatchList();
	
	// 개 전체 조회
	public List<PuppyVO> selectDogMatchList(PuppyVO puppyVO);
	// 개 단건 조회
	public PuppyVO selectDogMatchInfo(PuppyVO puppyVO);
	
	// 매칭견 등록
	public int insertMatchingPuppy(MatchingPuppyVO matchingPuppyVO);
	// 매칭견 조회
	public List<PuppyVO> selectMatchingDogList(Integer bulletinNo);
	
	// 단건 조회
	public MatchVO selectMatchInfo(MatchVO matchVO);
	// 등록
	public int insertMatch(MatchVO matchVO);
	// 수정
	public int updateMatch(@Param("match") MatchVO matchVO);
	
	// 채팅매칭수정
	public int updateChatMatch(@Param("match") MatchVO matchVO);
	
	// 삭제
	public int deleteMatch(int bNo);
	
	//매칭내역조회
	public List<MatchVO> selectMatchingBoard(String writer);
	
	//단건 대리기준조회
	public List<MatchVO> selectIdList(MatchVO matchVO);
	
	// 매칭견 등록
	public int insertChatPuppy(MatchingPuppyVO matchingPuppyVO);
	// 매칭견 삭제
	public int deleteMatchPuppy(MatchingPuppyVO matchingPuppyVO);

}
