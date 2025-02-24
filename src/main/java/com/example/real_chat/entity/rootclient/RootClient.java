package com.example.real_chat.entity.rootclient;

import com.example.real_chat.entity.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @Column(name = "deleted_at", columnDefinition = "DATETIME")
    private LocalDateTime deletedAt;

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

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }

    public boolean isDeleted() {
        return this.deletedAt != null;
    }
}