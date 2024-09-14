package com.example.real_chat.controller;

import com.example.real_chat.dto.ChatMessageRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello")
    public void message(ChatMessageRequestDTO<String> message) {
        // ChatMessageRequestDTO -> Message Entity로 변환 해야함
        simpMessagingTemplate.convertAndSend("/topic/message" + message.getRoomId(), message);
    }
}
