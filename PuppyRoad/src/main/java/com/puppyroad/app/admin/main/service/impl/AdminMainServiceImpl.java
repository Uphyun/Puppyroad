package com.puppyroad.app.admin.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puppyroad.app.admin.main.mapper.AdminMainMapper;
import com.puppyroad.app.admin.main.service.AdminMainService;

@Service
public class AdminMainServiceImpl implements AdminMainService{
	
	@Autowired
	AdminMainMapper adminMainMapper;

	@Override
	public int getNewWalkerCnt() {
		
		return adminMainMapper.getWalkerCnt();
	}
}
