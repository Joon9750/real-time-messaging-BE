package com.example.real_chat.global.exception;

public class CannotJoinChatRoomException extends RuntimeException {
    public CannotJoinChatRoomException(String message) {
        super(message);
    }

    public CannotJoinChatRoomException() {
        super("이미 해당 채팅방에 존재하는 유저입니다.");
    }
}
