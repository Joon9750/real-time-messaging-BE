package com.example.real_chat.service;

import com.example.real_chat.entity.ChatRoom;
import com.example.real_chat.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public Long addRoom(ChatRoom chatRoom) {
        return roomRepository.save(chatRoom);
    }

    public ChatRoom getRoom(Long roomId) {
        return roomRepository.findById(roomId).orElseThrow();
    }

    public void deleteRoom(Long roomId) {
        ChatRoom chatRoom = roomRepository.findById(roomId).orElseThrow();
        if (chatRoom.isDeleted()) throw new RuntimeException();
        else chatRoom.delete();
    }

    public List<ChatRoom> getAllRooms() {
        return roomRepository.findAll()
                .orElseThrow();
    }

    public List<ChatRoom> getUnDeletedRooms() {
        return roomRepository.findUnDeletedRooms().orElseThrow();
    }

    public void updateChatRoom(Long roomId, String name) {
        ChatRoom room = roomRepository.findById(roomId).orElseThrow();
        room.update(name);
    }
}
