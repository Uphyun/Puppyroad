package com.puppyroad.app.assi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puppyroad.app.assi.mapper.AssiMapper;
import com.puppyroad.app.assi.service.AssiService;
import com.puppyroad.app.assi.service.AssiVO;
import com.puppyroad.app.assi.service.WalkerAddInfoVO;

@Service
public class AssiServiceImpl implements AssiService{
	private AssiMapper assiMapper;
	
	@Autowired
	AssiServiceImpl(AssiMapper assiMapper){
		this.assiMapper = assiMapper;
	}

	@Override
	public List<AssiVO> getAssiList(AssiVO assiVO) {
		// TODO 전체 대리 조회
		return assiMapper.selectAssiList(assiVO);
	}

	@Override
	public AssiVO getAssiInfo(AssiVO assiVO) {
		// TODO 단건 조회
		return assiMapper.selectAssiInfo(assiVO);
	}
	@Override
	public WalkerAddInfoVO getWalkerInfo(String writer) {
		// TODO 단건 프로필 조회
		return assiMapper.selectWalkerInfo(writer);

	}

	@Override
	public WalkerAddInfoVO getMyWalkerInfo(String mcode) {
		// TODO 내 프로필 조회
		return assiMapper.selectmyWalkerInfo(mcode);
	}

	@Override
	public int addAssiInfo(AssiVO assiVO) {
		// TODO 등록
		int result = assiMapper.insertAssiInfo(assiVO);
		return result;
	}

}
