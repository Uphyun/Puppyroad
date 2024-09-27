package com.puppyroad.app.websocket.service;

import java.util.List;

import com.puppyroad.app.match.service.MatchVO;
import com.puppyroad.app.petstarprofile.service.PetStarProfileVO;

public interface ChatRoomService {
	// 전체 조회
	public List<ChatRoomDTO> getRoomList();
	// my 전체 조회
	public List<ChatRoomDTO> getMyRoomList(ChatRoomDTO chatRoomDTO);
	// 단건 조회
	public ChatRoomDTO getRoomInfo(ChatRoomDTO chatRoomDTO);
	// 등록
	public int addRoom(ChatRoomDTO chatRoomDTO, MatchVO matchVO);
	
	// 단건 내 프로필 조회
	public PetStarProfileVO getMyPorfile(PetStarProfileVO petStarProfileVO);
}
