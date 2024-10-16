package com.example.real_chat.repository;

import com.example.real_chat.entity.ChatRoom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public ChatRoom findById(Long id) {
        return entityManager.find(ChatRoom.class, id);
    }

    @Override
    public void delete(Long id) throws RuntimeException {
        ChatRoom chatRoom = entityManager.find(ChatRoom.class, id);
        if (chatRoom.isDeleted()) throw new RuntimeException();
        else {
            chatRoom.delete();
        }
    }

    @Override
    public List<ChatRoom> findAll() {
        return entityManager.createQuery("select m from ChatRoom m", ChatRoom.class)
                .getResultList();
    }

    @Override
    public List<ChatRoom> findUnDeletedRooms() {
        return entityManager.createQuery("select m from ChatRoom m where m.deletedAt is null", ChatRoom.class)
                .getResultList();
    }
}
