package com.example.real_chat.service.command;

import com.example.real_chat.entity.rootClient.RootClient;

public interface RootClientCommandService {

    Long addRootClient(RootClient rootClient);
    void deleteRootClient(Long id);
    void updateRootClient(Long rootClientId, String id, String password, String name);
}
