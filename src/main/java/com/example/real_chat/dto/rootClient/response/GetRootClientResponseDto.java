package com.example.real_chat.dto.rootClient.response;

import com.example.real_chat.entity.RootClient;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetRootClientResponseDto {

    private RootClient rootClient;
}
