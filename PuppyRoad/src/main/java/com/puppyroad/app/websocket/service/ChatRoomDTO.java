package com.puppyroad.app.websocket.service;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class ChatRoomDTO {

    private String roomId;
    private String roomName;
    private String recipient;
    private Date createdDate;
    private String chattingType;
    private String sender;
    private Set<WebSocketSession> sessions = new HashSet<>();
    //WebSocketSession은 Spring에서 Websocket Connection이 맺어진 세션

}
