package com.puppyroad.app.navi.service;

import java.util.List;
import java.util.Map;

import com.puppyroad.app.puppy.service.PuppyVO;

public interface NaviService {
	public List<PuppyVO> getPuppyList(String bulletinNo);
	public int getBoardNo(String writer);
	public List<NaviVO> callBackNavi(NaviVO naviVO);
	public Map<String, Object> setMatching(NaviVO naviVO);
	public String endMatching(int matchCode);
}
