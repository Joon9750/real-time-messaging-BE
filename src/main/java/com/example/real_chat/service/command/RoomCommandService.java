package com.example.real_chat.service.command;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomCommandService {

    private final RoomRepository roomRepository;

    public Long addRoom(ChatRoom chatRoom) {
        return roomRepository.save(chatRoom);
    }

    public void updateChatRoom(Long roomId, String name) {
        ChatRoom room = roomRepository.findById(roomId).orElseThrow();
        room.update(name);
    }

    public void deleteRoom(Long roomId) {
        ChatRoom chatRoom = roomRepository.findById(roomId).orElseThrow();
        if (chatRoom.isDeleted()) throw new RuntimeException();
        else chatRoom.delete();
    }
}
