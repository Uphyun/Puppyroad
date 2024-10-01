package com.puppyroad.app.dogwalker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puppyroad.app.dogwalker.mapper.DogWalkerMapper;
import com.puppyroad.app.dogwalker.service.DogWalkVO;
import com.puppyroad.app.dogwalker.service.DogWalkerSerivce;

@Service
public class DogWalkerServiceImpl implements DogWalkerSerivce {
	
	private DogWalkerMapper dogWalkerMapper;
	
	@Autowired
	public DogWalkerServiceImpl(DogWalkerMapper dogWalkerMapper) {
		
		this.dogWalkerMapper = dogWalkerMapper;
	}
	
	@Override
	public int walkerinsert(DogWalkVO dogWalkVO) {
		
		return dogWalkerMapper.insertwalker(dogWalkVO);
	}

	@Override
	public int walkerupdate(DogWalkVO dogWalkVO) {
		
		return dogWalkerMapper.updatewalker(dogWalkVO);
	}

	@Override
	public DogWalkVO walkerInfo(String memberCode) {
		// TODO Auto-generated method stub
		return dogWalkerMapper.selectWalkerInfo(memberCode);
	}

	@Override
	public int countWalkInfo() {
		// TODO Auto-generated method stub
		return dogWalkerMapper.countWalkInfo();
	}

	@Override
	public boolean walkerInfoCount(String memberCode) {
		return dogWalkerMapper.walkerInfoCount(memberCode) >= 1;
	}

}
