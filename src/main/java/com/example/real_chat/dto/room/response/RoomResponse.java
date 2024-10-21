package com.example.real_chat.dto.room.response;

import com.example.real_chat.entity.room.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.Hibernate;

@Getter
@AllArgsConstructor
public class RoomResponse {

    private Long id;
    private String name;
    private Long rootClientId;

    public RoomResponse(ChatRoom chatRoom) {
        this.id = chatRoom.getId();
        this.name = chatRoom.getName();
        this.rootClientId = chatRoom.getRootClient().getId();
    }
}
