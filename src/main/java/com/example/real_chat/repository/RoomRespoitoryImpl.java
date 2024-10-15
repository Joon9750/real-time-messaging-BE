package com.example.real_chat.repository;

import com.example.real_chat.entity.ChatRoom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomRespoitoryImpl implements RoomRepository {

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
        // entityManager에서는 지우지 않고 isdeleted로만 판별
        // entityManager.remove(entityManager.find(Room.class, id));
    }

    @Override
    public List<ChatRoom> findAll() {
        return entityManager.createQuery("select m from ChatRoom m", ChatRoom.class)
                .getResultList();
    }
}
