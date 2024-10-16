package com.example.real_chat.repository;

import com.example.real_chat.entity.ChatRoom;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {

    Long save(ChatRoom chatRoom);
    ChatRoom findById(Long id);
    void delete(Long id);
    List<ChatRoom> findAll();
    List<ChatRoom> findUnDeletedRooms();
}
