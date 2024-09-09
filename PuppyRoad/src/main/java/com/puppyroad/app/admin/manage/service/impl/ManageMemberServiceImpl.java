package com.puppyroad.app.admin.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puppyroad.app.admin.manage.mapper.ManageMemberMapper;
import com.puppyroad.app.admin.manage.service.ManageMemberService;
import com.puppyroad.app.member.service.MemberVO;

@Service
public class ManageMemberServiceImpl implements ManageMemberService{
	
	@Autowired
	ManageMemberMapper manageMemberMapper;

	@Override
	public List<MemberVO> getMemberList() {
		// TODO Auto-generated method stub
		return manageMemberMapper.selectMemberList();
	}

	@Override
	public MemberVO getMemberInfo(String memberCode) {

		return manageMemberMapper.selectMemberInfo(memberCode);
	}

}
