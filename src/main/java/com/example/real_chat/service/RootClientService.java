package com.example.real_chat.service;

import com.example.real_chat.entity.RootClient;
import com.example.real_chat.repository.RootClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RootClientService {

    private final RootClientRepository rootClientRepository;

    public Long addRootClient(RootClient rootClient) {
        rootClientRepository.save(rootClient);
        return rootClient.getId();
    }

    public RootClient getRootClient(Long id) {
        return rootClientRepository.findById(id).orElseThrow();
    }

    public void deleteRootClient(Long id) {
        RootClient client = rootClientRepository.findById(id).orElseThrow();
        if (client.isDeleted()) throw new RuntimeException();
        else client.delete();
    }

    public void updateRootClient(Long rootClientId, String id, String password, String name) {
        RootClient rootClient = rootClientRepository.findById(rootClientId).orElseThrow();
        rootClient.update(id, password, name);
    }
}
