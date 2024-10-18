package com.example.real_chat.entity.user;

import com.example.real_chat.entity.base.BaseTimeEntity;
import com.example.real_chat.entity.rootClient.RootClient;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "memeber") // 에러의 원인은 H2에서 "user"라는 이름이 어떠한 명령어로 예약되어 있기 때문입니다! 따라서 user 테이블을 사용하면, H2는 예약된 명령어라고 에러를 발생시키게 되요. 이를 막기 위해서는 테이블의 이름을 변경할 수도 있고
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
