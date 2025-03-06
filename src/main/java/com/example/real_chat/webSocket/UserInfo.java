package com.example.real_chat.webSocket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

@Getter
@AllArgsConstructor
public class UserInfo {
    private String name;
    private Long userId;
    private String chatRoomId;
    private WebSocketSession session;
}