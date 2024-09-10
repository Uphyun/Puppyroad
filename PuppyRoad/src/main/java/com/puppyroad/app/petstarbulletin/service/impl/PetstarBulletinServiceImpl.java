package com.puppyroad.app.petstarbulletin.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puppyroad.app.petstarbulletin.mapper.PetstarBulletinMapper;
import com.puppyroad.app.petstarbulletin.service.PetstarBulletinService;
import com.puppyroad.app.petstarbulletin.service.PetstarBulletinVO;

@Service
public class PetstarBulletinServiceImpl implements PetstarBulletinService {
	private PetstarBulletinMapper bulletinMapper;
	
	@Autowired
	PetstarBulletinServiceImpl(PetstarBulletinMapper bulletinMapper){
		this.bulletinMapper = bulletinMapper;
	}
	
	// 전체 조회
	@Override
	public List<PetstarBulletinVO> getBulletinList(PetstarBulletinVO bulletinVO) {
		return bulletinMapper.selectBulletinList(bulletinVO);
	}
	
	// 단건조회
	@Override
	public PetstarBulletinVO getBulletinInfo(PetstarBulletinVO bulletinVO) {
		return bulletinMapper.selectBulletinInfo(bulletinVO);
	}
	
	// 등록
	@Override
	public int addBulletin(PetstarBulletinVO bulletinVO) {
		int result = bulletinMapper.insertBulletin(bulletinVO);
		if(result == 1) {
			return bulletinVO.getBulletinNo();
		} else {
			return -1;
		}
	}
	
	// 수정
	@Override
	public Map<String, Object> modifyBulletin(PetstarBulletinVO bulletinVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = bulletinMapper.updateBulletin(bulletinVO);
		if(result == 1) {
			isSuccessed = true;
		}
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String updateDate = sdf.format(today);
		
		map.put("date", updateDate);
		map.put("result", isSuccessed);
		map.put("target", bulletinVO);
		
		return map;
	}
	
	// 삭제
	@Override
	public int removeBulletin(int bno) {
		return bulletinMapper.deleteBulletin(bno);
	}
	
}
