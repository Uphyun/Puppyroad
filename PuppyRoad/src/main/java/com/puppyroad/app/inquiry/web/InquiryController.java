package com.puppyroad.app.inquiry.web;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.puppyroad.app.inquiry.service.InquiryService;
import com.puppyroad.app.inquiry.service.InquiryVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class InquiryController {
	private InquiryService inquiryService;

	@Value("${file.upload.path}")
	private String uploadPath;

	@Autowired
	InquiryController(InquiryService inquiryService) {
		this.inquiryService = inquiryService;
	}

	// 등록 - 페이지
	@GetMapping("inquiryInsert")
	public String insertForm() {
		return "inquiry/inquiryInsert";
	}

	// 등록 - 처리
	@PostMapping("inquiryInsert")
	public String inquiryInsertProcess(@RequestPart MultipartFile[] files, InquiryVO inquiryVO) {
		List<String> imageList = new ArrayList<>();

		for (MultipartFile file : files) {
			System.out.println("컨트롤러 도달");
			log.info(file.getContentType());
			log.info(file.getOriginalFilename());
			log.info(String.valueOf(file.getSize()));

			String fileName = file.getOriginalFilename();
			String saveName = uploadPath + fileName;

			log.debug("saveName : " + saveName);

			Path savePath = Paths.get(saveName); // path 순수 경로

			try {
				file.transferTo(savePath);
				imageList.add(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		inquiryVO.setAttachedFile(String.join(",", imageList));
		int bno = inquiryService.addInquiry(inquiryVO);

		String url = null;
		if (bno > -1) {
			url = "redirect:inquiryInsert";
		} else {
			url = "redirect:inquiryList";
		}
		return url;
	}
}
