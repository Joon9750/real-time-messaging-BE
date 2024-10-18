package com.example.real_chat.service.command;

import com.example.real_chat.entity.user.User;

public interface UserCommandService {

    Long addUser(User user);
    void deleteUser(Long userId);
}
