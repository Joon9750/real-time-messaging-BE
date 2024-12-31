package com.example.real_chat.service;

import com.example.real_chat.entity.rootClient.RootClient;
import com.example.real_chat.global.exception.RootClientAlreadyDeletedException;
import com.example.real_chat.repository.RootClientRepository;
import com.example.real_chat.service.command.RootClientCommandService;
import com.example.real_chat.service.query.RootClientQueryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
class RootClientServiceTest {

    @Autowired private RootClientCommandService rootClientCommandService;
    @Autowired private RootClientQueryService rootClientQueryService;

    @MockitoBean
    private RootClientRepository rootClientRepository;

    @Test
    @DisplayName("루트 회원 생성 테스트")
    void addRootClient() {
        // given
        RootClient rootClient = RootClient.createRootClient("1", "11111", "hong");
        when(rootClientRepository.save(any(RootClient.class)))
                .thenReturn(rootClient.getId());

        // when
        Long id = rootClientCommandService.addRootClient(rootClient);

        // then
        assertEquals(id, rootClient.getId());
        verify(rootClientRepository, times(1)).save(any(RootClient.class));
    }

    @Test
    @DisplayName("루트 회원 삭제 성공")
    void testDeleteRootClient_Success() {
        // Given
        Long id = 1L;
        RootClient mockRootClient = mock(RootClient.class);
        when(rootClientRepository.findById(id)).thenReturn(Optional.of(mockRootClient));
        when(mockRootClient.isDeleted()).thenReturn(false);

        // When
        rootClientCommandService.deleteRootClient(id);

        // Then
        verify(mockRootClient, times(1)).delete(); // RootClient class delete 메서드가 호출되었는지 확인
        verify(rootClientRepository, times(1)).findById(id); // findById 호출 확인
        assertNotNull(rootClientRepository.findById(id)); // RootClient는 삭제되더라도 서버에서 값 유지
    }

    @Test
    @DisplayName("이미 삭제된 루트 회원 삭제시 RootClientAlreadyDeletedException 발생")
    void testDeleteRootClient_AlreadyDeleted() {
        // Given
        Long id = 1L;
        RootClient mockRootClient = mock(RootClient.class);
        when(rootClientRepository.findById(id)).thenReturn(Optional.of(mockRootClient));
        when(mockRootClient.isDeleted()).thenReturn(true);

        // When & Then
        RootClientAlreadyDeletedException exception = assertThrows(RootClientAlreadyDeletedException.class, () -> {
            rootClientCommandService.deleteRootClient(id);
        });

        assertEquals("ID " + id + "의 RootClient는 이미 삭제되었습니다.", exception.getMessage());
        verify(mockRootClient, never()).delete(); // RootClient class delete 메서드가 호출되지 않았는지 확인
        verify(rootClientRepository, times(1)).findById(id); // findById 호출 확인
    }
}