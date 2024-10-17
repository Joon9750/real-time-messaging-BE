package com.example.real_chat.service;

import com.example.real_chat.entity.User;
import com.example.real_chat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long addUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        if (user.isDeleted()) throw new RuntimeException();
        else user.delete();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll().orElseThrow();
    }

    public List<User> getUndeletedUsers() {
        return userRepository.findUnDeletedUsers().orElseThrow();
    }
}
