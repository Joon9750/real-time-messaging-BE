package com.example.real_chat.repository;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.user.User;
import com.example.real_chat.entity.userChatRoom.UserChatRoom;

import java.util.List;

public interface UserChatRoomRepository {

    Long save(UserChatRoom userChatRoom);

    // 특정 유저가 참여한 채팅방 목록 조회
    List<UserChatRoom> findByUser(User user);

    // 특정 채팅방에 속한 유저 목록 조회
    List<UserChatRoom> findByChatRoom(ChatRoom chatRoom);

    // 특정 유저가 특정 채팅방에 참여 여부 확인
    boolean existsByUserAndChatRoom(User user, ChatRoom chatRoom);
}