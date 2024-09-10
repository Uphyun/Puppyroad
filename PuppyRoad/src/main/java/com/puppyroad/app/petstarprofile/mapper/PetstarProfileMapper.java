package com.puppyroad.app.petstarprofile.mapper;

import java.util.List;

import com.puppyroad.app.petstarprofile.service.PetStarProfileVO;

public interface PetstarProfileMapper {
	// 전체 조회
	public List<PetStarProfileVO> selectProfileList(PetStarProfileVO profileVO);
	
	// 단건 조회
	public PetStarProfileVO selectPetStarProfileInfo(PetStarProfileVO profileVO);
	
	// 단건 등록
	public int insertProfile(PetStarProfileVO profileVO);
	
	// 수정
	public int updateProfile(PetStarProfileVO profileVO);
	
	// 삭제
	public int deleteProfile(int pNo);
}
