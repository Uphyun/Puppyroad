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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.puppyroad.app.petstarprofile.service.PetStarProfileVO;
import com.puppyroad.app.petstarprofile.service.PetstarProfileService;
import com.puppyroad.app.util.SecurityUtil;

@Controller
public class PetStarProfileController {
	private PetstarProfileService profileService;
	
	@Value("${file.upload.path}")
	private String uploadPath;
	
	@Autowired
	PetStarProfileController(PetstarProfileService profileService){
		this.profileService = profileService;
	}
	
	// 전체 조회
	@GetMapping("user/profileList")
	public String profileList(PetStarProfileVO profileVO, Model model) {
		List<PetStarProfileVO> list = profileService.getProfileList(profileVO);
		model.addAttribute("profiles",list);
		return "petstar/profileList";
	}
	
	// 단건 조회
	@GetMapping("user/mypage")
	public String profileInfo(PetStarProfileVO profileVO, Model model ) {
		PetStarProfileVO findVO = profileService.getProfileInfo(profileVO);
		model.addAttribute("profiles", findVO);
		return "petstar/mypage";
	}
	
	// 등록 - 페이지
	@GetMapping("user/profileInsert")
	public String profileInsertForm() {
		return "petstar/profileInsert";
	}
	
	// 등록 - 처리
	@PostMapping("user/profileInsert")
	public String profileInsertProcess(@RequestPart MultipartFile file, PetStarProfileVO profileVO) {
		String fileName = file.getOriginalFilename();
		String saveName = uploadPath + fileName;
		String mcode = SecurityUtil.memberCode();
		
		Path savePath = Paths.get(saveName);
		
		try {
			file.transferTo(savePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		profileVO.setProfilePicture(fileName);
		profileVO.setMemberCode(mcode);
		String result = profileService.addProfile(profileVO);
		
		return result;
	}
	
	// 수정 - 페이지
	@GetMapping("user/profileUpdate")
	public String profileUpdateForm(PetStarProfileVO profileVO, Model model) {
		PetStarProfileVO findVO = profileService.getProfileInfo(profileVO);
		model.addAttribute("profile", findVO);
		return "petstar/mypage";
	}
	

	// 수정 - 처리
	@PostMapping("user/profileUpdate")
	public Map<String, Object> profileUpdate(PetStarProfileVO profileVO, @RequestPart MultipartFile file){
		String fileName = file.getOriginalFilename();
		String saveName = uploadPath + fileName;
		Path savePath = Paths.get(saveName);
		
		try {
			// 파일 업로드 처리
			file.transferTo(savePath);
		} catch (IOException e) {
			e.printStackTrace(); // 업로드 실패 시 예외 처리
		}
		
		profileVO.setProfilePicture(fileName);
		
		return profileService.modifyProfile(profileVO);
	}
	
	// 삭제
	@GetMapping("user/profileDelete")
	public String boardDelete(@RequestParam Integer no) {
		profileService.removeProfile(no);
		return "redirect:/user/petstar";
	}
} //end
