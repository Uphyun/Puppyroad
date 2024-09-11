package com.puppyroad.app.admin.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public List<MemberVO> getSearchMemberList(MemberVO memberVO, String condition) {
		
		return manageMemberMapper.selectSearchMemberList(memberVO, condition);
	}

	@Override
	public Map<String, Object> updateAccountState(MemberVO memberVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSuccess = false;
		
		int result = manageMemberMapper.updateAccountState(memberVO);
		
		if(result == 1) {
			isSuccess = true;
		}
		
		map.put("isSuccess", isSuccess);
		map.put("vo", memberVO);

		return map;
	}

}
