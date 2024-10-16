package com.example.real_chat.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "rootclient_id")
    private RootClient rootClient;

    public static ChatRoom createRoom(String name) {
        return ChatRoom.builder()
                .name(name)
                .build();
    }

    public void update(String name) {
        this.name = name;
    }
}