package com.example.real_chat.service.userchatroom;

import com.example.real_chat.entity.userchatroom.UserChatRoom;
import com.example.real_chat.global.exception.CannotJoinChatRoomException;
import com.example.real_chat.service.command.UserChatRoomCommandServiceImpl;
import com.example.real_chat.service.global.ServiceTest;
import com.example.real_chat.service.query.RoomQueryService;
import com.example.real_chat.service.query.UserQueryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserChatRoomCommandServiceTest extends ServiceTest {

    @InjectMocks
    private UserChatRoomCommandServiceImpl userChatRoomCommandService;

    @Mock UserQueryService userQueryService;
    @Mock RoomQueryService roomQueryService;

    @Test
    @DisplayName("채팅방에 유저가 들어갈 경우 joinChatRoom 메소드가 호출되고, 중간 테이블인 userchatroom에 정상 저장됩니다.")
    void testJoinChatRoomSuccess() {
        // given
        when(userChatRoomRepository.existsByUserAndChatRoom(user, chatRoom)).thenReturn(false);
    }

    @Test
    @DisplayName("이미 해당 유저가 채팅방에 들어가 있는 경우 - CannotJoinChatRoomException 던짐")
    void testJoinChatRoomFailure_WhenAlreadyUserJoinedChatRoom() {
        // given
        when(userChatRoomRepository.existsByUserAndChatRoom(user, chatRoom)).thenReturn(true);
        when(userQueryService.getUserById(user.getId())).thenReturn(user);
        when(roomQueryService.getRoom(chatRoom.getId())).thenReturn(chatRoom);

        // when & then
        assertThrows(CannotJoinChatRoomException.class, () -> {
            userChatRoomCommandService.joinChatRoom(user.getId(), chatRoom.getId());
        });

        verify(userChatRoomRepository, times(1)).existsByUserAndChatRoom(user, chatRoom);
        verify(userChatRoomRepository, times(0)).save(any(UserChatRoom.class));
    }

    @Test
    @DisplayName("채팅방을 찾을 수 없는 경우 - ")
    void testJoinChatRoomFailure_WhenCannotFoundChatRoom() {
        // given

    }

    @Test
    @DisplayName("유저를 찾을 수 없는 경우 - ")
    void testJoinChatRoomFailure_WhenCannotFoundUser() {
        // given

    }
}
