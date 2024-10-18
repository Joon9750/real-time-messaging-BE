package com.example.real_chat.entity.userChatRoom;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.user.User;
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