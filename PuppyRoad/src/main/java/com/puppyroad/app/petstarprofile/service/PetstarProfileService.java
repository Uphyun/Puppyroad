package com.puppyroad.app.petstarprofile.service;

import java.util.List;
import java.util.Map;


public interface PetstarProfileService {
	// 전체조회
	public List<PetStarProfileVO> getProfileList(PetStarProfileVO profileVO);

	// 단건 조회
	public PetStarProfileVO getProfileInfo(PetStarProfileVO profileVO);

	// 등록
	public String addProfile(PetStarProfileVO profileVO);

	// 수정
	public Map<String, Object> modifyProfile(PetStarProfileVO profileVO);

	// 삭제
	public int removeProfile(int pno);
}
