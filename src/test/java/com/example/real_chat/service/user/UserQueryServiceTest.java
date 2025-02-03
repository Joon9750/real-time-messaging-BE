package com.example.real_chat.service.user;

import com.example.real_chat.service.global.ServiceTest;
import com.example.real_chat.service.query.UserQueryServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

public class UserQueryServiceTest extends ServiceTest {

    @InjectMocks
    private UserQueryServiceImpl userQueryService;

    @Test
    @DisplayName("유저 조회 테스트")
    void testGetUserSuccess() {

    }

    @Test
    @DisplayName("유저 조회 테스트 - 조회할 유저가 없을 경우")
    void testGetUserFailure_WhenUserNotFound() {

    }

    @Test
    @DisplayName("전체 유저 조회 테스트")
    void testGetAllUserSuccess() {

    }
}
