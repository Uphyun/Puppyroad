package com.puppyroad.app.petstarcomment.service;

import java.util.List;
import java.util.Map;


public interface PetstarCommentService {
	// 전체조회
	public List<PetstarCommentVO> getCommentList(int bno);
	
	// 등록
	public int addComment(PetstarCommentVO commentVO);
	
	// 삭제
	public int removeComment(int cno);
}
