package com.example.real_chat.service.userchatroom;

import com.example.real_chat.entity.userchatroom.UserChatRoom;
import com.example.real_chat.global.exception.CannotJoinChatRoomException;
import com.example.real_chat.service.builder.ServiceTestDataBuilder;
import com.example.real_chat.service.command.UserChatRoomCommandServiceImpl;
import com.example.real_chat.service.global.ServiceTest;
import com.example.real_chat.service.query.RoomQueryService;
import com.example.real_chat.service.query.UserQueryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
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
        when(userQueryService.getUserById(user.getId())).thenReturn(user);
        when(roomQueryService.getRoom(chatRoom.getId())).thenReturn(chatRoom);

        // joinChat 메소드에서 userChatRoom 객체를 따로 생성하기 때문에 ServiceTest의 UserChatRoom 객체랑 다르다. 따라서 any() 사용했다.
        when(userChatRoomRepository.save(any(UserChatRoom.class))).thenReturn(userChatRoom.getId());

        // when
        Long userChatRoomId = userChatRoomCommandService.joinChatRoom(user.getId(), chatRoom.getId());

        // then
        verify(roomQueryService, times(1)).getRoom(chatRoom.getId());
        verify(userQueryService, times(1)).getUserById(user.getId());
        verify(userChatRoomRepository, times(1)).existsByUserAndChatRoom(user, chatRoom);
        verify(userChatRoomRepository, times(1)).save(any(UserChatRoom.class));

        // 엔티티 연관관계 설정 - user.getUserChatRooms()에 userChatRoom이 추가되었는지 검증
        assertThat(user.getUserChatRooms()).hasSize(1);
        assertThat(user.getUserChatRooms()).extracting("chatRoom").contains(chatRoom);
    }

    @Test
    @DisplayName("이미 해당 유저가 채팅방에 들어가 있는 경우 - CannotJoinChatRoomException 예외 발생")
    void testJoinChatRoomFailure_WhenAlreadyUserJoinedChatRoom() {
        // given
        when(userChatRoomRepository.existsByUserAndChatRoom(user, chatRoom)).thenReturn(true);
        when(userQueryService.getUserById(user.getId())).thenReturn(user);
        when(roomQueryService.getRoom(chatRoom.getId())).thenReturn(chatRoom);

        // when & then
        assertThrows(CannotJoinChatRoomException.class, () -> {
            userChatRoomCommandService.joinChatRoom(user.getId(), chatRoom.getId());
        });

        verify(roomQueryService, times(1)).getRoom(chatRoom.getId());
        verify(userQueryService, times(1)).getUserById(user.getId());
        verify(userChatRoomRepository, times(1)).existsByUserAndChatRoom(user, chatRoom);
        verify(userChatRoomRepository, times(0)).save(any(UserChatRoom.class));

        assertThat(user.getUserChatRooms()).hasSize(0);
    }

    @Test
    @DisplayName("채팅방을 찾을 수 없는 경우 - NoSuchElementException 예외 발생")
    void testJoinChatRoomFailure_WhenCannotFoundChatRoom() {
        // given
        when(userQueryService.getUserById(user.getId())).thenThrow(new NoSuchElementException());

        // when & then
        assertThrows(NoSuchElementException.class, () -> {
           userChatRoomCommandService.joinChatRoom(user.getId(), chatRoom.getId());
        });
    }

    @Test
    @DisplayName("유저를 찾을 수 없는 경우 - NoSuchElementException 예외 발생")
    void testJoinChatRoomFailure_WhenCannotFoundUser() {
        // given
        when(userQueryService.getUserById(user.getId())).thenReturn(user);
        when(roomQueryService.getRoom(chatRoom.getId())).thenThrow(new NoSuchElementException());

        // when & then
        assertThrows(NoSuchElementException.class, () -> {
            userChatRoomCommandService.joinChatRoom(user.getId(), chatRoom.getId());
        });
    }

    @Test
    @DisplayName("채팅방이 삭제되면 방에 속한 유저들 모두 해당 방에서 삭제된다.")
    void testLeaveUserChatRoomByChatRoomIdSuccess() {
    }

    @Test
    @DisplayName("삭제할 채팅방을 찾을 수 없는 경우 - NoSuchElementException 예외 발생")
    void testLeaveUserChatRoomByChatRoomIdFailure_WhenCannotFoundChatRoom() {
        // when
        when(roomQueryService.getRoom(chatRoom.getId())).thenThrow(new NoSuchElementException());

        // when & then
        assertThrows(NoSuchElementException.class, () -> {
            userChatRoomCommandService.leaveUserChatRoomByChatRoomId(chatRoom.getId());
        });

        verify(userChatRoomRepository, times(0)).findByChatRoom(chatRoom);
        verify(userChatRoomRepository, times(0)).delete(any(UserChatRoom.class));
    }
}
