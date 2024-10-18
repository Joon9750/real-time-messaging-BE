package com.example.real_chat.repository;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.user.User;
import com.example.real_chat.entity.userChatRoom.UserChatRoom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserChatRoomRepositorylmpl implements UserChatRoomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long save(UserChatRoom userChatRoom) {
        entityManager.persist(userChatRoom);
        return userChatRoom.getId();
    }

    @Override
    public List<UserChatRoom> findByUser(User user) {
        return entityManager.createQuery(
                "SELECT uc FROM UserChatRoom uc WHERE uc.user = :user", UserChatRoom.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public List<UserChatRoom> findByChatRoom(ChatRoom chatRoom) {
        return entityManager.createQuery(
                "select uc from UserChatRoom uc where uc.chatRoom = :chatRoom",UserChatRoom.class)
                .setParameter("chatRoom", chatRoom)
                .getResultList();
    }

    @Override
    public boolean existsByUserAndChatRoom(User user, ChatRoom chatRoom) {
        return (boolean) entityManager.createQuery(
                "select case when count(uc) > 0 then true else false end" +
                        " from UserChatRoom uc where uc.user = :user and uc.chatRoom = :chatRoom")
                .setParameter("user", user)
                .setParameter("chatRoom", chatRoom)
                .getSingleResult();
    }
}