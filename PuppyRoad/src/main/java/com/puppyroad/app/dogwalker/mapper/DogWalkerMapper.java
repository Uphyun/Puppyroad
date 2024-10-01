package com.puppyroad.app.dogwalker.mapper;

import com.puppyroad.app.dogwalker.service.DogWalkVO;

public interface DogWalkerMapper {
	
	//도그워커 프로필등록
	public int insertwalker(DogWalkVO dogWalkVO);
	//도그워커 수정
	public int updatewalker(DogWalkVO dogWalkVO);
	//조회
	public DogWalkVO selectWalkerInfo(String memberCode);
	//도그워커정보갯수조회
	public int countWalkInfo();

	public int walkerInfoCount(String memberCode);
}
