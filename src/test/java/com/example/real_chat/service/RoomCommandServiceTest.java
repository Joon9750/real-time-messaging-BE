package com.example.real_chat.service;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.rootClient.RootClient;
import com.example.real_chat.repository.RoomRepository;
import com.example.real_chat.service.command.RoomCommandServiceImpl;
import com.example.real_chat.service.command.UserChatRoomCommandServiceImpl;
import com.example.real_chat.service.query.RootClientQueryServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomCommandServiceTest {

    @Mock private RoomRepository roomRepository;
    @Mock private RootClientQueryServiceImpl rootClientQueryService;
    // 호출부는 없으나 testDeleteRoomSuccess 메소드에서 deleteUserChatRoom 메소드를 호출할 때 의존성 주입되어야 한다.
    @Mock private UserChatRoomCommandServiceImpl userChatRoomCommandService;

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
        when(rootClientQueryService.getRootClient(any())).thenThrow(NoSuchElementException.class);

        // when & then
        RuntimeException exception = assertThrows(NoSuchElementException.class,
                () -> roomCommandService.addRoom(chatRoom.getName(), rootClient.getId()));

        // then
        assertEquals("NoSuchElementException", exception.getClass().getSimpleName());
        verify(roomRepository, times(0)).save(any(ChatRoom.class));
    }

    @Test
    @DisplayName("채팅방 수정 테스트 - chatRoom이 존재하는 경우")
    void testUpdateChatRoomSuccess() {
        // given
        String roomNewName = "newRoomName";
        when(roomRepository.findById(anyLong())).thenReturn(Optional.ofNullable(chatRoom));

        // // Unnecessary stubbings detected. 발생한다. 불필요한 Stub에 해당되기 때문
        // when(roomRepository.save(any(ChatRoom.class))).thenReturn(chatRoom.getId());

        // when
        roomCommandService.updateChatRoom(chatRoom.getId(), roomNewName);

        // then
        // chatRoom 수정이 Entity Dirty Checking으로 이루어지기 때문에 save가 0번 호출된다.
        verify(roomRepository, times(0)).save(any(ChatRoom.class));
        assertEquals(chatRoom.getName(), roomNewName);
    }

    @Test
    @DisplayName("채팅방 수정 테스트 - chatRoom이 존재하지 않는 경우")
    void testUpdateChatRoomFailureWhenChatRoomNotFound() {
        // given
        when(roomRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when & then
        RuntimeException exception = assertThrows(NoSuchElementException.class,
                () -> roomCommandService.updateChatRoom(0L, "name"));

        // then
        assertEquals("NoSuchElementException", exception.getClass().getSimpleName());
    }

    @Test
    @DisplayName("채팅방 삭제 테스트 - 채팅방 삭제 경우 방에 속해 있는 사람들도 방에서 탈퇴됨.")
    void testDeleteRoomSuccess() {
        // given
        when(roomRepository.findById(anyLong())).thenReturn(Optional.ofNullable(chatRoom));

        // when
        roomCommandService.deleteRoom(chatRoom.getId());

        // then
        verify(userChatRoomCommandService, times(1)).leaveUserChatRoomByChatRoomId(chatRoom.getId());
        verify(roomRepository, times(1)).delete(chatRoom);
    }

    @Test
    @DisplayName("채팅방 삭제 테스트 - 삭제하려는 채팅방이 없는 경우")
    void testDeleteRoomFailureWhenChatRoomNotFound() {
        // given
        when(roomRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when & then
        RuntimeException exception = assertThrows(NoSuchElementException.class,
                () -> roomCommandService.deleteRoom(0L));

        // then
        verify(userChatRoomCommandService, times(0)).leaveUserChatRoomByChatRoomId(chatRoom.getId());
        verify(roomRepository, times(0)).delete(chatRoom);
    }
}
