package com.example.real_chat.dto.room.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateRoomRequest {

    @NotBlank(message = "변경할 채팅방 이름을 입력해주세요.")
    private String name;
}
