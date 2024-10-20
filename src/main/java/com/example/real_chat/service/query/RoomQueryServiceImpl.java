package com.example.real_chat.service.query;

import com.example.real_chat.entity.room.ChatRoom;
import com.example.real_chat.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomQueryServiceImpl implements RoomQueryService {

    private final RoomRepository roomRepository;

    @Override
    public ChatRoom getRoom(Long roomId) {
        return roomRepository.findById(roomId).orElseThrow();
    }

    @Override
    public List<ChatRoom> getAllRooms() { return roomRepository.findAll(); }

    @Override
    public List<ChatRoom> getUnDeletedRooms() {
        return roomRepository.findUnDeletedRooms();
    }
}