package com.puppyroad.app.assi.service;

import java.util.List;

public interface AssiService {
	
	// 전체 대리매칭 조회
	public List<AssiVO> getAssiList(AssiVO assiVO);
	
	// 단건 조회
	public AssiVO getAssiInfo(AssiVO assiVO);
	// 단건 워커프로필 조회
	public WalkerAddInfoVO getWalkerInfo(String writer);

}
