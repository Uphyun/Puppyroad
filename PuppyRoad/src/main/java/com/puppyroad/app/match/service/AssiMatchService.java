package com.puppyroad.app.match.service;

import java.util.List;
import java.util.Map;

public interface AssiMatchService {
	// 전체 자율매칭정보 조회
	public List<AssiMatchVO> getAssiMatchList();
	// 단건 조회
	public AssiMatchVO getAssiMatchInfo(AssiMatchVO assiVO);
	// 단건 등록
	public int addAssiMatch(AssiMatchVO assiVO);
	// 단건 수정
	public Map<String, Object> modifyAssiMatch(AssiMatchVO assiVO);
	// 단건 삭제
	public Map<String, Object> removeAssiMatch(int assiVO);
	
}
