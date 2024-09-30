package com.puppyroad.app.walkjournal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puppyroad.app.walkjournal.mapper.WalkJournalMapper;
import com.puppyroad.app.walkjournal.service.WalkJournalService;
import com.puppyroad.app.walkjournal.service.WalkJournalVO;

@Service
public class WalkJournalServiceImpl implements WalkJournalService {
	
	private WalkJournalMapper walkJournalMapper;
	
	@Autowired
	public WalkJournalServiceImpl(WalkJournalMapper walkJournalMapper) {
		
		this.walkJournalMapper = walkJournalMapper;
	}
	
	@Override
	public List<WalkJournalVO> WalkJournalList(String userId){
		
		return walkJournalMapper.selectAllWalkJournals(userId);
	}

	@Override
	public WalkJournalVO WalkJournalGetInfo(WalkJournalVO walkJournalVO) {
		
		return walkJournalMapper.selectOneWalkJournals(walkJournalVO);
	}

	@Override
	public List<WalkJournalVO> dogWalkJournalList(WalkJournalVO walkJournalV) {
		
		return walkJournalMapper.dogWalkJournals(walkJournalV);
	}

	@Override
	public WalkJournalVO dogWalkJournalGetInfo(WalkJournalVO walkJournalV) {
		
		return walkJournalMapper.dogSelectOneWalkJournals(walkJournalV);
	}

	@Override
	public int insertWalkJournal(WalkJournalVO walkJournalVO) {
		// TODO Auto-generated method stub
		return walkJournalMapper.insertWalkJournal(walkJournalVO);
	}
}//end
