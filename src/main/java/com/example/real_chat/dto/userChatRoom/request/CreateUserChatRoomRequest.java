package com.example.real_chat.dto.userChatRoom.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateUserChatRoomRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long roomId;
}