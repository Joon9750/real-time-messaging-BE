package com.example.real_chat.dto.rootClient.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateRootClientRequest {

    @NotBlank(message = "루트 클라이언트 아이디를 입력해주세요. 빈 문자열은 불가합니다.")
    private String rootClientId;

    @NotBlank(message = "루트 클라이언트 비밀번호를 입력해주세요. 빈 문자열은 불가합니다.")
    @Size(min = 10, message = "루트 클라이언트 비밀번호는 10글자 이상으로 입력해야 합니다.")
    private String rootClientPassword;

    @NotBlank(message = "루트 클라이언트 이름을 입력해주세요. 빈 문자열은 불가합니다.")
    private String rootClientName;
}
