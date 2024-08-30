package com.puppyroad.app.news.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppyroad.app.news.service.NewsService;
import com.puppyroad.app.news.service.NewsVO;

@Controller
public class NewsController {
	private NewsService newsService;
	
	@Autowired 
	NewsController(NewsService newsService){
		this.newsService = newsService;
	}
	
	// 전체조회
	@GetMapping("newsList")
	public String newsList(Model model) {
		List<NewsVO> list = newsService.getNewsList();
		model.addAttribute("newss", list);
		return "news/newsList";
	}
	
	// 단건조회
	@GetMapping("newsInfo")
	public String newsInfo(Integer newsno, NewsVO newsVO, Model model) {
		NewsVO findVO = newsService.getNewsInfo(newsVO);
		model.addAttribute("news",findVO);
		return "news/newsInfo";
	}
	
	// 등록 - 페이지
//	@GetMapping("newsInsert")
//	public String newsInsertForm() {
//		return "news/newsInsert";
//	}
	
	// 등록 - 처리
//	@PostMapping("newsInsert")
//	public String newsInsertProcess(NewsVO newsVO) {
//		int bno = newsService.addNews(newsVO);
//		return "redirect:newsInsert?bulletinNo=" + bno;
//	}
//	
	// 수정 - 페이지
	@GetMapping("newsInsert")
	public String boardUpdateForm(NewsVO newsVO, Model model) {
		NewsVO findVO = newsService.getNewsInfo(newsVO);
		model.addAttribute("news",findVO);
		return "news/newsUpdate";
	}
	
	// 수정 - 처리
	@PostMapping("newsInsert")
	@ResponseBody // => AJAX
	public Map<String, Object> newsUpdateAJAXJSON(@RequestBody NewsVO newsVO){
		return newsService.modifyNews(newsVO);
	}
	
	//삭제
	@GetMapping("newsDelete")
	public String boardDelete(@RequestParam Integer no) { //@RequestParam 생략가능 생략하는게 좋음
		newsService.removeNews(no);
		return "redirect:newsList";
	}
}
