package com.puppyroad.app.petstarprofile.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puppyroad.app.petstarprofile.mapper.PetstarProfileMapper;
import com.puppyroad.app.petstarprofile.service.PetStarProfileVO;
import com.puppyroad.app.petstarprofile.service.PetstarProfileService;

@Service
public class PetStarProfileServiceImpl implements PetstarProfileService {
	private PetstarProfileMapper profileMapper;
	
	@Autowired
	PetStarProfileServiceImpl(PetstarProfileMapper profileMapper){
		this.profileMapper = profileMapper;
	}
	
	// 전체조회
	@Override
	public List<PetStarProfileVO> getProfileList(PetStarProfileVO profileVO) {
		return profileMapper.selectProfileList(profileVO);
	}
	
	// 단건조회
	@Override
	public PetStarProfileVO getProfileInfo(PetStarProfileVO profileVO) {
		return profileMapper.selectPetStarProfileInfo(profileVO);
	}

	// 등록
	@Override
	public String addProfile(PetStarProfileVO profileVO) {
		int result = profileMapper.insertProfile(profileVO);
		if(result == 1) {
			return profileVO.getMemberCode();
		} else {
			return "fail";
		}
	}

	// 수정
	@Override
	public Map<String, Object> modifyProfile(PetStarProfileVO profileVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = profileMapper.updateProfile(profileVO);
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed);
		map.put("target", profileVO);
		
		return map;
	}

	//삭제
	@Override
	public int removeProfile(int pno) {
		return profileMapper.deleteProfile(pno);
	}
}
