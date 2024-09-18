package com.puppyroad.app.walkjournal.mapper;

import java.util.List;

import com.puppyroad.app.walkjournal.service.WalkJournalVO;

public interface WalkJournalMapper {
	
	public List<WalkJournalVO> selectAllWalkJournals();
	
	public WalkJournalVO selectOneWalkJournals(WalkJournalVO walkJournalV);
}
