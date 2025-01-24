package com.example.real_chat.service;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.rootClient.RootClient;
import com.example.real_chat.repository.RoomRepository;
import com.example.real_chat.service.command.RoomCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RoomCommandServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomCommandService roomCommandService;

    private RootClient rootClient;
    private ChatRoom chatRoom;

    @BeforeEach
    void setUp() {
        // 테스트를 위한 RootClient와 ChatRoom 객체 초기화
        rootClient = setUpRootClient();
        chatRoom = setUpChatRoom(rootClient);
    }

    private ChatRoom setUpChatRoom(RootClient rootClient) {
        return ChatRoom.builder()
                .name("newChatRoom")
                .rootClient(rootClient)
                .build();
    }

    private RootClient setUpRootClient() {
        return RootClient.builder()
                .clientId("clientId")
                .clientPassword("clientPassword")
                .clientName("clientName")
                .build();
    }
}
