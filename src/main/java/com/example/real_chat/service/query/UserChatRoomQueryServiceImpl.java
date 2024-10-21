package com.example.real_chat.service.query;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.user.User;
import com.example.real_chat.entity.userChatRoom.UserChatRoom;
import com.example.real_chat.repository.UserChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserChatRoomQueryServiceImpl implements UserChatRoomQueryService {

    private final UserChatRoomRepository userChatRoomRepository;

    private final UserQueryService userQueryService;
    private final RoomQueryService roomQueryService;

    @Override
    public UserChatRoom findUserInChatRoom(Long userId, Long chatRoomId) {
        return userChatRoomRepository.findByUserAndChatRoom(findUserById(userId), findChatRoomById(chatRoomId))
                .orElseThrow();
    }

    @Override
    public List<UserChatRoom> getChatRoomsUserParticipatesIn(Long userId) {
        return userChatRoomRepository.findByUser(findUserById(userId));
    }

    @Override
    public List<UserChatRoom> getParticipantsInChatRoom(Long chatRoomId) {
        return userChatRoomRepository.findByChatRoom(findChatRoomById(chatRoomId));
    }

    @Override
    public Boolean doesUserExistInChatRoom(Long userId, Long chatRoomId) {
        return userChatRoomRepository.existsByUserAndChatRoom(findUserById(userId), findChatRoomById(chatRoomId));
    }

    private User findUserById(Long userId) {
        return userQueryService.getUserById(userId);
    }

    private ChatRoom findChatRoomById(Long chatRoomId) {
        return roomQueryService.getRoom(chatRoomId);
    }
}
