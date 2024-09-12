package com.puppyroad.app.petstarbulletin.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.puppyroad.app.petstarbulletin.service.PetstarBulletinService;
import com.puppyroad.app.petstarbulletin.service.PetstarBulletinVO;
import com.puppyroad.app.util.SecurityUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PetstarBulletinController {
	private PetstarBulletinService bulletinService;

	@Value("${file.upload.path}")
	private String uploadPath;

	@Autowired
	PetstarBulletinController(PetstarBulletinService bulletinService) {
		this.bulletinService = bulletinService;
	}

	// 전체 조회
	@GetMapping("user/petstar")
	public String bulletinList(PetstarBulletinVO bulletinVO, Model model) {
		List<PetstarBulletinVO> list = bulletinService.getBulletinList(bulletinVO);
		model.addAttribute("bulletin", list);
		return "petstar/petstar";
	}

	// 단건조회
	@GetMapping("user/bulletinInfo")
	public String bulletinInfo(Integer bulletinno, PetstarBulletinVO bulletinVO, Model model) {
		PetstarBulletinVO findVO = bulletinService.getBulletinInfo(bulletinVO);
		model.addAttribute("bulletin", findVO);
		return "bulletin/bulletinInfo";
	}

	// 등록 - 페이지
	@GetMapping("user/bulletinInsert")
	public String bulletinInsertForm() {
		return "petstar/bulletinInsert";
	}

	// 등록 - 처리
	@PostMapping("user/bulletinInsert")
	@ResponseBody
	public int bulletinInsertProcess(@RequestPart MultipartFile[] files, PetstarBulletinVO bulletinVO) {
		String mcode = SecurityUtil.memberCode();
		bulletinVO.setMemberCode(mcode);
		
		List<String> imageList = new ArrayList<>();

		for (MultipartFile file : files) {
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
		bulletinVO.setAttachedFile(String.join(",", imageList));
		int result = bulletinService.addBulletin(bulletinVO);

		return result;
	}

	// 수정 - 페이지
	@GetMapping("user/bulletinUpdate")
	public String bulletinUpdateForm(PetstarBulletinVO bulletinVO, Model model) {
		PetstarBulletinVO findVO = bulletinService.getBulletinInfo(bulletinVO);
		model.addAttribute("bulletin", findVO);
		return "bulletin/bulletinUpdate";
	}

	// 수정 - 처리
	@PostMapping("user/bulletinUpdate")
	@ResponseBody
	public Map<String, Object> bulletinUpdate(PetstarBulletinVO bulletinVO,
			@RequestPart(required = false) MultipartFile[] files, // 파일이 없을
			@RequestParam(value = "deletedFiles", required = false) String[] deletedFiles // 삭제할 파일 목록
	) {
		// 1. 기존 첨부파일 가져오기
		PetstarBulletinVO findVO = bulletinService.getBulletinInfo(bulletinVO);
		String existingFiles = findVO.getAttachedFile(); // 기존 파일 목록 문자열
		List<String> fileList = (existingFiles != null && !existingFiles.isEmpty())
				? new ArrayList<>(Arrays.asList(existingFiles.split(",")))
				: new ArrayList<>();

		// 2. 삭제할 파일 처리
		if (deletedFiles != null && deletedFiles.length > 0) {
			for (String fileName : deletedFiles) {
				// 파일 시스템에서 파일 삭제
				Path filePath = Paths.get(uploadPath + fileName);
				try {
					Files.deleteIfExists(filePath); // 파일이 존재할 경우 삭제
					fileList.remove(fileName); // 기존 파일 목록에서 삭제된 파일 제거
				} catch (IOException e) {
					e.printStackTrace(); // 파일 삭제 실패 시 예외 처리
				}
			}
		}

		// 3. 새로운 파일 업로드 처리
		if (files != null && files.length > 0) {
			for (MultipartFile file : files) {
				String fileName = file.getOriginalFilename();
				String saveName = uploadPath + fileName;
				Path savePath = Paths.get(saveName);

				try {
					// 파일 업로드 처리
					file.transferTo(savePath);
					fileList.add(fileName); // 새 파일을 파일 목록에 추가
				} catch (IOException e) {
					e.printStackTrace(); // 업로드 실패 시 예외 처리
				}
			}
		}

		// 4. 최종 파일 목록을 다시 PetstarBulletinVO에 설정
		if (!fileList.isEmpty()) {
			bulletinVO.setAttachedFile(String.join(",", fileList));
		} else {
			bulletinVO.setAttachedFile(null); // 모든 파일이 삭제된 경우 null로 처리
		}

		// 5. 게시글 수정 처리
		return bulletinService.modifyBulletin(bulletinVO);
	}

	// 삭제
	@GetMapping("user/bulletinDelete")
	public String boardDelete(@RequestParam Integer no) {
		bulletinService.removeBulletin(no);
		return "redirect:/user/bulletinList";
	}

}
