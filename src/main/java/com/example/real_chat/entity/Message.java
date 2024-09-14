package com.example.real_chat.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends Resource<String> {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;

    public static Message createMessage(Room room, String sender, String textData) {
        return Message.builder()
                .room(room)
                .sender(sender)
                .data(textData)
                .build();
    }
}