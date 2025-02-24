package com.example.real_chat.service.builder;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.rootclient.RootClient;
import com.example.real_chat.entity.user.User;
import com.example.real_chat.entity.userchatroom.UserChatRoom;

import java.util.ArrayList;
import java.util.List;

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

    public static List<UserChatRoom> createUserChatRooms() {
        List<UserChatRoom> userChatRooms = new ArrayList<>();

        ChatRoom chatRoom1 = createChatRoom(createRootClient());
        ChatRoom chatRoom2 = ChatRoom.builder()
                .id(2L)
                .name("secondChatRoom")
                .rootClient(createRootClient())
                .build();

        UserChatRoom userChatRoom1 = UserChatRoom.create(createSpecificUser(createRootClient(), "firstUser"), chatRoom1);
        UserChatRoom userChatRoom2 = UserChatRoom.create(createSpecificUser(createRootClient(), "secondUser"), chatRoom2);
        UserChatRoom userChatRoom3 = UserChatRoom.create(createSpecificUser(createRootClient(), "thirdUser"), chatRoom2);

        userChatRooms.add(userChatRoom1);
        userChatRooms.add(userChatRoom2);
        userChatRooms.add(userChatRoom3);

        return userChatRooms;
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
