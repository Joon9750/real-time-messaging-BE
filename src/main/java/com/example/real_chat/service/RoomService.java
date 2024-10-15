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
        return roomRepository.findById(roomId);
    }

    public void deleteRoom(Long roomId) {
        roomRepository.delete(roomId);
    }

    public List<ChatRoom> getAllRooms() {
        return roomRepository.findAll();
    }
}
