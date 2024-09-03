package com.puppyroad.app.websocket.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.puppyroad.app.websocket.service.ChatMessageDTO;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class WebsocketController {
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	// 채팅 메시지 수신 및 저장
	@MessageMapping("chat")
	@Operation(summary = "메시지 전송", description = "메시지를 전송합니다.")
	public ResponseEntity<String> receiveMessage(@RequestBody ChatMessageDTO messageDto) {
		// 메시지 저장
		
		// 메시지를 해당 채팅방 구독자들에게 전송
        return ResponseEntity.ok("메시지 전송 완료");
    }
}
