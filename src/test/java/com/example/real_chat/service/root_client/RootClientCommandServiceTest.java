package com.example.real_chat.service.root_client;

import com.example.real_chat.entity.rootClient.RootClient;
import com.example.real_chat.repository.RootClientRepository;
import com.example.real_chat.service.builder.ServiceTestDataBuilder;
import com.example.real_chat.service.command.RootClientCommandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RootClientCommandServiceTest {

    @Mock private RootClientRepository rootClientRepository;

    @InjectMocks
    private RootClientCommandServiceImpl rootClientCommandService;

    private RootClient rootClient;

    @BeforeEach
    void setUp() {
        rootClient = ServiceTestDataBuilder.createRootClient();
    }

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
    @DisplayName("루트 회원 삭제 성공")
    void testDeleteRootClientSuccess() {
    }

    @Test
    @DisplayName("이미 삭제된 루트 회원 삭제시 RootClientAlreadyDeletedException 발생")
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
