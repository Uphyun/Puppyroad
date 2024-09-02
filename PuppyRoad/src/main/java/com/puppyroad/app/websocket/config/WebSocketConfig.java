package com.puppyroad.app.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSocketMessageBroker
//롬복으로 스프링에서 DI(의존성 주입)의 방법 중에 생성자 주입을 임의의 코드없이 자동으로 설정해주는 어노테이션
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
	//해당 파라미터의 접두사가 붙은 목적지에 메세지를 보냄
    config.enableSimpleBroker("/sub");
    
    //"/pub"는 메세지를 송신할 때 사용
    config.setApplicationDestinationPrefixes("/pub");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
	//ws 이라는 엔드포인트 추가 등록 (stomp 통신으로 인식)
    registry.addEndpoint("/ws")
            .setAllowedOrigins("*");
  }
  
}
