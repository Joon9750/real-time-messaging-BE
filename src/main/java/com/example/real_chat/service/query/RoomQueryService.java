package com.example.real_chat.service.query;

import com.example.real_chat.entity.room.ChatRoom;

import java.util.List;

public interface RoomQueryService {

    ChatRoom getRoom(Long roomId);
    List<ChatRoom> getAllRooms();
}
