package com.example.real_chat.service.rootclient;

import com.example.real_chat.entity.rootclient.RootClient;
import com.example.real_chat.global.exception.RootClientAlreadyDeletedException;
import com.example.real_chat.service.builder.ServiceTestDataBuilder;
import com.example.real_chat.service.global.ServiceTest;
import com.example.real_chat.service.command.RootClientCommandServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.NoSuchElementException;
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
        rootClientCommandService.deleteRootClient(spyRootClient.getId());

        // then
        verify(rootClientRepository, times(1)).findById(rootClient.getId());
        verify(spyRootClient, times(1)).isDeleted();
        verify(spyRootClient, times(1)).delete();
        assertTrue(spyRootClient.isDeleted());
        assertNotNull(spyRootClient.getDeletedAt());
    }

    @Test
    @DisplayName("존재하지 않은 루트 회원을 삭제하려 할 때")
    void testDeleteRootClientFailure_WhenRootClientIsNotFound() {
        // given
        when(rootClientRepository.findById(rootClient.getId())).thenReturn(Optional.empty());

        // when & then
        assertThrows(NoSuchElementException.class, () -> {
            rootClientCommandService.deleteRootClient(rootClient.getId());
        });
    }

    @Test
    @DisplayName("이미 삭제된 루트 회원 삭제 시 RootClientAlreadyDeletedException 발생 테스트")
    void testDeleteRootClientFailure_WhenAlreadyDeleted() {
        // given
        RootClient spyRootClient = spy(ServiceTestDataBuilder.createRootClient());
        doReturn(true).when(spyRootClient).isDeleted();

        when(rootClientRepository.findById(spyRootClient.getId())).thenReturn(Optional.of(spyRootClient));

        // when & then
        assertThrows(RootClientAlreadyDeletedException.class, () -> {
            rootClientCommandService.deleteRootClient(spyRootClient.getId());
        });

        // verify: isDeleted()가 호출되었는지 확인
        verify(spyRootClient, times(1)).isDeleted();
        verify(spyRootClient, times(0)).delete();
    }

    @Test
    @DisplayName("루트 회원 정보 업데이트 성공 테스트 - 빈 문자열로 수정을 할 경우 수정 사항이 반영되지 않음")
    void testUpdateRootClientSuccess() {
        // given
        RootClient spyRootClient = spy(ServiceTestDataBuilder.createRootClient());
        when(rootClientRepository.findById(spyRootClient.getId())).thenReturn(Optional.of(spyRootClient));

        // when
        rootClientCommandService.updateRootClient(spyRootClient.getId(), "newId", "", "newName");

        // then
        verify(spyRootClient, times(1)).update("newId", spyRootClient.getClientPassword(), "newName");
    }

    @Test
    @DisplayName("존재하지 않는 루트 회원 업데이트 시 예외 발생 테스트")
    void testUpdateRootClientFailure_WhenNotFound() {
        // given
        when(rootClientRepository.findById(rootClient.getId())).thenReturn(Optional.empty());

        // when & then
        assertThrows(NoSuchElementException.class, () -> {
            rootClientCommandService.updateRootClient(rootClient.getId(), "newId", "newPassword", "newName");
        });
    }
}