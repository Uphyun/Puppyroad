package com.puppyroad.app.dogwalker.web;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.puppyroad.app.dogwalker.service.DogWalkVO;
import com.puppyroad.app.dogwalker.service.DogWalkerSerivce;
import com.puppyroad.app.util.SecurityUtil;

@Controller
public class DogWalkerController {
	
	private DogWalkerSerivce dogWalkerSerivce;
	private String uploadPath;
	
	@Autowired
	public DogWalkerController(DogWalkerSerivce dogWalkerSerivce) {
		this.dogWalkerSerivce = dogWalkerSerivce;
	}
	
	//도그워커 프로필 - 페이지
	@GetMapping("user/insertWalker")
	public String insertWalkerPage(Model model) {
		model.addAttribute("dogWalkVO", new DogWalkVO());
		return "walker/insertWalker";
	}
	
	//도그워커 프로필 - 페이지
	@PostMapping("user/insertWalker")
	public String insertWalkerProcess(DogWalkVO dogWalkVO, 
			                          RedirectAttributes redirectAttributes, 
			                          @RequestPart(required = false) MultipartFile file) {
		
	 	String mcode = SecurityUtil.memberCode();
	    dogWalkVO.setMemberCode(mcode);
		    
		  if (file != null && !file.isEmpty()) {
		        String fileName = file.getOriginalFilename();
		        String saveName = uploadPath + fileName;
		        Path savePath = Paths.get(saveName);

		        try {
		            // 파일을 저장소에 저장
		            file.transferTo(savePath);
		            // 새로 업로드된 파일명을 VO에 설정
		            dogWalkVO.setPicture(fileName);
		        } catch (IOException e) {
		            e.printStackTrace(); // 파일 업로드 실패 처리
		        }
		    } 
		 
		int result = dogWalkerSerivce.walkerinsert(dogWalkVO);
		if(result == 1) {
		    return "redirect:/user/insertDone";  // 성공 시 리다이렉트할 경로
		} else {			
			redirectAttributes.addFlashAttribute("message", "프로필 등록 실패");
		    return "redirect:/user/insertWalker?error=true";  // 실패 시 리다이렉트할 경로
		}
		
	}
	//프로필등록 완료페이지
	@GetMapping("user/insertDone")
	public String insertDonePage() {
		
		return "walker/insertDone";
	}
	
	//프로필 수정
	@PostMapping("user/updateWalker")
	public String updateWalker(@RequestPart(required = false) MultipartFile file, DogWalkVO dogWalkVO) {
		
		String mcode = SecurityUtil.memberCode();
	    dogWalkVO.setMemberCode(mcode);
	    
		 if (file != null && !file.isEmpty()) {
		        String fileName = file.getOriginalFilename();
		        String saveName = uploadPath + fileName;
		        Path savePath = Paths.get(saveName);

		        try {
		            // 파일을 저장소에 저장
		            file.transferTo(savePath);
		            // 새로 업로드된 파일명을 VO에 설정
		            dogWalkVO.setPicture(fileName);
		        } catch (IOException e) {
		            e.printStackTrace(); // 파일 업로드 실패 처리
		        }
		    } 
		 
		 dogWalkerSerivce.walkerupdate(dogWalkVO);
		 
		return "redirect:/user/walkerProfile";
	}
	
	//도그워커단건조회
	@GetMapping("/user/walkerProfile")
	public String getWalkerProfile(Model model) {
	    
	    // 현재 로그인된 사용자의 memberCode 가져오기
	    String memberCode = SecurityUtil.memberCode();
	    
	    // DogWalkVO 객체를 데이터베이스에서 가져옴 (단건 조회)
	    DogWalkVO dogWalkVO = dogWalkerSerivce.walkerInfo(memberCode);
	    
	    String workWeek = dogWalkVO.getWorkWeek(); 
	    String workTime = dogWalkVO.getWorkTime(); 
	    
	    model.addAttribute("workWeek", workWeek != null ? workWeek.split(",") : new String[0]);
	    model.addAttribute("workTime", workTime != null ? workTime.split(",") : new String[0]);
	    
	    // DogWalkVO 객체와 추가된 데이터를 모델에 담아서 뷰로 전달
	    model.addAttribute("dogWalkVO", dogWalkVO);

	    // 뷰로 이동
	    return "walker/infoWalker";  // 단건 조회 페이지로 이동
	}
}//end of controll
