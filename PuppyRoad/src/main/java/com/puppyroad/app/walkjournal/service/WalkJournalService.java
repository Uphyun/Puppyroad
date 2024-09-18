package com.puppyroad.app.walkjournal.service;

import java.util.List;

public interface WalkJournalService {
	
	//산책일지 전체리스트 조회
	public List<WalkJournalVO> WalkJournalList();
	
	//단건조회
	public WalkJournalVO WalkJournalGetInfo(WalkJournalVO walkJournalV);
	
}
