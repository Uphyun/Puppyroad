package com.puppyroad.app.puppy.mapper;

import java.util.List;

import com.puppyroad.app.puppy.service.PuppyVO;

public interface PuppyMapper {
	
	public int insertPuppy(PuppyVO puppyVO); // 강아지 프로필 등록
	public List<PuppyVO> selectPuppy(String clientUserId); //본인 강아지 조회
	public PuppyVO selectPuppyInfo(PuppyVO puppyVO); //단건조회
	public int updatePuppy(PuppyVO puppyVO); // 강아지 프로필 수정
	public int deletePuppy(String puppyCode);
	
}
