package com.example.real_chat.dto.userChatRoom.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetChatRoomResponse {
    private Long roomId;
    private String roomName;
}
