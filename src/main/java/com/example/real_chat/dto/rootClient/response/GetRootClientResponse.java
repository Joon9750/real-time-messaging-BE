package com.example.real_chat.dto.rootClient.response;

import com.example.real_chat.entity.rootClient.RootClient;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetRootClientResponse {

    private RootClient rootClient;
}
