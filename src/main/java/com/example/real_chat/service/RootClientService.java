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
        return rootClientRepository.findById(id);
    }

    public void deleteRootClient(Long id) {
        rootClientRepository.deleteById(id);
    }

    public void updateRootClient(Long rootClientId, String id, String passward, String name) {
        RootClient rootClient = rootClientRepository.findById(rootClientId);
        rootClient.update(id, passward, name); // 이걸로 변경감지 되지 않나? -> 변경 감지 적용 안되네
    }
}
