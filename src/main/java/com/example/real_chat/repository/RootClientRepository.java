package com.example.real_chat.repository;

import com.example.real_chat.entity.RootClient;

public interface RootClientRepository {

    Long save(RootClient rootClient);
    RootClient findById(Long id);
    void deleteById(Long id);
}
