package com.puppyroad.app.navi.mapper;

import java.util.List;

import com.puppyroad.app.navi.service.NaviVO;

public interface NaviMapper {
	//강아지 경로 설정 및 조회
	public List<NaviVO> setCallDogNavi(NaviVO naviVO);
}
