package com.puppyroad.app.report.web;

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

import com.puppyroad.app.report.service.ReportService;
import com.puppyroad.app.report.service.ReportVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ReportController {
	private ReportService reportService;
	
	@Value("${file.upload.path}")
	private String uploadPath;
	
	@Autowired
	ReportController(ReportService reportService){
		this.reportService = reportService;
	}
	
	// 등록 - 페이지
		@GetMapping("reportInsert")
		public String insertForm() {
			return "report/reportInsert";
		}

		// 등록 - 처리
		@PostMapping("reportInsert")
		public String reportInsertProcess(@RequestPart MultipartFile[] files, ReportVO reportVO) {
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
			reportVO.setAttachedFile(String.join(",", imageList));
			int bno = reportService.addReport(reportVO);

			String url = null;
			if (bno > -1) {
				url = "redirect:reportInsert";
			} else {
				url = "redirect:reportList";
			}
			return url;
		}
}
