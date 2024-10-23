package com.example.real_chat.service.command;

import com.example.real_chat.entity.rootClient.RootClient;
import com.example.real_chat.entity.user.User;
import com.example.real_chat.repository.UserRepository;
import com.example.real_chat.service.query.RootClientQueryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    private final RootClientQueryService rootClientQueryService;

    @Override
    public Long addUser(String userName, Long clientId) {
        RootClient rootClient = rootClientQueryService.getRootClient(clientId);
        User user = User.create(userName, rootClient);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("User not found with id : " + userId)
        );
        userRepository.delete(user);
    }
}
