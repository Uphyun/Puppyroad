package com.puppyroad.app.petstarcomment.service;

import java.util.List;
import java.util.Map;


public interface PetstarCommentService {
	// 전체조회
	public List<PetstarCommentVO> getCommentList(PetstarCommentVO commentVO);
	
	// 단건조회
	public PetstarCommentVO getCommentInfo(PetstarCommentVO commentVO);
	
	// 등록
	public int addComment(PetstarCommentVO commentVO);
	
	// 수정
	public Map<String, Object> modifyComment(PetstarCommentVO commentVO);
	
	// 삭제
	public int removeComment(int cno);
}
