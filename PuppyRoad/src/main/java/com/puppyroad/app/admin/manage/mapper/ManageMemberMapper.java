package com.puppyroad.app.admin.manage.mapper;

import java.util.List;

import com.puppyroad.app.member.service.MemberVO;

public interface ManageMemberMapper {
	//의뢰인리스트
	public List<MemberVO> selectMemberList();
}
