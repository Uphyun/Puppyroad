package com.puppyroad.app.walkjournal.mapper;

import java.util.List;

import com.puppyroad.app.walkjournal.service.WalkJournalVO;

public interface WalkJournalMapper {
	//의뢰인 산책일지조회
	public List<WalkJournalVO> selectAllWalkJournals(String userId);
	//의뢰인 산책일지 단건조회
	public WalkJournalVO selectOneWalkJournals(WalkJournalVO walkJournalV);
	//도그워커 산책일지조회
	public List<WalkJournalVO> dogWalkJournals(WalkJournalVO walkJournalV);
	//도그워커 산책일지단건조회
	public WalkJournalVO dogSelectOneWalkJournals(WalkJournalVO walkJournalV);
	//도그워커 산책일지 등록
	public int insertWalkJournal(WalkJournalVO walkJournalV);

}
