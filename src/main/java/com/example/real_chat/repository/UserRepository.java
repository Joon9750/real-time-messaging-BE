package com.example.real_chat.repository;

import com.example.real_chat.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Long save(User user);

    Optional<User> findById(Long id);
    List<User> findAll();

    void delete(User user);
}