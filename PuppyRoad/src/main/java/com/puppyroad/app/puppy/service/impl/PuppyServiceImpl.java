package com.puppyroad.app.puppy.service.impl;

import java.util.List;

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

	@Override
	public List<PuppyVO> ListPuppy(String clientUserId) {
		
		return puppymapper.selectPuppy(clientUserId);
	}

	@Override
	public PuppyVO getInfoPuppy(PuppyVO puppyVO) {
		
		return puppymapper.selectPuppyInfo(puppyVO);
	}

	@Override
	public int updatePuppy(PuppyVO puppyVO) {
		
		return puppymapper.updatePuppy(puppyVO);
	}
	
	
	
	
	
}
