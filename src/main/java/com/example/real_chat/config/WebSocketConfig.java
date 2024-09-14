package com.example.real_chat.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/sub"); //클라이언트에서 보낸 메세지를 받을 prefix
        config.setApplicationDestinationPrefixes("/pub"); //해당 주소를 구독하고 있는 클라이언트들에게 메세지 전달
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/ws-real-chat")
//                .setAllowedOrigins("http://localhost:8080")
//                .withSockJS();

        registry.addEndpoint("/ws-real-chat")
                .setAllowedOriginPatterns("*");
        // 주소 : ws://localhost:8080/ws-real-chat
    }
}
