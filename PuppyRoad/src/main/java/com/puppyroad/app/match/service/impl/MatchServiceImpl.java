package com.puppyroad.app.match.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.puppyroad.app.match.mapper.MatchMapper;
import com.puppyroad.app.match.service.MatchService;
import com.puppyroad.app.match.service.MatchVO;

@Service
public class MatchServiceImpl implements MatchService {
	private MatchMapper matchMapper;
	
	@Autowired
	MatchServiceImpl(MatchMapper matchMapper){
		this.matchMapper = matchMapper;
	}

	@Override
	public List<MatchVO> getMatchList() {
		// TODO 전체 자율매칭 조회
		return matchMapper.selectMatchList();
	}

	@Override
	public MatchVO getMatchInfo(MatchVO matchVO) {
		// TODO 단건 조회
		return matchMapper.selectMatchInfo(matchVO);
	}

	@Override
	@Transactional
	public int addMatch(MatchVO matchVO) {
		// TODO 단건 등록
		int result = matchMapper.insertMatch(matchVO);
		return result == 1 ? matchVO.getBulletinNo() : -1;  
				
	}

	@Override
	public Map<String, Object> modifyMatch(MatchVO matchVO) {
		// TODO 단건 수정
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = matchMapper.updateMatch(matchVO);
		
		if (result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed);
		map.put("target", matchVO);
		
		return map;
	}

	@Override
	public Map<String, Object> removeMatch(int matchId) {
		// TODO 단건 삭제
		Map<String, Object> map = new HashMap<>();
		
		int result = matchMapper.deleteMatch(matchId);
		
		if (result == 1) {
			map.put("matchId", matchId);
		}
		
		return map;
	}

	@Override
	public List<MatchVO> getDogList(MatchVO matchVO) {
		// 개 정보 리스트
		return matchMapper.selectDogMatchList(matchVO);
	}

	@Override
	public MatchVO getDogInfo(MatchVO matchVO) {
		// 개 단건 정보
		return matchMapper.selectDogMatchInfo(matchVO);
	}


}
