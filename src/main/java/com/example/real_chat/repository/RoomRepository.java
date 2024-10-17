package com.example.real_chat.repository;

import com.example.real_chat.entity.room.ChatRoom;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {

    Long save(ChatRoom chatRoom);

    Optional<ChatRoom> findById(Long id);
    Optional<List<ChatRoom>> findAll();
    Optional<List<ChatRoom>> findUnDeletedRooms();
}
