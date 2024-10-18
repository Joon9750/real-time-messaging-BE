package com.example.real_chat.service.command;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.user.User;

public interface UserChatRoomCommandService {

    Long joinChatRoom(User user, ChatRoom chatRoom);
    void leaveChatRoom(User user, ChatRoom chatRoom);
}
