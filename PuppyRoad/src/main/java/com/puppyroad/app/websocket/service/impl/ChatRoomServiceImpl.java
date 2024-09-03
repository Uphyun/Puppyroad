package com.puppyroad.app.websocket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puppyroad.app.websocket.mapper.ChatMapper;
import com.puppyroad.app.websocket.service.ChatRoomDTO;
import com.puppyroad.app.websocket.service.ChatRoomService;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {
	private ChatMapper chatMapper;
	
	@Autowired
	void ChatServiceImpl(ChatMapper chatMapper){
		this.chatMapper = chatMapper;
	}
	
	@Override
	public List<ChatRoomDTO> getRoomList() {
		return chatMapper.selectRoomList();
	}

	@Override
	public ChatRoomDTO getRoomInfo(ChatRoomDTO chatRoomDTO) {
		return chatMapper.selectRoomInfo(chatRoomDTO);
	}

	@Override
	public int addRoom(ChatRoomDTO chatRoomDTO) {
		return chatMapper.insertRoom(chatRoomDTO);
	}

}
