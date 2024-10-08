package com.puppyroad.app.navi.mapper;



import java.util.List;

import com.puppyroad.app.match.service.MatchVO;
import com.puppyroad.app.navi.service.NaviVO;
import com.puppyroad.app.puppy.service.PuppyVO;

public interface NaviMapper {
	//매칭 게시판 번호 조회
	public int selectBulletinNo(String writer);
	//강아지 목록 조회
	public List<PuppyVO> selectPuppyList(String bulletinNo);
	//강아지 경로 설정 및 조회
	public void setCallDogNavi(NaviVO naviVO);
	//실시간 매칭 등록
	public int insertMatchingInfo(NaviVO naviVO);
	//실시간 매칭 조회
	public List<NaviVO> getMatchingList(int matchCode);
	//실시간 마커 정보 조회
	public MatchVO selectMarkerInfo(int bulletinNo);
	//실시간 매칭 종료
	public int deleteMatchingInfo(int matchCode);
}
