package com.puppyroad.app.petstarcomment.mapper;

import java.util.List;

import com.puppyroad.app.petstarcomment.service.PetstarCommentVO;


public interface PetstarCommentMapper {
	// 전체 조회
	public List<PetstarCommentVO> selectCommentList(int bno);
	
	// 단건 조회
	public PetstarCommentVO selectCommentInfo(PetstarCommentVO commentVO);
	
	// 단건 등록
	public int insertComment(PetstarCommentVO commentVO);
	
	// 삭제
	public int deleteComment(int cno);
}
