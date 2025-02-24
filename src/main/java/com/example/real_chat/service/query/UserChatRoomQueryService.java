package com.example.real_chat.service.query;


import com.example.real_chat.entity.userchatroom.UserChatRoom;

import java.util.List;

public interface UserChatRoomQueryService {

    UserChatRoom getUserInChatRoom(Long userId, Long chatRoomId);
    List<UserChatRoom> getChatRoomsUserParticipatesIn(Long userId);
    List<UserChatRoom> getParticipantsInChatRoom(Long chatRoomId);
    Boolean doesUserExistInChatRoom(Long userId, Long chatRoomId);
}
