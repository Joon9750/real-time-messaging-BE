package com.example.real_chat.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String entityName) {
        super(entityName + "을(를) 찾을 수 없습니다.");
    }
}
