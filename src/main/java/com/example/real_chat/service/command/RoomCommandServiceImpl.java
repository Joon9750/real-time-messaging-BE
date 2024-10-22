package com.example.real_chat.service.command;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.entity.rootClient.RootClient;
import com.example.real_chat.repository.RoomRepository;
import com.example.real_chat.service.query.RootClientQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        ChatRoom room = roomRepository.findById(roomId).orElseThrow();
        room.update(name);
    }

    @Override
    public void deleteRoom(Long roomId) {
        ChatRoom chatRoom = roomRepository.findById(roomId).orElseThrow();
        roomRepository.delete(chatRoom);
    }

    private void deleteUserChatRoom(Long roomId) {
        userChatRoomCommandService.
    }
}
