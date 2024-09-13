package com.puppyroad.app.websocket.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ChatMessageDTO {

    private String roomId;
    private String writer;
    private String message;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date   outgoingDate;
    private String attachedFile;
    private String chattingCode;
}