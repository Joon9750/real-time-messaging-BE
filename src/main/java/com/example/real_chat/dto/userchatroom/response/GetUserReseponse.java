package com.example.real_chat.dto.userchatroom.response;

import com.example.real_chat.entity.userchatroom.UserChatRoom;
import lombok.Getter;

@Getter
public class GetUserReseponse {

    private final Long userId;
    private final String userName;

    public GetUserReseponse(UserChatRoom userChatRoom) {
        this.userId = userChatRoom.getUser().getId();
        this.userName = userChatRoom.getUser().getUserName();
    }
}
