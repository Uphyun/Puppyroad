package com.puppyroad.app.websocket.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatMessageDTO {
	
    private String roomId;// 방 번호
    private String writer;//채팅을 보낸 사람
    private String message;// 메세지
}
