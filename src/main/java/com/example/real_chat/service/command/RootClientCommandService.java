package com.example.real_chat.service.command;

import com.example.real_chat.entity.rootClient.RootClient;

public interface RootClientCommandService {

    Long addRootClient(RootClient rootClient);
    // root client는 실제로 DB에서 삭제하지 않고 isDelete 인스턴스로 삭제 여부 확인
    void deleteRootClient(Long id);
    void updateRootClient(Long rootClientId, String id, String password, String name);
}
