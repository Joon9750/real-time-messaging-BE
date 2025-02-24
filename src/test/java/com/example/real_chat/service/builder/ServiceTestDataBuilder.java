package com.example.real_chat.service.builder;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.rootclient.RootClient;
import com.example.real_chat.entity.user.User;
import com.example.real_chat.entity.userchatroom.UserChatRoom;

public class ServiceTestDataBuilder {

    public static RootClient createRootClient() {
        return RootClient.builder()
                .id(1L)
                .clientId("clientId")
                .clientPassword("clientPassword")
                .clientName("clientName")
                .build();
    }

    public static ChatRoom createChatRoom(RootClient rootClient) {
        return ChatRoom.builder()
                .id(1L)
                .name("newChatRoom")
                .rootClient(rootClient)
                .build();
    }

    public static User createUser(RootClient rootClient) {
        return User.create("userName", rootClient);
    }

    public static User createSpecificUser(RootClient rootClient, String name) {
        return User.create(name, rootClient);
    }

    public static UserChatRoom createUserChatRoom(User user, ChatRoom chatRoom) {
        return UserChatRoom.create(user, chatRoom);
    }
}
