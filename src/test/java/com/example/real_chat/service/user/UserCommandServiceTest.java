package com.example.real_chat.service.user;

import com.example.real_chat.entity.user.User;
import com.example.real_chat.service.command.UserCommandServiceImpl;
import com.example.real_chat.service.global.ServiceTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserCommandServiceTest extends ServiceTest {

    @InjectMocks
    private UserCommandServiceImpl userCommandService;

    @Test
    @DisplayName("유저 생성 테스트 - 조회한 루트 클라이언트가 존재할 경우")
    void testAddUserSuccess() {
        // given
        when(rootClientRepository.findById(rootClient.getId())).thenReturn(Optional.ofNullable(rootClient));

        // when
        userCommandService.addUser(user.getUserName(), rootClient.getId());

        // then
        // User 객체를 addUser 메소드에서 생성한다.
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("유저 생성 테스트 - 조회한 루트 클라이언트가 존재할 않을 경우")
    void testAddUserFailure_WhenRootClientNotFound() {
        // given
        when(rootClientRepository.findById(rootClient.getId())).thenReturn(Optional.empty());

        // when & then
        assertThrows(NoSuchElementException.class,
                () -> userCommandService.addUser(user.getUserName(), rootClient.getId()));
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    @DisplayName("유저 삭제 테스트")
    // 이때 해당 유저가 삭제 방에 있으면 해당 방에서 나가지는지 검증 필요하다.
    // User 엔티티의 CascadeType.REMOVE로 설정되어 있다.
    void testRemoveUserSuccess() {
        // given
        when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));

        // when
        userCommandService.delete(user.getId());

        // then
        verify(userRepository, times(1)).findById(user.getId());
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    @DisplayName("유저 삭테 테스트 - 이미 삭제되었거나 없는 유저를 삭제할 경우")
    void testRemoveUserFailure_WhenUserNotFound() {
        // given
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        // when & then
        assertThrows(NoSuchElementException.class,
                () -> userCommandService.delete(user.getId()));
        verify(userRepository, times(1)).findById(user.getId());
        verify(userRepository, times(0)).delete(user);
    }
}