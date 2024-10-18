package com.example.real_chat.entity.user;

import com.example.real_chat.entity.base.BaseTimeEntity;
import com.example.real_chat.entity.rootClient.RootClient;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String userName;

    @ManyToOne
    @JoinColumn(name = "rootclient_id")
    private RootClient client;



    public static User create(String name, RootClient client) {
        User user = new User();
        user.userName = name;
        user.client = client;
        return user;
    }

    public void update(String name) {
        this.userName = name;
    }
}
