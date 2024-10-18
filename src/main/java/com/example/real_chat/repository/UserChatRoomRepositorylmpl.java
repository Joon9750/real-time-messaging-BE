package com.example.real_chat.repository;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.user.User;
import com.example.real_chat.entity.user.UserChatRoom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserChatRoomRepositorylmpl implements UserChatRoomRepository {

    @Override
    public Long save(UserChatRoom userChatRoom) {
        return 0;
    }

    @Override
    public List<UserChatRoom> findByUser(User user) {
        return List.of();
    }

    @Override
    public List<UserChatRoom> findByChatRoom(ChatRoom chatRoom) {
        return List.of();
    }

    @Override
    public boolean existsByUserAndChatRoom(User user, ChatRoom chatRoom) {
        return false;
    }
}
