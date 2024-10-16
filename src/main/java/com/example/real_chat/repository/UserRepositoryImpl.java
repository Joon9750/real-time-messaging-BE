package com.example.real_chat.repository;

import com.example.real_chat.entity.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long save(User user) {
        entityManager.persist(user);
        return user.getId();
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<List<User>> findAll() {
        List<User> users = entityManager.createQuery("select u from User u", User.class)
                .getResultList();
        return Optional.ofNullable(users);
    }

    @Override
    public Optional<List<User>> findUnDeletedUsers() {
        List<User> undeletedUsers = entityManager.createQuery("select u from User u where u.deletedAt is null", User.class)
                .getResultList();
        return Optional.ofNullable(undeletedUsers);
    }
}
