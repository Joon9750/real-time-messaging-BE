package com.example.real_chat.dto.room.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateRoomRequestDto {

    @NotBlank
    private String name;
}
