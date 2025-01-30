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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
}
