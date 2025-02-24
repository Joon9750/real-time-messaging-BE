package com.example.real_chat.service.builder;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.rootClient.RootClient;
import com.example.real_chat.entity.user.User;
import com.example.real_chat.entity.userChatRoom.UserChatRoom;

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
        return User.builder()
                .id(1L)
                .userName("name")
                .client(rootClient)
                .build();
    }

    public static User createSpecificUser(RootClient rootClient, long id, String name) {
        return User.builder()
                .id(id)
                .userName(name)
                .client(rootClient)
                .build();
    }

    public static UserChatRoom createUserChatRoom(User user, ChatRoom chatRoom) {
        return UserChatRoom.builder()
                .id(1L)
                .chatRoom(chatRoom)
                .user(user)
                .build();
    }
}
