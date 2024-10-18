package com.example.real_chat.service.command;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomCommandServiceImpl implements RoomCommandService {

    private final RoomRepository roomRepository;

    @Override
    public Long addRoom(ChatRoom chatRoom) {
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
        if (chatRoom.isDeleted()) throw new RuntimeException();
        else chatRoom.delete();
    }
}
