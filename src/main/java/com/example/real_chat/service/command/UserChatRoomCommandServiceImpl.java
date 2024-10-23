package com.example.real_chat.service.command;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.user.User;
import com.example.real_chat.entity.userChatRoom.UserChatRoom;
import com.example.real_chat.exception.CannotJoinChatRoomException;
import com.example.real_chat.repository.UserChatRoomRepository;
import com.example.real_chat.service.query.RoomQueryService;
import com.example.real_chat.service.query.UserQueryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserChatRoomCommandServiceImpl implements UserChatRoomCommandService {

    private final UserChatRoomRepository userChatRoomRepository;

    private final UserQueryService userQueryService;
    private final RoomQueryService roomQueryService;

    @Override
    public Long joinChatRoom(Long userId, Long chatRoomId) {
        User user = getUser(userId);
        ChatRoom chatRoom = getChatRoom(chatRoomId);

        boolean isUserInChatRoom = userChatRoomRepository.existsByUserAndChatRoom(user, chatRoom);

        if (!isUserInChatRoom) {
            UserChatRoom userChatRoom = new UserChatRoom();
            userChatRoom.setUser(user);
            userChatRoom.setChatRoom(chatRoom);

            user.addUserChatRoom(userChatRoom);

            userChatRoomRepository.save(userChatRoom);
            return userChatRoom.getId();
        } else {
            throw new CannotJoinChatRoomException();
        }
    }

    @Override
    public void leaveUserChatRoomByChatRoomId(Long chatRoomId) {
        ChatRoom chatRoom = getChatRoom(chatRoomId);
        List<UserChatRoom> userChatRoom = userChatRoomRepository.findByChatRoom(chatRoom);

        for (UserChatRoom m : userChatRoom) {
            User user = m.getUser();
            user.removeUserChatRoom(m);
            userChatRoomRepository.delete(m);
        }
    }

    private User getUser(Long userId) {
        return userQueryService.getUserById(userId);
    }

    private ChatRoom getChatRoom(Long chatRoomId) {
        return roomQueryService.getRoom(chatRoomId);
    }
}
