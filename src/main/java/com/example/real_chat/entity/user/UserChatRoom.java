package com.example.real_chat.entity.user;

import com.example.real_chat.entity.room.ChatRoom;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class UserChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Setter
    private User user;

    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    @Setter
    private ChatRoom chatRoom;
}
