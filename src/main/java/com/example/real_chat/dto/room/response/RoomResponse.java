package com.example.real_chat.dto.room.response;

import com.example.real_chat.entity.room.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoomResponse {

    private Long id;
    private String name;

    public RoomResponse(ChatRoom chatRoom) {
        this.id = chatRoom.getId();
        this.name = chatRoom.getName();
    }
}
