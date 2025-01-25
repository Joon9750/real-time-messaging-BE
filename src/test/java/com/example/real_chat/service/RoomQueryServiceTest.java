package com.example.real_chat.service;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.rootClient.RootClient;
import com.example.real_chat.service.query.RoomQueryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RoomQueryServiceTest {

    @InjectMocks
    private RoomQueryServiceImpl roomQueryService;

    private RootClient rootClient;
    private ChatRoom chatRoom;

    @BeforeEach
    void setUp() {
        rootClient = setUpRootClient();
        chatRoom = setUpChatRoom(rootClient);
    }

    private ChatRoom setUpChatRoom(RootClient rootClient) {
        return ChatRoom.builder()
                .id(1L)
                .name("newChatRoom")
                .rootClient(rootClient)
                .build();
    }

    private RootClient setUpRootClient() {
        return RootClient.builder()
                .id(1L)
                .clientId("clientId")
                .clientPassword("clientPassword")
                .clientName("clientName")
                .build();
    }
}
