package com.puppyroad.app.websocket.service;

import java.util.Date;

import lombok.Data;

@Data
public class ChatMessageDTO {

    private String roomId;
    private String writer;
    private String message;
    private Date   outgoingDate;
    private String attachedFile;
    private String chattingCode;
}