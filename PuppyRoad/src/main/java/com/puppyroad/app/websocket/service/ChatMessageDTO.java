package com.puppyroad.app.websocket.service;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatMessageDTO {
	
    private String chatRoomCode;// 방 번호
    private Date   outgoingDate;
    private String attachedFile;
    private String seriesCode;
    private String writer;//  채팅을 보낸 사람
    private String content;// 메세지
}
