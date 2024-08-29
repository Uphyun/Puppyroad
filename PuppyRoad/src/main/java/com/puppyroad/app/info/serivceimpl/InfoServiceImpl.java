package com.puppyroad.app.info.serivceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puppyroad.app.info.mapper.InfoMapper;
import com.puppyroad.app.info.service.InfoService;
import com.puppyroad.app.info.service.InfoVO;

@Service
public class InfoServiceImpl implements InfoService {
	private InfoMapper infoMapper;
	
	@Autowired
	InfoServiceImpl(InfoMapper infomapper){
		this.infoMapper = infomapper;
	}

	@Override
	public List<InfoVO> infoList() {
		return infoMapper.getInfoList();
	}
	

}
