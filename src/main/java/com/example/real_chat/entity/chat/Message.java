package com.example.real_chat.entity.chat;

import com.example.real_chat.entity.room.ChatRoom;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends Resource<String> {

    public static Message createMessage(
            ChatRoom chatRoom, String sender, String textData
    ) {
        return Message.builder()
                .chatRoom(chatRoom)
                .sender(sender)
                .data(textData)
                .build();
    }
}