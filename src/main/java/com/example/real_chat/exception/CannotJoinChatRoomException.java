package com.example.real_chat.exception;

public class CannotJoinChatRoomException extends RuntimeException {
    public CannotJoinChatRoomException(String message) {
        super(message);
    }

    public CannotJoinChatRoomException() {
        super("사용자가 채팅방에 추가되지 못했습니다.");
    }
}
