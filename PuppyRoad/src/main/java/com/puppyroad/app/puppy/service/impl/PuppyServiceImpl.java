package com.puppyroad.app.puppy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puppyroad.app.puppy.mapper.PuppyMapper;
import com.puppyroad.app.puppy.service.PuppyService;
import com.puppyroad.app.puppy.service.PuppyVO;

@Service
public class PuppyServiceImpl implements PuppyService {
	
	private PuppyMapper puppymapper;
	
	@Autowired
	public PuppyServiceImpl(PuppyMapper puppymapper) {
		
		this.puppymapper = puppymapper;
	}

	@Override
	public String addPuppy(PuppyVO puppyVO) {
		
		int result = puppymapper.insertPuppy(puppyVO);
		
		return result == 1 ? puppyVO.getPuppyCode() : "fail" ;
	}
	
	
	
	
	
}
