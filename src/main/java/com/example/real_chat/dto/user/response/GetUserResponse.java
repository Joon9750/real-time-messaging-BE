package com.example.real_chat.dto.user.response;

import com.example.real_chat.entity.user.User;
import lombok.Getter;

@Getter
public class GetUserResponse {

    private final Long id;
    private final String userName;

    public GetUserResponse(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
    }
}
