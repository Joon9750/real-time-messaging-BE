package com.example.real_chat.repository;

import com.example.real_chat.entity.rootClient.RootClient;

import java.util.Optional;

public interface RootClientRepository {

    Long save(RootClient rootClient);

    Optional<RootClient> findById(Long id);
}
