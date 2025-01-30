package com.example.real_chat.service.room;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.rootClient.RootClient;
import com.example.real_chat.repository.RoomRepository;
import com.example.real_chat.service.builder.ServiceTestDataBuilder;
import com.example.real_chat.service.query.RoomQueryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomQueryServiceTest {

    @Mock private RoomRepository roomRepository;

    @InjectMocks
    private RoomQueryServiceImpl roomQueryService;

    private ChatRoom chatRoom;

    @BeforeEach
    void setUp() {
        RootClient rootClient = ServiceTestDataBuilder.createRootClient();
        chatRoom = ServiceTestDataBuilder.createChatRoom(rootClient);
    }

    @Test
    @DisplayName("단일 채팅방 조회 테스트")
    void testGetChatRoomSuccess() {
        // given
        when(roomRepository.findById(anyLong())).thenReturn(Optional.ofNullable(chatRoom));

        // when
        roomQueryService.getRoom(chatRoom.getId());

        // then
        verify(roomRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("단일 채팅방 조회 테스트 - 채팅방이 존재하지 않을 경우")
    void testGetChatRoomFailure_WhenChatRoomNotFound() {
        // given
        when(roomRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
                () -> roomQueryService.getRoom(chatRoom.getId()));

        // then
        assertEquals("NoSuchElementException", exception.getClass().getSimpleName());
        verify(roomRepository, times(1)).findById(anyLong());
    }
}
