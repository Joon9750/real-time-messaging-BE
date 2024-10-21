package com.example.real_chat.dto.userChatRoom.response;

import com.example.real_chat.entity.userChatRoom.UserChatRoom;
import lombok.Getter;

@Getter
public class GetChatRoomResponse {

    private final Long roomId;
    private final String roomName;

    public GetChatRoomResponse(UserChatRoom userChatRoom) {
        this.roomId = userChatRoom.getChatRoom().getId();
        this.roomName = userChatRoom.getChatRoom().getName();
    }
}
