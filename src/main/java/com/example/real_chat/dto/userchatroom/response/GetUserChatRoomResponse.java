package com.example.real_chat.dto.userchatroom.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserChatRoomResponse {

    private Long userChatRoomId;

    private GetUserReseponse userResponse;
    private GetChatRoomResponse chatRoom;
}
