package com.example.real_chat.repository;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.user.User;
import com.example.real_chat.entity.userChatRoom.UserChatRoom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public Optional<UserChatRoom> findByUserAndChatRoom(User user, ChatRoom chatRoom) {
        try {
            UserChatRoom userChatRoom = (UserChatRoom) entityManager.createQuery(
                            "select uc from UserChatRoom uc where uc.user = :user and uc.chatRoom = :chatRoom")
                    .setParameter("user", user)
                    .setParameter("chatRoom", chatRoom)
                    .getSingleResult();

            return Optional.of(userChatRoom);
        } catch (NoResultException e) {
            return Optional.empty(); // 결과가 없을 때는 Optional.empty() 반환
        }
    }

    @Override
    public List<UserChatRoom> findByUser(User user) {
        return entityManager.createQuery(
                "select uc from UserChatRoom uc where uc.user = :user", UserChatRoom.class)
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

    @Override
    public void delete(UserChatRoom userChatRoom) {
        if (entityManager.contains(userChatRoom)) {
            entityManager.remove(userChatRoom); // 만약 이미 영속 상태라면 제거
        } else {
            // 영속 상태가 아닐 경우, 해당 엔티티를 찾고 삭제
            UserChatRoom managedUserChatRoom = entityManager.find(UserChatRoom.class, userChatRoom.getId());
            if (managedUserChatRoom != null) {
                entityManager.remove(managedUserChatRoom);
            }
        }
    }
}