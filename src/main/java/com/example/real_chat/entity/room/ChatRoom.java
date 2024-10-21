package com.example.real_chat.entity.room;

import com.example.real_chat.entity.rootClient.RootClient;
import com.example.real_chat.entity.base.BaseTimeEntity;
import com.example.real_chat.entity.userChatRoom.UserChatRoom;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE)
    private List<UserChatRoom> userChatRooms = new ArrayList<>();

    public static ChatRoom createRoom(String name, RootClient rootClient) {
        return ChatRoom.builder()
                .name(name)
                .rootClient(rootClient)
                .build();
    }

    public void update(String name) {
        this.name = name;
    }

    public void addUserChatRoom(UserChatRoom userChatRoom) {
        userChatRooms.add(userChatRoom);
        userChatRoom.setChatRoom(this); // 양방향 관계 설정
    }

    public void removeUserChatRoom(UserChatRoom userChatRoom) {
        userChatRooms.remove(userChatRoom);
        userChatRoom.setChatRoom(null); // 양방향 관계 해제
    }
}