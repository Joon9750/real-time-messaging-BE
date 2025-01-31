package com.example.real_chat.entity.user;

import com.example.real_chat.entity.base.BaseTimeEntity;
import com.example.real_chat.entity.rootClient.RootClient;
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

    public static User create(String name, RootClient client) {
        User user = new User();
        user.userName = name;
        user.client = client;
        return user;
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
