package com.puppyroad.app.websocket.web;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.puppyroad.app.websocket.service.ChatMessageDTO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor //생성자 생성
public class StompWebsocketController {
    private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달

    @MessageMapping("chat/enter") //@MessageMapping 을 통해 WebSocket으로 들어오는 메세지 발행을 처리한다.
    public void enter(ChatMessageDTO message){
        message.setMessage(message.getWriter() + "님이 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        //구독 경로를 각 유저마다 독립적으로 사용하면 해당 유저에게만 메시지를 전송할 수 있다. 나중에 convertAndSendToUser(보안용)
    }

    @MessageMapping("chat/message")
    public void message(ChatMessageDTO message){
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}