package com.puppyroad.app.websocket.mapper;

import java.util.List;

import com.puppyroad.app.websocket.service.ChatRoomDTO;

public interface ChatMapper {
	// 전체 방 리스트 조회
	public List<ChatRoomDTO> selectRoomList();
	// 방 추가
	public int insertRoom(ChatRoomDTO chatRoomDTO);
	// 단건 조회
	public ChatRoomDTO selectRoomInfo(ChatRoomDTO chatRoomDTO);

}
