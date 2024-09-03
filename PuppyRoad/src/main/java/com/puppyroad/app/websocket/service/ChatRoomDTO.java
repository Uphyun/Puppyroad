package com.puppyroad.app.websocket.service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.web.socket.WebSocketSession;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomDTO {
	
    private String roomId;// 방 번호
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();
    //Spring 에서 Websocket Connection 맺어진 세션
    
    public static ChatRoomDTO create(String name){
        ChatRoomDTO room = new ChatRoomDTO();

        room.roomId = UUID.randomUUID().toString();
        room.name = name;
        return room;
    }
}
