package com.puppyroad.app.navi.service;

import java.util.List;
import java.util.Map;

import com.puppyroad.app.match.service.MatchVO;
import com.puppyroad.app.puppy.service.PuppyVO;

public interface NaviService {
	public List<PuppyVO> getPuppyList(String bulletinNo);
	public int getBoardNo(String writer);
	public Map<String, Object> callBackNavi(NaviVO naviVO);
	public MatchVO getMarkerInfo(int bulletinNo);
	public Map<String, Object> setMatching(NaviVO naviVO);
	public String endMatching(int matchCode);
}
