package com.example.real_chat.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class RootClient extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rootclient_id")
    private Long id;

    private String clientId;

    private String clientPassword;

    private String clientName;
}