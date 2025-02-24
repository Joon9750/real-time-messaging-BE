package com.example.real_chat.service.user;

import com.example.real_chat.entity.user.User;
import com.example.real_chat.service.builder.ServiceTestDataBuilder;
import com.example.real_chat.service.global.ServiceTest;
import com.example.real_chat.service.query.UserQueryServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserQueryServiceTest extends ServiceTest {

    @InjectMocks
    private UserQueryServiceImpl userQueryService;

    @Test
    @DisplayName("유저 조회 테스트")
    void testGetUserSuccess() {
        // given
        when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));

        // when
        User responseUser = userQueryService.getUserById(user.getId());

        // then
        verify(userRepository, times(1)).findById(user.getId());
        assertEquals(responseUser.getId(), user.getId());
        assertEquals(responseUser.getUserName(), user.getUserName());
        assertEquals(responseUser.getClient(), user.getClient());
        assertEquals(responseUser.getUserChatRooms(), user.getUserChatRooms());
    }

    @Test
    @DisplayName("유저 조회 테스트 - 조회할 유저가 없을 경우")
    void testGetUserFailure_WhenUserNotFound() {
        // given
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        // when & then
        assertThrows(NoSuchElementException.class,
                () -> userQueryService.getUserById(user.getId()));
    }

    @Test
    @DisplayName("모든 유저 조회 테스트")
    void testGetAllUsersSuccess() {
        // given
        List<User> mockUsers = List.of(
                ServiceTestDataBuilder.createSpecificUser(rootClient, 1L, "firstUser"),
                ServiceTestDataBuilder.createSpecificUser(rootClient, 2L, "secondUser"),
                ServiceTestDataBuilder.createSpecificUser(rootClient, 3L, "thirdUser"),
                ServiceTestDataBuilder.createSpecificUser(rootClient, 4L, "fourthUser"),
                ServiceTestDataBuilder.createSpecificUser(rootClient, 5L, "fifthUser")
        );

        when(userRepository.findAll()).thenReturn(mockUsers);

        // when
        List<User> responseUsers = userQueryService.getAllUsers();

        // then
        verify(userRepository, times(1)).findAll();
        assertEquals(mockUsers.size(), responseUsers.size());
        assertEquals(mockUsers.getFirst().getId(), responseUsers.getFirst().getId());
        assertEquals(mockUsers.get(1).getId(), responseUsers.get(1).getId());
        assertEquals(mockUsers.getLast().getId(), responseUsers.getLast().getId());
    }
}
