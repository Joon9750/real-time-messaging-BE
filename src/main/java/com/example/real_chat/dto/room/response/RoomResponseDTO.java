package com.example.real_chat.dto.room.response;

import com.example.real_chat.entity.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoomResponseDTO {

    private Long id;
    private String name;
}
