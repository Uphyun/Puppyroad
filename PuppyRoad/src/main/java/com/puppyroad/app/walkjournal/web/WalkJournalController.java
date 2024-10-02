package com.puppyroad.app.walkjournal.web;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.puppyroad.app.util.SecurityUtil;
import com.puppyroad.app.walkjournal.service.WalkJournalService;
import com.puppyroad.app.walkjournal.service.WalkJournalVO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Controller
public class WalkJournalController {
   
   private WalkJournalService walkJournalService;
   private String uploadPath;
   
   @Autowired
   public WalkJournalController(WalkJournalService walkJournalService) {
      
      this.walkJournalService = walkJournalService;
   }
   
   //산책일지 리스트
   @GetMapping("user/walkJournalList")
   public String walkJournalList(String userId, Model model, @RequestPart(required = false) MultipartFile file) {
      String useId = SecurityUtil.memberCode();
      List<WalkJournalVO> jList = walkJournalService.WalkJournalList(useId);
      
       if (file != null && !file.isEmpty()) {
              String fileName = file.getOriginalFilename();
              String saveName = uploadPath + fileName;
              Path savePath = Paths.get(saveName);

              try {
                  // 파일을 저장소에 저장
                  file.transferTo(savePath);
              } catch (IOException e) {
                  e.printStackTrace(); // 파일 업로드 실패 처리
              }
          } 
       
      model.addAttribute("jList", jList);
      
      return "walkJournal/walkJournalList";
      
   }
   
   //산책일지 단건조회
   @GetMapping("user/walkJournalGetInfo")
   public String walkJournalGetInfo(WalkJournalVO walkJournalVO, Model model) {
      
      WalkJournalVO result = walkJournalService.WalkJournalGetInfo(walkJournalVO);
      model.addAttribute("result", result);
      
      return "walkJournal/walkJournalInfo";
   }
   
   //도그워커 산책일지 조회
   @GetMapping("user/dogwalkJournalList")
   public String dogwalkJournalList(WalkJournalVO walkJournalVO, Model model) {
      String mcode = SecurityUtil.memberCode();
      walkJournalVO.setWalkerCode(mcode);
      List<WalkJournalVO> list = walkJournalService.dogWalkJournalList(walkJournalVO);
      
      model.addAttribute("list", list);
      
      return "walkJournal/dogWalkJournalList"; 
   }
   
   //산책일지 단건조회
   @GetMapping("user/dogwalkJournalGetInfo")
   public String dogWalkJournalGetInfo(WalkJournalVO walkJournalVO, Model model) {
      String mcode = SecurityUtil.memberCode();
      walkJournalVO.setWalkerCode(mcode);
      WalkJournalVO result = walkJournalService.dogWalkJournalGetInfo(walkJournalVO);
      model.addAttribute("result", result);
      
      return "walkJournal/dogWalkJournalInfo";
   }
   
   //산책일지 등록처리
   @PostMapping("user/insertJournalProcess")
   @ResponseBody
   public int insertJournal(@RequestBody WalkJournalVO walkJournalVO, Model model) {
      String mcode = SecurityUtil.memberCode();
      walkJournalVO.setWalkerCode(mcode);
      return walkJournalService.insertWalkJournal(walkJournalVO);
      
   }
	/*
	 * //산책일지 등록페이지
	 * 
	 * @GetMapping("user/insertJournal") public String insertJournal(Model model,
	 * WalkJournalVO walkJournalVO) { String mcode = SecurityUtil.memberCode();
	 * walkJournalVO.setWriter(mcode); WalkJournalVO list =
	 * walkJournalService.walkJournalPetInfo(walkJournalVO);
	 * model.addAttribute("list", list); System.err.println(list); return
	 * "walkJournal/insertWalkJournal";
	 * 
	 * }
	 */
}//end
