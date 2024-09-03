package com.puppyroad.app.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSocketMessageBroker 
@RequiredArgsConstructor
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer { 
// WebSocketMessageBrokerConfigurer를 상속받아 STOMP로 메시지 처리 방법을 구성한다
    private final ChannelInterceptor[] stompHandler;
    //세션 관리

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) { 
    //configureMessageBroker에서는 메시지를 중간에서 라우팅할 때 사용하는 메시지 브로커를 구성한다.
 
        registry.enableSimpleBroker("/sub"); // 
        //해당 주소를 구독하는 클라이언트에 값을 보낸다(구독 요청의 prefix 넣고, 
        //클라이언트에서 1번 채널을 구독하고자 할 때 "/sub/"1형식 같은 규칙을 따라야 함 
        registry.setApplicationDestinationPrefixes("/pub"); // (4)
        //메세지 발행요청의 prefix 넣음
        //"/pub"로 시작하는 메세지만 해당 Broker 에서 처리
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { 
    //클라이언트에서 "Websocket"에 접근 할 수 있는 엔드포인트 지정
    	registry.addEndpoint("/stomp/chat") // ex ) ws://localhost:80/stomp/chat
                .setAllowedOriginPatterns("*").withSockJS(); 
                 // client 가 "sockJs" 로 개발되어 있을 때만 필요, client 가 java 면 필요없음
    }

    @Override
    public void configureClientInboundChannel (ChannelRegistration registration){
    //사용자가 웹 소켓 연결을 연결 될 때와 끊길 때 추가 기능(인증, 세션 관리)을 위한 인터셉터
    	registration.interceptors(stompHandler);
    }
}