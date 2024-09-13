package com.puppyroad.app.websocket.mapper;

import java.util.List;

import com.puppyroad.app.websocket.service.ChatMessageDTO;
import com.puppyroad.app.websocket.service.ChatMessageService;

public interface MessageMapper {
	//메세지 목록
	public List<ChatMessageDTO> selectMessageList(ChatMessageDTO chatMessageDTO);
	//가장 최근 메세지 불러오기
	public ChatMessageDTO selectRecentMessage(ChatMessageDTO chatMessageDTO);
	//메세지 저장
	public int insertMessage(ChatMessageDTO chatMessageDTO);
}
