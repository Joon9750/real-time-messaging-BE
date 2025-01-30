package com.example.real_chat.service;

import com.example.real_chat.repository.RoomRepository;
import com.example.real_chat.repository.RootClientRepository;
import com.example.real_chat.repository.UserChatRoomRepository;
import com.example.real_chat.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class ServiceTest {

    @Mock protected RoomRepository roomRepository;
    @Mock protected UserRepository userRepository;
    @Mock protected RootClientRepository rootClientRepository;
    @Mock protected UserChatRoomRepository userChatRoomRepository;
}
