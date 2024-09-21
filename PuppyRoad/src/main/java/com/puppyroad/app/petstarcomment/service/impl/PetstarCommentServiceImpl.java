package com.puppyroad.app.petstarcomment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puppyroad.app.petstarcomment.mapper.PetstarCommentMapper;
import com.puppyroad.app.petstarcomment.service.PetstarCommentService;
import com.puppyroad.app.petstarcomment.service.PetstarCommentVO;

@Service
public class PetstarCommentServiceImpl implements PetstarCommentService {
	private PetstarCommentMapper commentMapper;
	
	@Autowired
	PetstarCommentServiceImpl(PetstarCommentMapper commentMapper){
		this.commentMapper = commentMapper;
	}
	
	// 전체 조회
	@Override
	public List<PetstarCommentVO> getCommentList(int bno) {
		return commentMapper.selectCommentList(bno);
	}
	
	// 등록
	@Override
	public int addComment(PetstarCommentVO commentVO) {
		int result = commentMapper.insertComment(commentVO);
		
		if(result == 1) {
			return 1;
		} else {
			return -1;
		}
	}


	// 삭제
	@Override
	public int removeComment(int cno) {
		return commentMapper.deleteComment(cno);
	}

}
