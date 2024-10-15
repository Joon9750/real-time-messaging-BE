package com.example.real_chat.repository;

import com.example.real_chat.entity.RootClient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

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
    public RootClient findById(Long id) {
        return entityManager.find(RootClient.class, id);
    }

    @Override
    public void deleteById(Long id) {
        RootClient rootClient = entityManager.find(RootClient.class, id);
        if (rootClient.isDeleted()) throw new RuntimeException();
        else {
            rootClient.delete();
        }
    }
}
