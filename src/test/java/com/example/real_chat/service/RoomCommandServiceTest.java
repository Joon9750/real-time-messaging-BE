package com.example.real_chat.service;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.rootClient.RootClient;
import com.example.real_chat.repository.RoomRepository;
import com.example.real_chat.service.command.RoomCommandServiceImpl;
import com.example.real_chat.service.command.UserChatRoomCommandService;
import com.example.real_chat.service.query.RootClientQueryService;
import com.example.real_chat.service.query.RootClientQueryServiceImpl;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomCommandServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private RootClientQueryServiceImpl rootClientQueryService;

    @Mock
    private UserChatRoomCommandService userChatRoomCommandService;

    @InjectMocks
    private RoomCommandServiceImpl roomCommandService;

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

    @Test
    @DisplayName("체팅방 생성 테스트")
    void testAddRoomSuccess() {
        // given
        Long roomId = 1L;
        when(rootClientQueryService.getRootClient(any())).thenReturn(rootClient);
        when(roomRepository.save(any(ChatRoom.class))).thenReturn(roomId);

        // when
        Long id = roomCommandService.addRoom(chatRoom.getName(), rootClient.getId());

        // then
        assertEquals(roomId, id);
        verify(roomRepository, times(1)).save(any(ChatRoom.class));
    }

    @Test
    @DisplayName("체팅방 생성 실패 테스트 - rootClient가 존재하지 않을 때")
    void testAddRoomFailureWhenRootClientNotFound() {
        // given

    }

    @Test
    @DisplayName("채팅방 수정 테스트")
    void testUpdateChatRoomSuccess() {

    }

    @Test
    @DisplayName("채팅방 삭제 테스트 - 방에 속해 있던 사용자들 모두 방에서 나가짐")
    void testDeleteRoomSuccess() {

    }
}
