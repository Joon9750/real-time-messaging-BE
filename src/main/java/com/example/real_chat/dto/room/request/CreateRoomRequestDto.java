package com.example.real_chat.dto.room.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateRoomRequestDto {

    @NotBlank
    private String name;
}
