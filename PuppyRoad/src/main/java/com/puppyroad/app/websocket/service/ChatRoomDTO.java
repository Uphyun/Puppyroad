package com.puppyroad.app.websocket.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

import lombok.Data;

@Data
public class ChatRoomDTO {

    private String roomId;
    private String roomName;
    private String recipient;
    private Date createdDate;
    private String chattingType;
    private String sender;
    private Integer matchNo;
    private Set<WebSocketSession> sessions = new HashSet<>();
    private List<ChatMessageDTO> myRoomList;
    //WebSocketSession은 Spring에서 Websocket Connection이 맺어진 세션

}
