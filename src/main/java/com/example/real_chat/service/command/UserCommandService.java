package com.example.real_chat.service.command;

public interface UserCommandService {

    Long addUser(String userName, Long clientId);
    void delete(Long userId);
}
