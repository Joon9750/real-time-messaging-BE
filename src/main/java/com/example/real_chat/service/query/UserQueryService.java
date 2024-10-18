package com.example.real_chat.service.query;

import com.example.real_chat.entity.user.User;

import java.util.List;

public interface UserQueryService {

    User getUserById(Long userId);
    List<User> getAllUsers();
    List<User> getUndeletedUsers();
}
