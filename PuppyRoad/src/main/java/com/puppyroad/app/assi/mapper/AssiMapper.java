package com.puppyroad.app.assi.mapper;

import java.util.List;

import com.puppyroad.app.assi.service.AssiVO;
import com.puppyroad.app.assi.service.WalkerAddInfoVO;

public interface AssiMapper {
	// 전체 대리 조회
	public List<AssiVO> selectAssiList(AssiVO assiVO);
	
	// 단건 대리 조회
	public AssiVO selectAssiInfo(AssiVO assiVO);
	// 단건 워커 프로필 조회
	public WalkerAddInfoVO selectWalkerInfo(String writer);
	
	// 내 워커 추가정보 조회
	public WalkerAddInfoVO selectmyWalkerInfo(String mcode);
	
	// 대리 등록
	public int insertAssiInfo(AssiVO assiVO);
	// 대리 수정
	public int updateAssiInfo(AssiVO assiVO);

}
