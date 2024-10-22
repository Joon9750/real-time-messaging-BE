package com.example.real_chat.exception;

public class ChatRoomNotFoundException extends RuntimeException {
    public ChatRoomNotFoundException(String message) {
        super(message);
    }

    public ChatRoomNotFoundException() {
        super("채팅방을 찾을 수 없습니다.");
    }
}
