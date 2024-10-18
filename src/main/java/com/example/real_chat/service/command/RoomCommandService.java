package com.example.real_chat.service.command;

import com.example.real_chat.entity.room.ChatRoom;

public interface RoomCommandService {

    public Long addRoom(ChatRoom chatRoom);
    public void updateChatRoom(Long roomId, String name);
    public void deleteRoom(Long id);
}
