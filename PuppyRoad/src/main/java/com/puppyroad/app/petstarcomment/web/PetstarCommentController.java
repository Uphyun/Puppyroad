package com.puppyroad.app.petstarcomment.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppyroad.app.petstarcomment.service.PetstarCommentService;
import com.puppyroad.app.petstarcomment.service.PetstarCommentVO;
import com.puppyroad.app.util.SecurityUtil;

@Controller
public class PetstarCommentController {
	private PetstarCommentService commentService;
	
	@Autowired
	PetstarCommentController(PetstarCommentService commentService) {
		this.commentService = commentService;
	}
	
	
	// 등록 - 처리 (AJAX)
	@PostMapping("user/commentInsert")
	@ResponseBody
	public int commentInsertProcess(PetstarCommentVO commentVO) {
		String nick = SecurityUtil.nickname();
		commentVO.setWriter(nick);
		
		return commentService.addComment(commentVO);
	}
	
	// 나의 댓글 조회
	@GetMapping("user/commentList")
	public String commentList(PetstarCommentVO commentVO) {
		
		return "petstar/commentList" ;
	}
}
