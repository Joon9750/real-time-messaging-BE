package com.example.real_chat.dto.room.response;

import com.example.real_chat.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoomResponseDTO {

    private Long id;
    private String name;

    public static RoomResponseDTO from(Room room) {
        return new RoomResponseDTO(room.getId(), room.getName());
    }
}
