package com.example.real_chat.repository;

import com.example.real_chat.entity.room.ChatRoom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoomRepositoryImpl implements RoomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long save(ChatRoom chatRoom) {
        entityManager.persist(chatRoom);
        return chatRoom.getId();
    }

    @Override
    public Optional<ChatRoom> findById(Long id) {
        ChatRoom chatRoom = entityManager.find(ChatRoom.class, id);
        return Optional.ofNullable(chatRoom);
    }

    @Override
    public List<ChatRoom> findAll() {
        return entityManager.createQuery("select m from ChatRoom m", ChatRoom.class)
                .getResultList();
    }

//    @Override
//    public List<ChatRoom> findAll() {
//        return entityManager.createQuery("select m from ChatRoom m" +
//                        " join fetch m.rootClient rc", ChatRoom.class)
//                .getResultList();
//    }

    @Override
    public List<ChatRoom> findUnDeletedRooms() {
        return entityManager.createQuery("select m from ChatRoom m where m.deletedAt is null", ChatRoom.class)
                .getResultList();
    }
}
