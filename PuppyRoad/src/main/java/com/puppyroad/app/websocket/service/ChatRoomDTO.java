package com.puppyroad.app.websocket.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.web.socket.WebSocketSession;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomDTO {
	
    private String chatRoomCode;// 방 번호 name
    private String seriesCode; // 일련 번호 roomId
    private String memberCode; // 맴버 번호
    private Date   createdDate; // 만든 날짜
    private String chattingType; // 채팅 타입
    private Set<WebSocketSession> sessions = new HashSet<>();
    //Spring 에서 Websocket Connection 맺어진 세션
    
    public static ChatRoomDTO create(String chatRoomCode){
        ChatRoomDTO room = new ChatRoomDTO();

        room.seriesCode = UUID.randomUUID().toString();
        room.chatRoomCode = chatRoomCode;
        return room;
    }
}
