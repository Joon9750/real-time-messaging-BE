package com.example.real_chat.webSocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(signalingSocketHandler(), "/ws-real-chat")
                .setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler signalingSocketHandler() {

        return new WebSocketHandler();
    }
}