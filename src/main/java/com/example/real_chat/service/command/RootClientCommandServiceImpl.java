package com.example.real_chat.service.command;

import com.example.real_chat.entity.rootClient.RootClient;
import com.example.real_chat.repository.RootClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RootClientCommandServiceImpl implements RootClientCommandService {

    private final RootClientRepository rootClientRepository;

    @Override
    public Long addRootClient(RootClient rootClient) {
        rootClientRepository.save(rootClient);
        return rootClient.getId();
    }

    @Override
    public void deleteRootClient(Long id) {
        RootClient client = rootClientRepository.findById(id).orElseThrow();
        if (client.isDeleted()) throw new RuntimeException();
        else client.delete();
    }

    @Override
    public void updateRootClient(Long rootClientId, String id, String password, String name) {
        RootClient rootClient = rootClientRepository.findById(rootClientId).orElseThrow();
        rootClient.update(id, password, name);
    }
}
