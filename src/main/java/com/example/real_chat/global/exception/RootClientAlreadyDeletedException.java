package com.example.real_chat.global.exception;

public class RootClientAlreadyDeletedException extends RuntimeException {
    public RootClientAlreadyDeletedException(String message) {
        super(message);
    }
}