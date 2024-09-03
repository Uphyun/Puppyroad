package com.puppyroad.app.websocket.service;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatMessageDTO {
	
    private Date   outgoingDate; // 보낸 날짜
    private String attachedFile; // 첨부파일
    private String seriesCode;   // 일련번호 roomId
    private String writer;//  채팅을 보낸 사람
    private String content;// 메세지
}
