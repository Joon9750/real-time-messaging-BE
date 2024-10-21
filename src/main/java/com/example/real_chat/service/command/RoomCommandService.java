package com.example.real_chat.service.command;

import com.example.real_chat.entity.room.ChatRoom;

public interface RoomCommandService {

    Long addRoom(String roomName, Long clientId);
    void updateChatRoom(Long roomId, String name);
    void deleteRoom(Long id);
}
