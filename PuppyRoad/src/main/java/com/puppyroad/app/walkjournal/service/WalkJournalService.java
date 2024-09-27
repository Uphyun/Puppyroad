package com.puppyroad.app.walkjournal.service;

import java.util.List;

public interface WalkJournalService {
	
	//의뢰인 산책일지 전체리스트 조회
	public List<WalkJournalVO> WalkJournalList(String userId);
	
	//의뢰인 단건조회
	public WalkJournalVO WalkJournalGetInfo(WalkJournalVO walkJournalV);
	
	//도그워커 전체조회
	public List<WalkJournalVO> dogWalkJournalList(WalkJournalVO walkJournalV);
	
	//도그워커 단건조회
	public WalkJournalVO dogWalkJournalGetInfo(WalkJournalVO walkJournalV);
}
