package com.puppyroad.app.websocket.service;

import java.util.List;

public interface ChatMessageService {
	//메세지 목록
	public List<ChatMessageDTO> getMessageList(ChatMessageDTO chatMessageDTO);
	
	//가장 최근 메세지 불러오기
	public ChatMessageDTO getRecentMessage(ChatMessageDTO chatMessageDTO);
	
	//메세지 단건 저장
	public int saveMessage(ChatMessageDTO chatMessageDTO);

}
