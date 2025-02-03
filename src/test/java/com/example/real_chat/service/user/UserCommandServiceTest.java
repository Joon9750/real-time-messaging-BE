package com.example.real_chat.service.user;

import com.example.real_chat.entity.user.User;
import com.example.real_chat.repository.UserRepository;
import com.example.real_chat.service.command.UserCommandServiceImpl;
import com.example.real_chat.service.global.ServiceTest;
import com.example.real_chat.service.query.RootClientQueryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.longThat;
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
    }

    @Test
    @DisplayName("유저 삭제 테스트")
    void testRemoveUserSuccess() {

    }

    @Test
    @DisplayName("유저 삭테 테스트 - 이미 삭제되었거나 없는 유저를 삭제할 경우")
    void testRemoveUserFailure_WhenUserNotFound() {

    }
}