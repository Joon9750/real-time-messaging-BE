package com.example.real_chat.dto.rootClient.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateRootClientRequest {

    private String rootClientId;

    @Size(min = 10)
    private String rootClientPassword;

    private String rootClientName;
}
