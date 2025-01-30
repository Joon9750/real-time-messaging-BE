package com.example.real_chat.service.root_client;

import com.example.real_chat.entity.rootClient.RootClient;
import com.example.real_chat.repository.RootClientRepository;
import com.example.real_chat.service.builder.ServiceTestDataBuilder;
import com.example.real_chat.service.query.RootClientQueryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RootClientQueryServiceTest {

    @Mock private RootClientRepository rootClientRepository;

    @InjectMocks
    private RootClientQueryServiceImpl rootClientQueryService;

    private RootClient rootClient;

    @BeforeEach
    void setUp() {
        rootClient = ServiceTestDataBuilder.createRootClient();
    }

    @Test
    @DisplayName("")
    void testGetRootClientSuccess() {
        // given
        when(rootClientRepository.findById(rootClient.getId())).thenReturn(Optional.ofNullable(rootClient));

        // when
        RootClient response = rootClientQueryService.getRootClient(rootClient.getId());

        // then
        verify(rootClientRepository, times(1)).findById(rootClient.getId());
        assertEquals(rootClient.getId(), response.getId());
    }
}
