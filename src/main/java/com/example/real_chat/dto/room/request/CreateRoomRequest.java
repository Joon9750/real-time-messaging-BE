package com.example.real_chat.dto.room.request;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateRoomRequest {

    @NotBlank(message = "채팅방 이름을 입력해주세요. 빈 문자열은 불가합니다.")
    private String name;

    @NotNull(message = "루트 클라이언트 식별자 아이디를 입력해주세요.")
    private Long rootClientId;
}
