package com.example.real_chat.service.command;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.user.User;

public interface UserChatRoomCommandService {

    void joinChatRoom(User user, ChatRoom chatRoom);
}
