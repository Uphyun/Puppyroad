package com.puppyroad.app.dogwalker.service;

public interface DogWalkerSerivce {
	
	//도그워커프로필등록
	public int walkerinsert(DogWalkVO dogWalkVO);
	//드그워커 업데이트
	public int walkerupdate(DogWalkVO dogWalkVO);
	//단건조회
	public DogWalkVO walkerInfo(String memberCode);
}
