package com.example.real_chat.dto.rootclient.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateRootClientRequest {

    private String rootClientId;

    @Size(min = 10, message = "루트 클라이언트 비밀번호는 10글자 이상으로 입력해야 합니다.")
    private String rootClientPassword;

    private String rootClientName;
}
