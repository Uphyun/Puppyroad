package com.puppyroad.app.websocket.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.web.socket.WebSocketSession;

import lombok.Data;

@Data
public class ChatRoomDTO {
	
    private String chatRoomCode;// 방 번호
    private String seriesCode;
    private String memberCode;
    private Date   createdDate;
    private String chattingType;
    private Set<WebSocketSession> sessions = new HashSet<>();
    //Spring 에서 Websocket Connection 맺어진 세션
    
    public static ChatRoomDTO create(String seriesCode){
        ChatRoomDTO room = new ChatRoomDTO();

        room.chatRoomCode = UUID.randomUUID().toString();
        room.seriesCode = seriesCode;
        return room;
    }
}
