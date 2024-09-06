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
@EnableWebSocketMessageBroker //stomp 사용을 위한 어노테이션
@RequiredArgsConstructor //생성자 생성 어노테이션
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer { 
    //세션 관리

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { 
    //클라이언트에서 "Websocket"에 접근 할 수 있는 엔드포인트 지정
    	registry.addEndpoint("/stomp/chat") // ex ) ws://localhost:80/stomp/chat
                .setAllowedOriginPatterns("http://localhost:80")
                .withSockJS(); 
    }
	
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) { 
    //configureMessageBroker에서는 메시지를 중간에서 라우팅할 때 사용하는 메시지 브로커를 구성한다.
 
        registry.enableSimpleBroker("/sub"); // 스프링에서 제공하는 내장 브로커를 사용한다는 설정
        //해당 주소를 구독하는 클라이언트에 값을 보낸다(구독 요청의 prefix 넣고, 
        //클라이언트에서 1번 채널을 구독하고자 할 때 "/sub/"1형식 같은 규칙을 따라야 함 
        registry.setApplicationDestinationPrefixes("/pub"); // (4)
        //메세지 발행요청의 prefix 넣음
        //"/pub"로 시작하는 메세지만 해당 Broker 에서 처리해서 핸들러로 보냄
    }

}