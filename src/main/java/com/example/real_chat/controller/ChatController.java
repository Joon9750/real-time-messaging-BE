package com.example.real_chat.controller;

import com.example.real_chat.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping(value = "/chat/enter")
    public void enter(Message chatMessage) {
        // Message Entity -> ChatMessageRequestDTO로 변환해서 사용해야 함
        System.out.println("연결성공");
        chatMessage.setData(chatMessage.getSender() + "님이 채팅방에 참여하셨습니다.");
        simpMessagingTemplate.convertAndSend("/sub/chat/room/" + chatMessage.getChatRoom(), chatMessage);
    }

    @MessageMapping(value = "/chat/message")
    public void message(Message chatMessage) {
        simpMessagingTemplate.convertAndSend("/sub/chat/room/"+chatMessage.getChatRoom(),chatMessage);
    }
}
