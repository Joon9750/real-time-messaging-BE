package com.example.real_chat.dto.userChatRoom.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class GetUsersInChatRoomRequest {

    @NotNull
    private Long roomId;
}
