package com.puppyroad.app.navi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puppyroad.app.navi.mapper.NaviMapper;
import com.puppyroad.app.navi.service.NaviService;
import com.puppyroad.app.navi.service.NaviVO;

@Service
public class NaviServiceImpl implements NaviService {
	
	@Autowired
	NaviMapper naviMapper;

	@Override
	public List<NaviVO> callBackNavi(NaviVO naviVO) {
		System.out.println(naviVO.getScheduleCode());
		if(naviVO.getPuppyCode() != null) {
			naviMapper.setCallDogNavi(naviVO);
		}
		System.out.println(naviVO.getCNavi());

		return naviVO.getCNavi();
	}

}
