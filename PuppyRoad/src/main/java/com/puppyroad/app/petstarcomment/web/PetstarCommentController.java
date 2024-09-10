package com.puppyroad.app.petstarcomment.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.puppyroad.app.petstarcomment.service.PetstarCommentService;
import com.puppyroad.app.petstarcomment.service.PetstarCommentVO;

@Controller
public class PetstarCommentController {
//	private PetstarCommentService commentService;
//	
//	@Autowired
//	PetstarCommentController(PetstarCommentService commentService){
//		this.commentService = commentService;
//	}
//	
//	// 전체 조회
//	@GetMapping("user/commentList")
//	public String commentList(PetstarCommentVO commentVO, Model model) {
//		List<PetstarCommentVO> list = commentService.getCommentList(commentVO);
//		model.addAttribute("comments", list);
//		return "petstar/commentList";
//	}
//	
//	// 단건 조회
//	@GetMapping("user/commentInfo")
//	public String newsInfo(Integer cono, PetstarCommentVO commentVO, Model model) {
//		PetstarCommentVO findVO = commentService.getCommentInfo(commentVO);
//		model.addAttribute("comment", findVO);
//		return "petstar/commentInfo";
//	}
//	
//	// 등록 - 처리
}
