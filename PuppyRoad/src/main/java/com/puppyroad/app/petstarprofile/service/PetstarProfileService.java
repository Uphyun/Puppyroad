package com.puppyroad.app.petstarprofile.service;

import java.util.List;
import java.util.Map;


public interface PetstarProfileService {

	// 단건 조회
	public PetStarProfileVO getProfileInfo(PetStarProfileVO profileVO);

	// 등록
	public String addProfile(PetStarProfileVO profileVO);

	// 수정
	public Map<String, Object> modifyProfile(PetStarProfileVO profileVO);

}
