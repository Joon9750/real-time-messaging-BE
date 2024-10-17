package com.example.real_chat.service;

import com.example.real_chat.entity.rootClient.RootClient;
import com.example.real_chat.repository.RootClientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
class RootClientServiceTest {

    @Autowired
    private RootClientService rootClientService;

    @MockitoBean
    private RootClientRepository rootClientRepository;

    @Test
    void addRootClient() {
        // given
        RootClient rootClient = RootClient.createRootClient("1", "11111", "hong");

        // mocking
        when(rootClientRepository.save(any(RootClient.class))).thenReturn(rootClient.getId());

        // when
        Long addedClient = rootClientService.addRootClient(rootClient);

        // then
        assertEquals(rootClient.getId(), addedClient);
        verify(rootClientRepository, times(1)).save(rootClient);
    }

    @Test
    void getRootClient() {
        // given
        Long id = 1L;
        RootClient rootClient = RootClient.createRootClient("1", "11111", "hong");

        // mocking
        when(rootClientRepository.findById(id)).thenReturn(Optional.of(rootClient));

        // when: 서비스 메서드 호출
        RootClient rootClient1 = rootClientService.getRootClient(id);

        // then
        assertNotNull(rootClient1);
        assertEquals(rootClient.getId(), rootClient1.getId());
        verify(rootClientRepository, times(1)).findById(id);
    }

    @Test
    void deleteRootClient() {
        // given
        Long id = 1L;
        RootClient rootClient = RootClient.createRootClient("1", "11111", "hong");

        // mocking
        when(rootClientRepository.findById(id)).thenReturn(Optional.of(rootClient));

        // when
        rootClientService.deleteRootClient(id);

        // then
        verify(rootClientRepository, times(1)).findById(id);
        verify(rootClientRepository, times(1)).deleteById(id);
    }

    @Test
    void updateRootClient() {
        // given
        Long id = 1L;
        String newId = "1";
        String newPassword = "22222";
        String newName = "kim";

        RootClient existingRootClient = Mockito.mock(RootClient.class);

        // mocking
        when(rootClientRepository.findById(id)).thenReturn(Optional.of(existingRootClient));
        doNothing().when(existingRootClient).update(newId, newPassword, newName); // mock의 update 메서드 설정

        // when
        rootClientService.updateRootClient(id, newId, newPassword, newName);

        // then
        verify(rootClientRepository, times(1)).findById(id);
        verify(existingRootClient, times(1)).update(newId, newPassword, newName); // update 호출 검증
    }
}