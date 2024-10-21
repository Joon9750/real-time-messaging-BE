package com.example.real_chat.dto.userChatRoom.response;

import com.example.real_chat.entity.userChatRoom.UserChatRoom;
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
