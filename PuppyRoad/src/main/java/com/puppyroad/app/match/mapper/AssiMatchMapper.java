package com.puppyroad.app.match.mapper;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.puppyroad.app.match.service.AssiMatchVO;
import com.puppyroad.app.match.service.MatchVO;


public interface AssiMatchMapper {
	// 전체 대리게시판 조회
	public List<AssiMatchVO> selectAssiMatchList();
	// 단건 조회
	public AssiMatchVO selectAssiMatchInfo(AssiMatchVO assiVO);
	// 등록
	public int insertAssiMatch(AssiMatchVO assiVO);
	// 수정
	public int updateAssiMatch(@Param("assi") AssiMatchVO assiVO);
	// 삭제
	public int deleteAssiMatch(int bNo);
}
