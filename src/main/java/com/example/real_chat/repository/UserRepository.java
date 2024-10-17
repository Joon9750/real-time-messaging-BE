package com.example.real_chat.repository;

import com.example.real_chat.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Long save(User user);

    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    Optional<List<User>> findAll();
    Optional<List<User>> findUnDeletedUsers();
}