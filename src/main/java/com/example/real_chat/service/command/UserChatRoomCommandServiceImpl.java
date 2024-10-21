package com.example.real_chat.service.command;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.user.User;
import com.example.real_chat.entity.userChatRoom.UserChatRoom;
import com.example.real_chat.repository.UserChatRoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserChatRoomCommandServiceImpl implements UserChatRoomCommandService {

    private final UserChatRoomRepository userChatRoomRepository;

    @Override
    public Long joinChatRoom(User user, ChatRoom chatRoom) {
        // 유저가 이미 해당 채팅방에 속해 있는지 확인
        boolean isUserInChatRoom = userChatRoomRepository.existsByUserAndChatRoom(user, chatRoom);

        if (!isUserInChatRoom) {
            // 유저가 채팅방에 속해 있지 않으면 새로운 UserChatRoom 생성 및 저장
            UserChatRoom userChatRoom = new UserChatRoom();
            userChatRoom.setUser(user);
            userChatRoom.setChatRoom(chatRoom);

            userChatRoomRepository.save(userChatRoom);
            return userChatRoom.getId();
        } else {
            // 이미 참여 중이라면 필요한 다른 로직 처리 (에러 반환, 메시지 출력 등)
            throw new RuntimeException();
        }
    }

    @Override
    public void leaveChatRoom(User user, ChatRoom chatRoom) {
        userChatRoomRepository.findByUserAndChatRoom(user, chatRoom).ifPresent(userChatRoomRepository::delete);
    }

    @Override
    public void deleteChatRoom(ChatRoom chatRoom) {
        List<UserChatRoom> usersInChatRoom = userChatRoomRepository.findByChatRoom(chatRoom);
        for (UserChatRoom userChatRoom : usersInChatRoom) {
            userChatRoomRepository.delete(userChatRoom);
        }
    }
}
