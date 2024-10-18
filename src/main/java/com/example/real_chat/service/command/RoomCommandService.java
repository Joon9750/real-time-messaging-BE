package com.example.real_chat.service.command;

import com.example.real_chat.entity.room.ChatRoom;

public interface RoomCommandService {

    Long addRoom(ChatRoom chatRoom);
    void updateChatRoom(Long roomId, String name);
    void deleteRoom(Long id);
}
