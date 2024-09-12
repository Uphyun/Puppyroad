package com.puppyroad.app.puppy.web;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.puppyroad.app.petstarprofile.service.PetStarProfileVO;
import com.puppyroad.app.puppy.service.PuppyService;
import com.puppyroad.app.puppy.service.PuppyVO;
import com.puppyroad.app.util.SecurityUtil;

import jakarta.validation.Valid;

@Controller
public class PuppyController {
	
	private PuppyService puppyservice;
	//private SecurityUtil securityUtil;
	@Value("${file.upload.path}")
	private String uploadPath;
	
	@Autowired
	public PuppyController(PuppyService puppyservice) {
		
		this.puppyservice = puppyservice;
		//this.securityUtil = securityUtil;
	}
	
	//강아지 프로필 등록 - 페이지
	@GetMapping("user/insertPuppy")
	public String insertPuppyPage(Model model) {
		model.addAttribute("puppyVO", new PuppyVO());
		return "puppy/insertPuppy";
	}
	
	//강아지 프로필 등록 - 처리
	@PostMapping("user/insertPuppy")
	public String insertPuppyProcess(@Valid PuppyVO puppyVO, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes, @RequestPart(required = false) MultipartFile file) {
		 // 파일이 있는 경우에만 업로드 처리
	    if (file != null && !file.isEmpty()) {
	        String fileName = file.getOriginalFilename();
	        String saveName = uploadPath + fileName;
	        Path savePath = Paths.get(saveName);

	        try {
	            // 파일을 저장소에 저장
	            file.transferTo(savePath);
	            // 새로 업로드된 파일명을 VO에 설정
	            puppyVO.setPicture(fileName);
	        } catch (IOException e) {
	            e.printStackTrace(); // 파일 업로드 실패 처리
	        }
	    } 

	    // 프로필 처리
	    
		String mcode = SecurityUtil.memberCode();
		puppyVO.setClientUserId(mcode);
		System.out.println(puppyVO.getClientUserId());
		if(bindingResult.hasErrors()) {
			model.addAttribute("puppyVO", puppyVO);
			return "puppy/insertPuppy";
		}else {
			String addPuppy = puppyservice.addPuppy(puppyVO);
			if(addPuppy.equals("fail")) {
				return "redirect:puppy/puppyInsert";
			}else {
				redirectAttributes.addFlashAttribute("successMsg", "댕댕이 정상 등록 완료!");
				return "redirect:/user/listPuppy"; 
			}
		}
	}
	//강아지 리스트
	@GetMapping("user/listPuppy")
	public String listPuppys(String clientUserId, Model model) {
		List<PuppyVO> list = puppyservice.ListPuppy(SecurityUtil.memberCode());
		model.addAttribute("list", list);
		return "puppy/selectPuppy";
	}
	
	//강아지 조회
	@GetMapping("user/getInfoPuppy")
	@ResponseBody
	public PuppyVO getInfoPuppy(PuppyVO puppyVO, Model model) {
		String mcode = SecurityUtil.memberCode();
		puppyVO.setClientUserId(mcode);
		return puppyservice.getInfoPuppy(puppyVO);
	}
	
	//강아지 수정
	@PostMapping("user/updatePuppy")
	public String updatePuppy(PuppyVO puppyVO, Model model, @RequestPart(required = false) MultipartFile file) {
		
		model.addAttribute("profile", puppyVO);
		
		String mcode = SecurityUtil.memberCode();
		puppyVO.setClientUserId(mcode);
		
		  // 기존 첨부파일 가져오기
	    PuppyVO findVO = puppyservice.getInfoPuppy(puppyVO);

	    // 파일이 있는 경우에만 업로드 처리
	    if (file != null && !file.isEmpty()) {
	        String fileName = file.getOriginalFilename();
	        String saveName = uploadPath + fileName;
	        Path savePath = Paths.get(saveName);

	        try {
	            // 파일을 저장소에 저장
	            file.transferTo(savePath);
	            // 새로 업로드된 파일명을 VO에 설정
	            puppyVO.setPicture(fileName);
	        } catch (IOException e) {
	            e.printStackTrace(); // 파일 업로드 실패 처리
	        }
	    } else {
	        // 파일이 없으면 기존 이미지를 사용
	        puppyVO.setPicture(findVO.getPicture());
	    }

		puppyservice.updatePuppy(puppyVO);
		
		return "redirect:/user/listPuppy";
		
		
		
		
	}
	//강아지 삭제
	
	
	
	
	
}//end of controller
