package com.example.real_chat.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserResponse {

    private Long id;
    private String userName;
}
