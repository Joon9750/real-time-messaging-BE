package com.example.real_chat.dto.user.request;

import lombok.Getter;

@Getter
public class CreateUserRequest {

    private String userName;
    private Long rootClientId;
}
