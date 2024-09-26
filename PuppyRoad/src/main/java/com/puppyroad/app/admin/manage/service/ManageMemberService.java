package com.puppyroad.app.admin.manage.service;

import java.util.List;
import java.util.Map;

import com.puppyroad.app.main.service.PageDTO;
import com.puppyroad.app.member.service.MemberVO;

public interface ManageMemberService {
	//회원 리스트
	public List<MemberVO> getMemberList(MemberVO memberVO);
	//의뢰인 상세정보
	public MemberVO getMemberInfo(String memberCode);
	//봉사인리스트
	//도그워커리스트
	//계정 상태 업데이트
	public Map<String, Object> updateAccountState(MemberVO memberVO);
}
