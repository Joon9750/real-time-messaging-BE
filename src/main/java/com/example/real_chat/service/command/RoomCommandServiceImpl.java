package com.example.real_chat.service.command;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.rootClient.RootClient;
import com.example.real_chat.repository.RoomRepository;
import com.example.real_chat.service.query.RootClientQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomCommandServiceImpl implements RoomCommandService {

    private final RoomRepository roomRepository;

    private final RootClientQueryService rootClientQueryService;
    private final UserChatRoomCommandService userChatRoomCommandService;

    @Override
    public Long addRoom(String roomName, Long clientId) {
        RootClient rootClient = rootClientQueryService.getRootClient(clientId);
        ChatRoom chatRoom = ChatRoom.createRoom(roomName, rootClient);
        return roomRepository.save(chatRoom);
    }

    @Override
    public void updateChatRoom(Long roomId, String name) {
        ChatRoom room = getChatRoomOrThrow(roomId);
        room.update(name);
    }

    @Override
    public void deleteRoom(Long roomId) {
        ChatRoom chatRoom = getChatRoomOrThrow(roomId);
        roomRepository.delete(chatRoom);
        deleteUserChatRoom(roomId);
    }

    private ChatRoom getChatRoomOrThrow(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new NoSuchElementException("Room not found with id : " + roomId));
    }
    private void deleteUserChatRoom(Long roomId) {
        userChatRoomCommandService.leaveUserChatRoomByChatRoomId(roomId);
    }
}
