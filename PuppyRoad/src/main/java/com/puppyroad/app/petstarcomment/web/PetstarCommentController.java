package com.puppyroad.app.petstarcomment.web;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppyroad.app.petstarbulletin.service.PetstarBulletinService;
import com.puppyroad.app.petstarbulletin.service.PetstarBulletinVO;
import com.puppyroad.app.petstarcomment.service.PetstarCommentService;
import com.puppyroad.app.petstarcomment.service.PetstarCommentVO;
import com.puppyroad.app.petstarprofile.service.PetStarProfileVO;
import com.puppyroad.app.petstarprofile.service.PetstarProfileService;
import com.puppyroad.app.util.SecurityUtil;

@Controller
public class PetstarCommentController {
	private PetstarCommentService commentService;
	private PetstarBulletinService bulletinService;
	private PetstarProfileService profileService;
	
	@Autowired
	PetstarCommentController(PetstarCommentService commentService,
							PetstarBulletinService bulletinService,
							PetstarProfileService profileService) {
		this.commentService = commentService;
		this.bulletinService = bulletinService;
		this.profileService = profileService;
	}
	
	// 나의 댓글 조회
	@GetMapping("user/commentList")
	public String commentList(PetstarCommentVO commentVO,
							  PetstarBulletinVO bulletinVO,
							  Model model) {
		
		String mcode = SecurityUtil.memberCode();
		commentVO.setMemberCode(mcode);
		
		// 게시글 댓글 조회
		List<PetstarCommentVO> list = commentService.getMyCommentList(commentVO);
		model.addAttribute("com",list);
		
		PetstarBulletinVO findVO = bulletinService.getBulletinInfo(bulletinVO);
		model.addAttribute("bulletin",findVO);
		
		return "petstar/commentList" ;
	}
	
	// 등록 - 처리 (AJAX)
	@PostMapping("user/commentInsert")
	@ResponseBody
	public int commentInsertProcess(PetstarCommentVO commentVO, PetStarProfileVO profileVO) {
		String mcode = SecurityUtil.memberCode();
		commentVO.setMemberCode(mcode);
		
		String nick = SecurityUtil.nickname();
		commentVO.setWriter(nick);
		
		PetStarProfileVO findVO = profileService.getProfileInfo(profileVO);
		String picture = findVO.getProfilePicture();
		
		commentVO.setProfilePicture(picture);
		
		return commentService.addComment(commentVO);
	}
	
	// 삭제
	@GetMapping("user/commentDelete")
	public String commentDelete(@RequestParam Integer no) {
		commentService.removeComment(no);
		return "redirect:/user/commentList";
	}

}
