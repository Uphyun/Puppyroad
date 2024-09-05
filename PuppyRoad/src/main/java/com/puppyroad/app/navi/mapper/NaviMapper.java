package com.puppyroad.app.navi.mapper;



import com.puppyroad.app.navi.service.NaviVO;

public interface NaviMapper {
	//강아지 경로 설정 및 조회
	public void setCallDogNavi(NaviVO naviVO);
}
