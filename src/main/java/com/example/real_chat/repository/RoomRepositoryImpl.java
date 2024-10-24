package com.example.real_chat.repository;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.userChatRoom.UserChatRoom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

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

    @Override
    public void delete(ChatRoom chatRoom) {
        if (entityManager.contains(chatRoom)) {
            entityManager.remove(chatRoom); // 만약 이미 영속 상태라면 제거
        } else {
            // 영속 상태가 아닐 경우, 해당 엔티티를 찾고 삭제
            ChatRoom managedUserChatRoom = entityManager.find(ChatRoom.class, chatRoom.getId());
            if (managedUserChatRoom != null) {
                entityManager.remove(managedUserChatRoom);
            }
        }
    }
}
