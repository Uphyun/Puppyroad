package com.puppyroad.app.admin.manage.service;

import java.util.List;

import com.puppyroad.app.member.service.MemberVO;

public interface ManageMemberService {
	//의뢰인리스트
	public List<MemberVO> getMemberList();
	//의뢰인 상세정보
	public MemberVO getMemberInfo(String memberCode);
	//봉사인리스트
	//도그워커리스트
}
