package com.example.real_chat.entity.rootClient;

import com.example.real_chat.entity.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RootClient extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rootclient_id")
    private Long id;

    private String clientId;

    private String clientPassword;

    private String clientName;

    public static RootClient createRootClient(String clientId, String clientPassword, String clientName) {
        return RootClient.builder()
                .clientId(clientId)
                .clientPassword(clientPassword)
                .clientName(clientName)
                .build();
    }

    public void update(String clientId, String clientPassword, String clientName) {
        this.clientId = clientId;
        this.clientPassword = clientPassword;
        this.clientName = clientName;
    }
}