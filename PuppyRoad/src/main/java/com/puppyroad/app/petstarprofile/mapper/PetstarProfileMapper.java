package com.puppyroad.app.petstarprofile.mapper;

import java.util.List;

import com.puppyroad.app.petstarprofile.service.PetStarProfileVO;

public interface PetstarProfileMapper {
	
	// 단건 조회
	public PetStarProfileVO selectPetStarProfileInfo(PetStarProfileVO profileVO);
	
	// 단건 등록
	public int insertProfile(PetStarProfileVO profileVO);
	
	// 수정
	public int updateProfile(PetStarProfileVO profileVO);
	
}
