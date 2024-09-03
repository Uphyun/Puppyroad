package com.puppyroad.app.news.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.puppyroad.app.news.service.NewsService;
import com.puppyroad.app.news.service.NewsVO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class NewsController {
	private NewsService newsService;
	
	@Value("${file.upload.path}")
	private String uploadPath;
	
	@Autowired 
	NewsController(NewsService newsService){
		this.newsService = newsService;
	}
	
	// 공지사항 전체조회
	@GetMapping("newsList")
	public String newsList(NewsVO newsVO, Model model) {
		newsVO.setBulletinType("0");
		List<NewsVO> list = newsService.getNewsList(newsVO);
		model.addAttribute("newss", list);
		return "news/newsList";
	}
	
	// 이벤트 전체조회
		@GetMapping("eventList")
	public String eventList(NewsVO newsVO, Model model) {
		newsVO.setBulletinType("1");
		List<NewsVO> list = newsService.getNewsList(newsVO);
		model.addAttribute("event", list);
		return "news/eventList";
	}
	
	// 단건조회
	@GetMapping("newsInfo")
	public String newsInfo(Integer newsno, NewsVO newsVO, Model model) {
		NewsVO findVO = newsService.getNewsInfo(newsVO);
		model.addAttribute("news",findVO);
		return "news/newsInfo";
	}
	
	// 등록 - 페이지
	@GetMapping("newsInsert")
	public String newsInsertForm() {
		return "news/newsInsert";
	}
	
	// 등록 - 처리
	@PostMapping("newsInsert")
	public String newsInsertProcess(@RequestPart MultipartFile[] files, NewsVO newsVO) {
		List<String> imageList = new ArrayList<>();
		
		for(MultipartFile file : files) {
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
		newsVO.setAttachedFile(String.join(",", imageList));
		int bno = newsService.addNews(newsVO);
		
		String url = null;
		if(bno > -1) {
			url = "redirect:newsInfo?bulletinNo=" + bno;
		} else {
			url = "redirect:newsList";
		}
		return url;
	}

	
	// 수정 - 페이지
	@GetMapping("newsUpdate")
	public String boardUpdateForm(NewsVO newsVO, Model model) {
		NewsVO findVO = newsService.getNewsInfo(newsVO);
		model.addAttribute("news",findVO);
		return "news/newsUpdate";
	}
	
	// 수정 - 처리
	@PostMapping("newsUpdate")
	@ResponseBody
	public Map<String, Object> newsUpdateAJAXJSON(NewsVO newsVO
												,@RequestPart MultipartFile[] files){
		
		List<String> imageList = new ArrayList<>();
		 if (files != null && files.length > 0) {
		        for (MultipartFile file : files) {
		            String fileName = file.getOriginalFilename();
		            String saveName = uploadPath + fileName;
		            Path savePath = Paths.get(saveName);

		            try {
		                file.transferTo(savePath);  
		                imageList.add(fileName);
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		        newsVO.setAttachedFile(String.join(",", imageList));
		    }
		    
		    return newsService.modifyNews(newsVO);
	}
	
	//삭제
	@GetMapping("newsDelete")
	public String boardDelete(@RequestParam Integer no) { 
		newsService.removeNews(no);
		return "redirect:newsList";
	}
}
