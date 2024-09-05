package com.puppyroad.app.match.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puppyroad.app.match.mapper.AssiMatchMapper;
import com.puppyroad.app.match.mapper.MatchMapper;
import com.puppyroad.app.match.service.AssiMatchService;
import com.puppyroad.app.match.service.AssiMatchVO;
import com.puppyroad.app.match.service.MatchService;
import com.puppyroad.app.match.service.MatchVO;

@Service
public class AssiMatchServiceImpl implements AssiMatchService {
	private AssiMatchMapper assiMapper;
	
	@Autowired
	AssiMatchServiceImpl(AssiMatchMapper assiMapper){
		this.assiMapper = assiMapper;
	}

	@Override
	public List<AssiMatchVO> getAssiMatchList() {
		// TODO 전체 자율매칭 조회
		return assiMapper.selectAssiMatchList();
	}

	@Override
	public AssiMatchVO getAssiMatchInfo(AssiMatchVO assiVO) {
		// TODO 단건 조회
		return assiMapper.selectAssiMatchInfo(assiVO);
	}

	@Override
	public int addAssiMatch(AssiMatchVO assiVO) {
		// TODO 단건 등록
		int result = assiMapper.insertAssiMatch(assiVO);
		return result == 1 ? assiVO.getBulletinNo() : -1;  
				
	}

	@Override
	public Map<String, Object> modifyAssiMatch(AssiMatchVO assiVO) {
		// TODO 단건 수정
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = assiMapper.updateAssiMatch(assiVO);
		
		if (result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed);
		map.put("target", assiVO);
		
		return map;
	}

	@Override
	public Map<String, Object> removeAssiMatch(int matchId) {
		// TODO 단건 삭제
		Map<String, Object> map = new HashMap<>();
		
		int result = assiMapper.deleteAssiMatch(matchId);
		
		if (result == 1) {
			map.put("matchId", matchId);
		}
		
		return map;
	}


}
