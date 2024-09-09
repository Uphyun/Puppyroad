package com.puppyroad.app.admin.manage.mapper;

import java.util.List;

import com.puppyroad.app.member.service.MemberVO;

public interface ManageMemberMapper {
	//승인 할 도그워커 수 확인
	public int getWalkerCnt();
	//의뢰인리스트
	public List<MemberVO> selectMemberList();
	//의뢰인 상세정보
	public MemberVO selectMemberInfo(String memberCode);
}
