package com.puppyroad.app.petstarprofile.web;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.puppyroad.app.petstarbulletin.service.PetstarBulletinService;
import com.puppyroad.app.petstarbulletin.service.PetstarBulletinVO;
import com.puppyroad.app.petstarprofile.service.PetStarProfileVO;
import com.puppyroad.app.petstarprofile.service.PetstarProfileService;
import com.puppyroad.app.util.SecurityUtil;

@Controller
public class PetStarProfileController {
	private PetstarProfileService profileService;
	private PetstarBulletinService bulletinService;

	@Value("${file.upload.path}")
	private String uploadPath;

	
	@Autowired
	PetStarProfileController(PetstarProfileService profileService,PetstarBulletinService bulletinService) {
		this.profileService = profileService;
		this.bulletinService = bulletinService;
	}

	// 단건 조회
	@GetMapping("user/profile")
	public String profileInfo(PetStarProfileVO profileVO, Model model) {
		
		PetStarProfileVO findVO = profileService.getProfileInfo(profileVO);
		model.addAttribute("profiles", findVO);
		return "petstar/profile";
	}

	// 수정 - 페이지
	@GetMapping("user/mypage")
	public String profileUpdateForm(PetStarProfileVO profileVO,
									Model model, 
									PetstarBulletinVO bulletinVO) {
		
		String mcode = SecurityUtil.memberCode();
		profileVO.setMemberCode(mcode);
		bulletinVO.setMemberCode(mcode);
		
		String nick = SecurityUtil.nickname();
		profileVO.setNickname(nick);

		// 프로필
		PetStarProfileVO findVO = profileService.getProfileInfo(profileVO);

		// 모델에 프로필 정보를 추가하여 뷰로 전달
		// 게시글 리스트
		List<PetstarBulletinVO> list = bulletinService.getMyBulletinList(bulletinVO);
		model.addAttribute("bulletin",list);
		
		// 프로필
		model.addAttribute("profile", findVO);

		return "petstar/mypage";

	}

	// 수정 - 처리
	@PostMapping("user/profileUpdate")
	public String profileUpdate(PetStarProfileVO profileVO,
	                            @RequestPart(required = false) MultipartFile file, 
	                            BindingResult result,
	                            Model model) {
		
		// 폼 데이터가 바인딩되면서 오류가 발생한 경우
		if (result.hasErrors()) {
				return "petstar/mypage";
			}

	    model.addAttribute("profile", profileVO);

	    // 현재 사용자의 memberCode를 가져옴
	    String mcode = SecurityUtil.memberCode();
	    profileVO.setMemberCode(mcode);

	    // 기존 첨부파일 가져오기
	    PetStarProfileVO findVO = profileService.getProfileInfo(profileVO);

	    // 파일이 있는 경우에만 업로드 처리
	    if (file != null && !file.isEmpty()) {
	        String fileName = file.getOriginalFilename();
	        String saveName = uploadPath + fileName;
	        Path savePath = Paths.get(saveName);

	        try {
	            // 파일을 저장소에 저장
	            file.transferTo(savePath);
	            // 새로 업로드된 파일명을 VO에 설정
	            profileVO.setProfilePicture(fileName);
	        } catch (IOException e) {
	            e.printStackTrace(); // 파일 업로드 실패 처리
	        }
	    } else {
	        // 파일이 없으면 기존 이미지를 사용
	        profileVO.setProfilePicture(findVO.getProfilePicture());
	    }

	    // 프로필 수정 처리
	    profileService.modifyProfile(profileVO);
	    
	    // 수정 후 다시 마이페이지로 리다이렉트
	    return "redirect:/user/mypage";
	}

} // end
