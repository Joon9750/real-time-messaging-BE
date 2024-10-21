package com.example.real_chat.service.command;

import com.example.real_chat.entity.user.User;

public interface UserCommandService {

    Long addUser(String userName, Long clientId);
    void delete(Long userId);
}
