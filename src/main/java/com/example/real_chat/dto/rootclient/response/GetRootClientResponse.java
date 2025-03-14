package com.example.real_chat.dto.rootclient.response;

import com.example.real_chat.entity.rootclient.RootClient;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetRootClientResponse {

    private Long id;

    private String rootClientId;
    private String rootClientPassword;
    private String rootClientName;

    public GetRootClientResponse(RootClient rootClient) {
        this.id = rootClient.getId();

        this.rootClientId = rootClient.getClientId();
        this.rootClientPassword = rootClient.getClientPassword();
        this.rootClientName = rootClient.getClientName();
    }
}
