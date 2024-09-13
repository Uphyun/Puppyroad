package com.puppyroad.app.dogwalker.mapper;

import com.puppyroad.app.dogwalker.service.DogWalkVO;

public interface DogWalkerMapper {
	
	//도그워커 프로필등록
	public int insertwalker(DogWalkVO dogWalkVO);

}
