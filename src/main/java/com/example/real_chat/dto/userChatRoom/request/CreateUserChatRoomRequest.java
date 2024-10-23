package com.example.real_chat.dto.userChatRoom.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateUserChatRoomRequest {

    @NotNull(message = "유저 식별자 아이디를 입력해주세요.")
    private Long userId;

    @NotNull(message = "채팅방 식별자 아이디를 입력해주세요.")
    private Long roomId;
}
