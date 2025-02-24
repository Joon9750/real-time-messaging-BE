package com.example.real_chat.entity.userchatroom;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Setter
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    @Setter
    private ChatRoom chatRoom;

    // private 생성자 사용 (AllArgsConstructor 대신)
    private UserChatRoom(User user, ChatRoom chatRoom) {
        this.user = user;
        this.chatRoom = chatRoom;
    }

    // 정적 팩토리 메서드 사용
    public static UserChatRoom create(User user, ChatRoom chatRoom) {
        return new UserChatRoom(user, chatRoom);
    }
}