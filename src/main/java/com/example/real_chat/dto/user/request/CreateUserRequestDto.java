package com.example.real_chat.dto.user.request;

import lombok.Getter;

@Getter
public class CreateUserRequestDto {

    private String userName;
    private Long rootClientId;
}
