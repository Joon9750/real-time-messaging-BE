package com.example.real_chat.dto.room.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateRoomRequest {

    @NotBlank
    private String name;

    @NotNull
    private Long rootClientId;
}
