package com.example.real_chat.entity.room;

import com.example.real_chat.entity.rootClient.RootClient;
import com.example.real_chat.entity.base.BaseTimeEntity;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rootclient_id")
    private RootClient rootClient;

    public static ChatRoom createRoom(String name, RootClient rootClient) {
        return ChatRoom.builder()
                .name(name)
                .rootClient(rootClient)
                .build();
    }

    public void update(String name) {
        this.name = name;
    }
}