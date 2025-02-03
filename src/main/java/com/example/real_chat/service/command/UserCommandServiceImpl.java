package com.example.real_chat.service.command;

import com.example.real_chat.entity.rootClient.RootClient;
import com.example.real_chat.entity.user.User;
import com.example.real_chat.repository.RootClientRepository;
import com.example.real_chat.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final RootClientRepository rootClientRepository;

    @Override
    public Long addUser(String userName, Long clientId) {
        RootClient rootClient = getRootClient(clientId);
        User user = User.create(userName, rootClient);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long userId) {
        User user = getUser(userId);
        userRepository.delete(user);
    }

    private RootClient getRootClient(Long clientId) throws RuntimeException {
        return rootClientRepository.findById(clientId).orElseThrow(
                () -> new NoSuchElementException("Root client not found with id : " + clientId)
        );
    }

    private User getUser(Long userId) throws RuntimeException {
        return userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("User not found with id : " + userId)
        );
    }
}
