package com.example.real_chat.dto.rootClient.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateRootClientRequestDto {

    @NotBlank
    private String rootClientId;

    @NotBlank
    @Size(min = 10)
    private String rootClientPassword;

    @NotBlank
    private String rootClientName;
}
