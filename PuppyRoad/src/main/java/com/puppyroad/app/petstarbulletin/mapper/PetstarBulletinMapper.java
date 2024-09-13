package com.puppyroad.app.petstarbulletin.mapper;

import java.util.List;

import com.puppyroad.app.petstarbulletin.service.PetstarBulletinVO;

public interface PetstarBulletinMapper {
	// 전체 조회 All
	public List<PetstarBulletinVO> selectAllBulletinList(PetstarBulletinVO bulletinVO);

	// 전체 조회 My
	public List<PetstarBulletinVO> selectMyBulletinList(PetstarBulletinVO bulletinVO);

	// 단건 조회
	public PetstarBulletinVO selectBulletinInfo(PetstarBulletinVO bulletinVO);

	// 단건 등록
	public int insertBulletin(PetstarBulletinVO bulletinVO);

	// 수정
	public int updateBulletin(PetstarBulletinVO bulletinVO);

	// 삭제
	public int deleteBulletin(int bNo);
}
