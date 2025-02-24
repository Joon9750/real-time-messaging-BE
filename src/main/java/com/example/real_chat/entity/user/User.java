package com.example.real_chat.entity.user;

import com.example.real_chat.entity.base.BaseTimeEntity;
import com.example.real_chat.entity.rootclient.RootClient;
import com.example.real_chat.entity.userchatroom.UserChatRoom;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "\"User\"")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String userName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rootclient_id")
    private RootClient client;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserChatRoom> userChatRooms = new ArrayList<>();

    private User(String userName, RootClient client) {
        this.userName = userName;
        this.client = client;
    }

    public static User create(String name, RootClient client) {
        return new User(name, client);
    }

    public void update(String name) {
        this.userName = name;
    }

    public void addUserChatRoom(UserChatRoom userChatRoom) {
        userChatRooms.add(userChatRoom);
        userChatRoom.setUser(this);
    }

    public void removeUserChatRoom(UserChatRoom userChatRoom) {
        userChatRooms.remove(userChatRoom);
        userChatRoom.setUser(null);
    }
}
