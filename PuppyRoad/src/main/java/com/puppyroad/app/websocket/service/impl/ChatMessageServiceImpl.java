package com.puppyroad.app.websocket.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puppyroad.app.websocket.mapper.ChatMapper;
import com.puppyroad.app.websocket.mapper.MessageMapper;
import com.puppyroad.app.websocket.service.ChatMessageDTO;
import com.puppyroad.app.websocket.service.ChatMessageService;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {
	private MessageMapper messageMapper;
	
	@Autowired
	void ChatMessageServiceImpl(MessageMapper messageMapper){
		this.messageMapper = messageMapper;
	}

	@Override
	public List<ChatMessageDTO> getMessageList(ChatMessageDTO chatMessageDTO) {
		return messageMapper.selectMessageList(chatMessageDTO);
	}
	
	@Override
	public int saveMessage(ChatMessageDTO chatMessageDTO) {
		return messageMapper.insertMessage(chatMessageDTO);
	}

	@Override
	public ChatMessageDTO getRecentMessage(ChatMessageDTO chatMessageDTO) {
		return messageMapper.selectRecentMessage(chatMessageDTO);
	}



}
