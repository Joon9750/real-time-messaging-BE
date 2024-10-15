package com.example.real_chat.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "rootclient_id")
    private RootClient client;
}
