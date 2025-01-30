package com.example.real_chat.service.root_client;

import com.example.real_chat.entity.rootClient.RootClient;
import com.example.real_chat.service.global.ServiceTest;
import com.example.real_chat.service.query.RootClientQueryServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RootClientQueryServiceTest extends ServiceTest {

    @InjectMocks
    private RootClientQueryServiceImpl rootClientQueryService;

    @Test
    @DisplayName("루트 회원 조회 테스트 - 존재하는 아이디로 조회")
    void testGetRootClientSuccess() {
        // given
        when(rootClientRepository.findById(rootClient.getId())).thenReturn(Optional.ofNullable(rootClient));

        // when
        RootClient response = rootClientQueryService.getRootClient(rootClient.getId());

        // then
        verify(rootClientRepository, times(1)).findById(rootClient.getId());
        assertEquals(rootClient.getId(), response.getId());
    }

    @Test
    @DisplayName("루트 회원 조회 테스트 - 존재하는 않는 아이디로 조회")
    void testGetRootClientFailure_WhenRootClientNotFound() {
        // given
        Long id = 1L;
        when(rootClientRepository.findById(id)).thenReturn(Optional.empty());

        // when & then
        assertThrows(NoSuchElementException.class,
                () -> rootClientQueryService.getRootClient(id));
    }
}
