package com.puppyroad.app.admin.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puppyroad.app.admin.main.service.AdminMainService;
import com.puppyroad.app.admin.manage.mapper.ManageMemberMapper;

@Service
public class AdminMainServiceImpl implements AdminMainService{
	
	@Autowired
	ManageMemberMapper manageMemberMapper;

	@Override
	public int getNewWalkerCnt() {
		
		return manageMemberMapper.getWalkerCnt();
	}
}
