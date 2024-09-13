package com.puppyroad.app.puppy.service;

import java.util.List;

public interface PuppyService {
	
	public String addPuppy(PuppyVO puppyVO); // 강아지 프로필 등록
	public List<PuppyVO> ListPuppy(String clientUserId); //본인 강아지조회
	public PuppyVO getInfoPuppy(PuppyVO puppyVO); // 단건조회
	public int updatePuppy(PuppyVO puppyVO); // 프로필수정
	public int deletePuppy(String puppyCode);
}
