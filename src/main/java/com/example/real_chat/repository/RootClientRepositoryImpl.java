package com.example.real_chat.repository;

import com.example.real_chat.entity.RootClient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RootClientRepositoryImpl implements RootClientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long save(RootClient rootClient) {
        entityManager.persist(rootClient);
        return rootClient.getId();
    }

    @Override
    public Optional<RootClient> findById(Long id) {
        RootClient rootClient = entityManager.find(RootClient.class, id);
        return Optional.ofNullable(rootClient);
    }
}
