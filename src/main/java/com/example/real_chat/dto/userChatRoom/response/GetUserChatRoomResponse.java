package com.example.real_chat.dto.userChatRoom.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserChatRoomResponse {

    private Long userChatRoomId;

    private GetUserReseponse userReseponse;
    private GetChatRoomResponse chatRoom;
}
