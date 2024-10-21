package com.example.real_chat.dto.rootClient.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateRootClientRequest {

    @NotBlank
    private String rootClientId;

    @NotBlank
    @Size(min = 10)
    private String rootClientPassword;

    @NotBlank
    private String rootClientName;
}
