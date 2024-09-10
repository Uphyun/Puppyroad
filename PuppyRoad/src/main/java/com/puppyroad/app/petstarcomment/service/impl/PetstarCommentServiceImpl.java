package com.puppyroad.app.petstarcomment.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.puppyroad.app.petstarcomment.mapper.PetstarCommentMapper;
import com.puppyroad.app.petstarcomment.service.PetstarCommentService;
import com.puppyroad.app.petstarcomment.service.PetstarCommentVO;

public class PetstarCommentServiceImpl implements PetstarCommentService {
	private PetstarCommentMapper commentMapper;
	
	@Autowired
	PetstarCommentServiceImpl(PetstarCommentMapper commentMapper){
		this.commentMapper = commentMapper;
	}
	
	// 전체 조회
	@Override
	public List<PetstarCommentVO> getCommentList(PetstarCommentVO commentVO) {
		return commentMapper.selectCommentList(commentVO);
	}
	
	// 단건 조회
	@Override
	public PetstarCommentVO getCommentInfo(PetstarCommentVO commentVO) {
		return commentMapper.selectCommentInfo(commentVO);
	}

	// 등록
	@Override
	public int addComment(PetstarCommentVO commentVO) {
		int result = commentMapper.insertComment(commentVO);
		if(result == 1) {
			return commentVO.getBulletinNo();
		} else {
			return -1;
		}
	}

	// 수정
	@Override
	public Map<String, Object> modifyComment(PetstarCommentVO commentVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = commentMapper.updateComment(commentVO);
		if(result == 1) {
			isSuccessed = true;
		}
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String updateDate = sdf.format(today);
		
		map.put("date", updateDate);
		map.put("result", isSuccessed);
		map.put("target", commentVO);
		
		return map;
	}

	// 삭제
	@Override
	public int removeComment(int cno) {
		return commentMapper.deleteComment(cno);
	}

}
