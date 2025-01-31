package com.example.real_chat.service.root_client;

import com.example.real_chat.entity.rootClient.RootClient;
import com.example.real_chat.service.builder.ServiceTestDataBuilder;
import com.example.real_chat.service.global.ServiceTest;
import com.example.real_chat.service.command.RootClientCommandServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RootClientCommandServiceTest extends ServiceTest {

    @InjectMocks
    private RootClientCommandServiceImpl rootClientCommandService;

    @Test
    @DisplayName("루트 회원 생성 테스트")
    void testAddRootClientSuccess() {
        // given
        when(rootClientRepository.save(rootClient)).thenReturn(rootClient.getId());

        // when
        Long responseId = rootClientCommandService.addRootClient(rootClient);

        // then
        assertEquals(responseId, rootClient.getId());
        verify(rootClientRepository, times(1)).save(rootClient);
    }

    @Test
    @DisplayName("루트 회원 삭제 성공 테스트 - 존재하는 회원이고 아직 삭제되지 않은 상태일 때")
    void testDeleteRootClientSuccess() {
        // given
        RootClient spyRootClient = spy(ServiceTestDataBuilder.createRootClient());
        when(rootClientRepository.findById(spyRootClient.getId())).thenReturn(Optional.of(spyRootClient));

        // when
        rootClientCommandService.deleteRootClient(rootClient.getId());

        // then
        verify(rootClientRepository, times(1)).findById(rootClient.getId());
        verify(spyRootClient, times(1)).isDeleted();
        verify(spyRootClient, times(1)).delete();
    }

    @Test
    @DisplayName("이미 삭제된 루트 회원 삭제시 RootClientAlreadyDeletedException 발생 테스트")
    void testDeleteRootClientFailure_WhenAlreadyDeleted() {
    }

    @Test
    @DisplayName("루트 회원 업데이트 테스트 - 정상 동작")
    void testUpdateRootClientSuccess() {
    }

    @Test
    @DisplayName("존재하지 않는 루트 회원 업데이트 시 예외 발생 테스트")
    void testUpdateRootClientFailure_WhenNotFound() {
    }
}